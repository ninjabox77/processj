package visitor;

import ast.*;
import ast.Package;
import ast.expr.DeclarationExpr;
import ast.expr.Expression;
import ast.expr.VariableExpr;
import ast.java.FieldDeclaration;
import ast.stmt.EmptyStmt;
import ast.stmt.Statement;
import ast.toplevel.*;
import ast.types.*;
import misc.Tuple;
import misc.Tuple1;
import misc.Tuple2;
import org.objectweb.asm.Opcodes;
import parser.ProcessJBaseVisitor;
import parser.ProcessJParser;
import typesystem.*;

import static misc.ConfigureAST.*;

/**
 * Builds the AST from the parse tree generated by antlr4.
 *
 * @author Ben
 */
public class AstBuilder extends ProcessJBaseVisitor<Object> {

  private ArrayType getParentArray(ArrayType array) {
    while (!array.isOrphan()) {
      array = (ArrayType) array.getParentNode().get();
    }
    return array;
  }

  private ASTType createASTType(Type type) {
    if (type.isPrimitiveType()) {
      Primitive primitive = type.asPrimitiveType();
      PrimitiveNode primitiveNode = configureNode(new PrimitiveNode(), primitive);
      if (primitive.isByteType()) {
        primitiveNode.setTSType(primitive.asByteType());
      } else if (primitive.isIntegerType()) {
        primitiveNode.setTSType(primitive.asIntegerType());
      } else if (primitive.isShortType()) {
        primitiveNode.setTSType(primitive.asShortType());
      } else if (primitive.isBooleanType()) {
        primitiveNode.setTSType(primitive.asBooleanType());
      } else if (primitive.isLongType()) {
        primitiveNode.setTSType(primitive.asLongType());
      } else if (primitive.isFloatType()) {
        primitiveNode.setTSType(primitive.asFloatType());
      } else if (primitive.isDoubleType()) {
        primitiveNode.setTSType(primitive.asDoubleType());
      } else if (primitive.isCharType()) {
        primitiveNode.setTSType(primitive.asCharType());
      } else if (primitive.isTimerType()) {
        primitiveNode.setTSType(primitive.asTimerType());
      } else if (primitive.isBarrierType()) {
        primitiveNode.setTSType(primitive.asBarrierType());
      } else if (primitive.isStringType()) {
        primitiveNode.setTSType(primitive.asStringType());
      }
      return primitiveNode;
    } else if (type.isConstructedType()) {
      Constructed constructed = type.asConstructedType();
      if (constructed.isArrayType()) {
        ArrayType arrayType = constructed.asArrayType();
        ArrayNode arrayNode = configureNode(new ArrayNode(), arrayType);
        return arrayNode.setTSType(arrayType);
      } else if (constructed.isChannelType()) {
        ChannelType channelType = constructed.asChannelType();
        ChannelNode channelNode = configureNode(new ChannelNode(), channelType);
        return channelNode.setTSType(channelType);
      } else if (constructed.isChannelEndType()) {
        ChannelEndType channelEndType = constructed.asChannelEndType();
        ChannelEndNode channelEndNode = configureNode(new ChannelEndNode(), channelEndType);
        return channelEndNode.setTSType(channelEndType);
      } else if (constructed.isTypeVariable()) {
        TypeVariable typeVariable = constructed.asTypeVariable();
        ConstructedNode constructedNode = configureNode(new ConstructedNode(), typeVariable);
        return constructedNode.setTSType(typeVariable);
      }
      // types like protocol and record are resolved elsewhere
    }
    return null;
  }

  @Override
  public Object visitInit(ProcessJParser.InitContext ctx) {
    Package packet = visitPackageDeclaration(ctx.packageDeclaration());
    Sequence<Import> imports = Sequence.sequenceList();
    ctx.importDeclaration().forEach(i -> imports.add((Import) visit(i)));
    Sequence<TopLevelDeclaration<?>> topLevel = Sequence.sequenceList();
    ctx.typeDeclaration().forEach(t -> topLevel.add((TopLevelDeclaration<?>) visit(t)));
    return null;
  }

  @Override
  public Package visitPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx) {
    Package packet = new Package(ctx.Identifier().getText());
    return configureNode(packet, ctx);
  }

  @Override
  public Name visitPackageAccess(ProcessJParser.PackageAccessContext ctx) {
    String ident = ctx.Identifier(0).getText();
    Name oldName = configureNode(new Name(ident), ctx.Identifier(0));
    for (int i = 1; i < ctx.Identifier().size(); ++i) {
      ident = ctx.Identifier(i).getText();
      Name newName = configureNode(new Name(ident), ctx.Identifier(i));
      newName.setQualifier(oldName);
      oldName = newName;
    }
    return configureNode(oldName, ctx);
  }

  @Override
  public Import visitSingleImportDeclaration(ProcessJParser.SingleImportDeclarationContext ctx) {
    Name name = new Name(ctx.Identifier(0).getText());
    configureNode(name, ctx.Identifier(0));
    Import _import = new Import().setName(name);
    if (ctx.Identifier().size() > 1) {
      _import.setFieldName(ctx.Identifier(1).getText());
    }
    return configureNode(_import, ctx);
  }

  @Override
  public Import visitMultiImportDeclaration(ProcessJParser.MultiImportDeclarationContext ctx) {
    Name name = visitPackageAccess(ctx.packageAccess());
    Import _import = new Import().setName(name);
    if (ctx.Identifier() != null) {
      _import.setFieldName(ctx.Identifier().getText());
    }
    return configureNode(_import, ctx);
  }

  @Override
  public Import visitMultiImportDeclarationStar(ProcessJParser.MultiImportDeclarationStarContext ctx) {
    Name name = visitPackageAccess(ctx.packageAccess());
    Import _import = new Import().setName(name).setStar(true);
    return configureNode(_import, ctx);
  }

  @Override
  public ProcedureTopLevel visitProcedureDeclaration(ProcessJParser.ProcedureDeclarationContext ctx) {
    int modifiers = Opcodes.ACC_PRIVATE;
    if (ctx.modifier() != null) {
      modifiers -= Opcodes.ACC_PRIVATE;
      for (int i = 0; i < ctx.modifier().size(); ++i) {
        modifiers += visitModifier(ctx.modifier(i));
      }
    }
    ASTType type = visitType_(ctx.type_());
    String name = ctx.Identifier().toString();
    ProcedureTopLevel procedureDecl = new ProcedureTopLevel();
    if (ctx.formalDeclarationList() != null) {
      Sequence<Parameter> parameters = (Sequence<Parameter>) visit(ctx.formalDeclarationList());
      procedureDecl.setParameters(parameters);
    }
    procedureDecl.setModifiers(modifiers);
    procedureDecl.setASTType(type);
    procedureDecl.setName(name);
    return configureNode(procedureDecl, ctx);
  }

  @Override
  public Integer visitModifier(ProcessJParser.ModifierContext ctx) {
    if (ctx.PRIVATE() != null) {
      return Opcodes.ACC_PRIVATE;
    }
    if (ctx.PROTECTED() != null) {
      return Opcodes.ACC_PROTECTED;
    }
    if (ctx.CONST() != null) {
      return Opcodes.ACC_FINAL;
    }
    if (ctx.NATIVE() != null) {
      return Opcodes.ACC_NATIVE;
    }
    return Opcodes.ACC_PRIVATE;
  }

  @Override
  public ASTType visitType_(ProcessJParser.Type_Context ctx) {
    if (ctx.primitiveType() != null) {
      Primitive type = visitPrimitiveType(ctx.primitiveType());
      return configureNode(new PrimitiveNode(type), ctx);
    } else if (ctx.referenceType() != null) {
      Type type = visitReferenceType(ctx.referenceType());
      if (type.isConstructedType()) {
        Constructed ctr = type.asConstructedType();
        if (ctr.isArrayType()) {
          return configureNode(new ArrayNode(ctr.asArrayType()), ctx);
        } else if (ctr.isChannelType()) {
          return configureNode(new ChannelNode(ctr.asChannelType()), ctx);
        } else if (ctr.isChannelEndType()) {
          return configureNode(new ChannelEndNode(ctr.asChannelEndType()), ctx);
        } else if (ctr.isTypeVariable()) {
          return configureNode(new ConstructedNode(ctr.asTypeVariable()), ctx);
        }
      }
    } else if (ctx.classType() != null) {
      // TODO: this is for when a Java class is used
    }
    VoidType type = configureNode(new VoidType(), ctx);
    return configureNode(new VoidNode(type), ctx);
  }

  @Override
  public Primitive visitPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx) {
    if (ctx.numericType() != null) {
      return visitNumericType(ctx.numericType());
    } else if (ctx.BOOLEAN() != null) {
      return configureNode(new BooleanType(), ctx);
    } else if (ctx.STRING() != null) {
      return configureNode(new StringType(), ctx);
    } else if (ctx.BARRIER() != null) {
      return configureNode(new BarrierType(), ctx);
    }
    return configureNode(new TimerType(), ctx);
  }

  @Override
  public Primitive visitNumericType(ProcessJParser.NumericTypeContext ctx) {
    if (ctx.integralType() != null) {
      return visitIntegralType(ctx.integralType());
    }
    return visitFloatingPointType(ctx.floatingPointType());
  }

  @Override
  public Primitive visitIntegralType(ProcessJParser.IntegralTypeContext ctx) {
    if (ctx.BYTE() != null) {
      return configureNode(new ByteType(), ctx);
    } else if (ctx.SHORT() != null) {
      return configureNode(new ShortType(), ctx);
    } else if (ctx.INT() != null) {
      return configureNode(new IntegerType(), ctx);
    } else if (ctx.LONG() != null) {
      return configureNode(new LongType(), ctx);
    }
    return configureNode(new CharType(), ctx);
  }

  @Override
  public Primitive visitFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx) {
    if (ctx.FLOAT() != null) {
      return configureNode(new FloatType(), ctx);
    }
    return configureNode(new DoubleType(), ctx);
  }

  @Override
  public Type visitReferenceType(ProcessJParser.ReferenceTypeContext ctx) {
    if (ctx.arrayType() != null) {
      return configureNode(visitArrayType(ctx.arrayType()), ctx);
    } else if (ctx.channelType() != null) {
      return configureNode((Type) visit(ctx.channelType()), ctx);
    }
    return configureNode((Type) visit(ctx.typeVariable()), ctx);
  }

  @Override
  public ArrayType visitArrayType(ProcessJParser.ArrayTypeContext ctx) {
    ArrayType array = visitDims(ctx.dims());
    if (ctx.primitiveType() != null) {
      Type type = visitPrimitiveType(ctx.primitiveType());
      array.setComponentType(type);
    } else if (ctx.channelType() != null) {
      Type type = (Type) visit(ctx.channelType());
      array.setComponentType(type);
    } else if (ctx.typeVariable() != null) {
      Type type = (Type) visit(ctx.typeVariable());
      array.setComponentType(type);
    }
    return getParentArray(array);
  }

  //
  // we build an array-type in reverse order so that in the body of
  // the caller we can set its component-type; e.g.:
  //
  //    ArrayType(ArrayType(ArrayType(<component-type>)))
  //
  // where component-type can be an atomic or constructed type
  //
  @Override
  public ArrayType visitDims(ProcessJParser.DimsContext ctx) {
    ArrayType outerType = configureNode(new ArrayType(), ctx.LBRACK(0));
    for (int i = 1; i < ctx.LBRACK().size(); ++i) {
      ArrayType innerType = configureNode(new ArrayType(), ctx.LBRACK(i));
      outerType.setComponentType(innerType);
      outerType = innerType;
    }
    return outerType;
  }

  @Override
  public ChannelType visitChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelType chan = new ChannelType(type, ChannelType.SHARED_READ);
    return configureNode(chan, ctx);
  }

  @Override
  public ChannelType visitChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelType chan = new ChannelType(type, ChannelType.SHARED_WRITE);
    return configureNode(chan, ctx);
  }

  @Override
  public ChannelType visitChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelType chan = new ChannelType(type, ChannelType.SHARED);
    return configureNode(chan, ctx);
  }

  @Override
  public ChannelType visitChannelType_(ProcessJParser.ChannelType_Context ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelType chan = new ChannelType(type, ChannelType.NON_SHARED);
    return configureNode(chan, ctx);
  }

  @Override
  public ChannelEndType visitChannelDotRead(ProcessJParser.ChannelDotReadContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelEndType chan = new ChannelEndType(type, ChannelEndType.READ_END);
    return configureNode(chan, ctx);
  }

  @Override
  public ChannelEndType visitChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelEndType chan = new ChannelEndType(type, ChannelEndType.WRITE_END);
    return configureNode(chan, ctx);
  }

  @Override
  public Object visitChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelEndType chan = new ChannelEndType(type, ChannelEndType.SHARED + ChannelEndType.READ_END);
    return configureNode(chan, ctx);
  }

  @Override
  public Object visitChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx) {
    Type type = configureNode((Type) visit(ctx.type_()), ctx.type_());
    ChannelEndType chan = new ChannelEndType(type, ChannelEndType.SHARED + ChannelEndType.WRITE_END);
    return configureNode(chan, ctx);
  }

  @Override
  public TypeVariable visitTypeVariableIdentifier(ProcessJParser.TypeVariableIdentifierContext ctx) {
    Name name = configureNode(new Name(ctx.Identifier().getText()), ctx.Identifier());
    return configureNode(new TypeVariable(name), ctx);
  }

  @Override
  public TypeVariable visitTypeVariablePackageDotIdentifier(ProcessJParser.TypeVariablePackageDotIdentifierContext ctx) {
    Name name = new Name();
    name.setQualifier(visitPackageAccess(ctx.packageAccess()));
    name.setIdentifier(ctx.Identifier().getText());
    configureNode(name, ctx.packageAccess());
    return configureNode(new TypeVariable(name), ctx);
  }

  @Override
  public Sequence<Parameter> visitFormalDeclaration_(ProcessJParser.FormalDeclaration_Context ctx) {
    Sequence<Parameter> parameters = Sequence.sequenceList();
    parameters.addAll(visitFormalDeclarations(ctx.formalDeclarations()));
    // TODO: lastFormalDeclaration??
    return parameters;
  }

  @Override
  public Sequence<Parameter> visitLastFormalDeclaration_(ProcessJParser.LastFormalDeclaration_Context ctx) {
    return null;
  }

  @Override
  public Sequence<Parameter> visitFormalDeclarations(ProcessJParser.FormalDeclarationsContext ctx) {
    Sequence<Parameter> parameters = Sequence.sequenceList();
    ctx.formalDeclaration().forEach(p -> parameters.add(visitFormalDeclaration(p)));
    return parameters;
  }

  @Override
  public Parameter visitFormalDeclaration(ProcessJParser.FormalDeclarationContext ctx) {
    // TODO: variableModifier??
    ASTType type = visitType_(ctx.type_());
    VariableDecl variableDecl = visitVariableDeclarator(ctx.variableDeclarator());
    if (variableDecl.getASTType() != null) {
      if (variableDecl.getASTType() != null) {
        ArrayNode arrayNode = variableDecl.getASTType().asArrayNode();
        arrayNode.getTSType().setTSType(type.getTSType());
        variableDecl.setASTType(arrayNode);
      } else {
        variableDecl.setASTType(type);
      }
    }
    Parameter parameter = new Parameter();
    parameter.setASTType(variableDecl.getASTType());
    parameter.setName(variableDecl.getName());
    return configureNode(parameter, ctx);
  }

  @Override
  public Object visitBlockExpression(ProcessJParser.BlockExpressionContext ctx) {
    return null;
  }

  @Override
  public RecordDecl visitRecordDeclaration(ProcessJParser.RecordDeclarationContext ctx) {
    int modifiers = Opcodes.ACC_PRIVATE;
    if (ctx.modifier() != null) {
      modifiers -= Opcodes.ACC_PRIVATE;
      for (int i = 0; i < ctx.modifier().size(); ++i) {
        modifiers += visitModifier(ctx.modifier(i));
      }
    }
    String name = ctx.Identifier().getText();
    Sequence<TopLevelDeclaration<?>> implementedNames = Sequence.sequenceList();
    if (ctx.extends_() != null) {
      Sequence<Name> _extends = visitExtends(ctx.extends_());
      for (Name n : _extends) {
        TypeVariableDecl typeDecl = configureNode(new TypeVariableDecl(), n);
        typeDecl.setQualifier(n);
        implementedNames.add(typeDecl);
      }
    }
    Sequence<FieldDeclaration> fields = visitRecordBody(ctx.recordBody());
    RecordDecl recordDecl = new RecordDecl();
    recordDecl.setModifiers(modifiers);
    recordDecl.setName(name);
    recordDecl.setImplementedNames(implementedNames);
    recordDecl.setDeclaredFields(fields);
    return configureNode(recordDecl, ctx);
  }

  @Override
  public Sequence<Name> visitExtends(ProcessJParser.ExtendsContext ctx) {
    Sequence<Name> _extends = Sequence.sequenceList();
    ctx.typeVariable().forEach(n -> _extends.add((Name) visit(n)));
    return _extends;
  }

  @Override
  public Sequence<FieldDeclaration> visitRecordBody(ProcessJParser.RecordBodyContext ctx) {
    Sequence<FieldDeclaration> fields = Sequence.sequenceList();
    if (ctx.recordField() != null) {
      ctx.recordField().forEach(f -> fields.addAll(visitRecordField(f)));
    }
    return fields;
  }

  @Override
  public Sequence<FieldDeclaration> visitRecordField(ProcessJParser.RecordFieldContext ctx) {
    Sequence<FieldDeclaration> fields = Sequence.sequenceList();
    Type type = (Type) visit(ctx.type_());
    Sequence<VariableDecl> variables = visitVariableDeclaratorList(ctx.variableDeclaratorList());
    for (VariableDecl v : variables) {
      if (v.getASTType() != null) {
        ArrayNode arrayNode = v.getASTType().asArrayNode();
        arrayNode.getTSType().setTSType(type);
        v.setASTType(arrayNode);
      } else {
        v.setASTType(createASTType(type));
      }
      FieldDeclaration field = new FieldDeclaration(v);
    }
    return fields;
  }

  @Override
  public Sequence<VariableDecl> visitVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx) {
    Sequence<VariableDecl> variables = Sequence.sequenceList();
    ctx.variableDeclarator().forEach(v -> variables.add(visitVariableDeclarator(v)));
    return variables;
  }

  @Override
  public VariableDecl visitVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx) {
    VariableDecl variableDecl = new VariableDecl();
    Tuple<?> tuple = visitVariableDeclaratorIdentifier(ctx.variableDeclaratorIdentifier());
    if (tuple.isTuple1()) {
      Tuple1<String> t1 = tuple.asTuple1();
      variableDecl.setName(t1.getV1());
      configureNode(variableDecl, ctx.variableDeclaratorIdentifier());
    } else {
      Tuple2<String, ArrayType> t2 = tuple.asTuple2();
      ArrayNode arrayNode = configureNode(new ArrayNode(t2.getV2()), t2.getV2());
      variableDecl.setASTType(arrayNode);
      variableDecl.setName(t2.getV1());
      configureNode(variableDecl, ctx);
    }
    if (ctx.variableInitializer() != null) {
      Expression<?> rightExpression = visitVariableInitializer(ctx.variableInitializer());
      variableDecl.setRightExpression(rightExpression);
    }
    return variableDecl;
  }

  @Override
  public Tuple<?> visitVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx) {
    String name = ctx.Identifier().getText();
    if (ctx.dims() != null) {
      ArrayType type = visitDims(ctx.dims());
      return Tuple2.tuple(name, type);
    }
    return Tuple1.tuple(name);
  }

  @Override
  public Expression<?> visitVariableInitializer(ProcessJParser.VariableInitializerContext ctx) {
    return null;
  }

  @Override
  public ProtocolDecl visitProtocolDeclarationSemi(ProcessJParser.ProtocolDeclarationSemiContext ctx) {
    int modifiers = Opcodes.ACC_PRIVATE;
    if (ctx.modifier() != null) {
      modifiers -= Opcodes.ACC_PRIVATE;
      for (int i = 0; i < ctx.modifier().size(); ++i) {
        modifiers += visitModifier(ctx.modifier(i));
      }
    }
    String name = ctx.Identifier().getText();
    Sequence<TopLevelDeclaration<?>> implementedNames = Sequence.sequenceList();
    if (ctx.extends_() != null) {
      Sequence<Name> _extends = visitExtends(ctx.extends_());
      for (Name n : _extends) {
        TypeVariableDecl typeDecl = configureNode(new TypeVariableDecl(), n);
        typeDecl.setQualifier(n);
        implementedNames.add(typeDecl);
      }
    }
    ProtocolDecl protocolDecl = new ProtocolDecl();
    protocolDecl.setModifiers(modifiers);
    protocolDecl.setName(name);
    protocolDecl.setImplementedNames(implementedNames);
    return configureNode(protocolDecl, ctx);
  }

  @Override
  public ProtocolDecl visitProtocolDeclarationWithBody(ProcessJParser.ProtocolDeclarationWithBodyContext ctx) {
    int modifiers = Opcodes.ACC_PRIVATE;
    if (ctx.modifier() != null) {
      modifiers -= Opcodes.ACC_PRIVATE;
      for (int i = 0; i < ctx.modifier().size(); ++i) {
        modifiers += visitModifier(ctx.modifier(i));
      }
    }
    String name = ctx.Identifier().getText();
    Sequence<TopLevelDeclaration<?>> names = Sequence.sequenceList();
    if (ctx.extends_() != null) {
      Sequence<Name> _extends = visitExtends(ctx.extends_());
      for (Name n : _extends) {
        TypeVariableDecl typeDecl = configureNode(new TypeVariableDecl(), n);
        typeDecl.setQualifier(n);
        names.add(typeDecl);
      }
    }
    Sequence<ProtocolTagDecl> tags = visitProtocolBody(ctx.protocolBody());
    ProtocolDecl protocolTopLevel = new ProtocolDecl();
    protocolTopLevel.setModifiers(modifiers);
    protocolTopLevel.setName(name);
    protocolTopLevel.setImplementedNames(names);
    protocolTopLevel.setDeclaredTags(tags);
    return configureNode(protocolTopLevel, ctx);
  }

  @Override
  public Sequence<ConstantDecl> visitConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx) {
    int modifiers = Opcodes.ACC_PRIVATE;
    if (ctx.modifier() != null) {
      modifiers -= Opcodes.ACC_PRIVATE;
      for (int i = 0; i < ctx.modifier().size(); ++i) {
        modifiers += visitModifier(ctx.modifier(i));
      }
    }
    ASTType type = visitType_(ctx.type_());
    Sequence<ConstantDecl> constantTopLevels = Sequence.sequenceList();
    Sequence<VariableDecl> variables = visitVariableDeclaratorList(ctx.variableDeclaratorList());
    for (VariableDecl v : variables) {
      if (v.getASTType() != null) {
        ArrayNode arrayNode = v.getASTType().asArrayNode();
        arrayNode.getTSType().setTSType(type.getTSType());
        v.setASTType(arrayNode);
      } else {
        v.setASTType(type);
      }
      ConstantDecl constantDecl = new ConstantDecl();
      constantDecl.setModifiers(modifiers);
      constantDecl.setASTType(v.getASTType());
      constantDecl.setName(v.getName());
      constantDecl.setRightExpression(v.getRightExpression());
      constantTopLevels.add(configureNode(constantDecl, ctx));
    }
    return constantTopLevels;
  }

  @Override
  public Sequence<ProtocolTagDecl> visitProtocolBody(ProcessJParser.ProtocolBodyContext ctx) {
    Sequence<ProtocolTagDecl> tags = Sequence.sequenceList();
    ctx.protocolCase().forEach(t -> tags.add(visitProtocolCase(t)));
    return tags;
  }

  @Override
  public ProtocolTagDecl visitProtocolCase(ProcessJParser.ProtocolCaseContext ctx) {
    String name = ctx.Identifier().getText();
    Sequence<FieldDeclaration> fields = visitRecordBody(ctx.recordBody());
    ProtocolTagDecl protocolTag = new ProtocolTagDecl(name, fields);
    return configureNode(protocolTag, ctx);
  }

  @Override
  public Statement visitStatement(ProcessJParser.StatementContext ctx) {
    if (ctx.localVariableDeclarationStatement() != null) {
      //
    } else if (ctx.expressionStatement() != null) {
      //
    }
    return configureNode(new EmptyStmt(), ctx);
  }

  @Override
  public Object visitLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx) {
    return null;
  }

  @Override
  public Object visitLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx) {
    ASTType type = visitType_(ctx.type_());
    Sequence<VariableDecl> variables = visitVariableDeclaratorList(ctx.variableDeclaratorList());
    return null;
  }
}
