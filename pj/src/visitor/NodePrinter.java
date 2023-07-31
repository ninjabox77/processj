package visitor;

import ast.*;
import ast.Package;
import ast.comments.BlockComment;
import ast.comments.LineComment;
import ast.expr.*;
import ast.java.ClassDeclaration;
import ast.java.ConstructorDeclaration;
import ast.java.FieldDeclaration;
import ast.java.MethodDeclaration;
import ast.stmt.*;
import ast.toplevel.*;
import ast.types.*;
import control.CompilationUnit;
import typesystem.*;

import java.io.PrintWriter;

public class NodePrinter implements VoidVisitor<Visitor> {

  private int tabLevel_ = 0;
  private final PrintWriter writer_;

  private String tabs(int line) {
    final String TAB = "  ";
    return line + ":" + TAB.repeat(Math.max(0, tabLevel_));
  }

  public NodePrinter(PrintWriter writer) {
    writer_ = writer;
  }

  @Override
  public void visit(final AnnotationNode a, Visitor arg) {
  }

  @Override
  public void visit(final ArrayDimension a, Visitor arg) {
    writer_.print(tabs(a.getLine()) + "ArrayDimension:");
    tabLevel_++;
    a.getDimension().ifPresent(d -> d.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BytecodeAST c, Visitor arg) {
  }

  @Override
  public void visit(final CompileUnit c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "CompileUnit:");
    tabLevel_++;
    if (c.getPackage() != null) {
      c.getPackage().accept(this, arg);
    }
    c.getImports().ifPresent(i -> i.accept(this, arg));
    c.getTypeDeclarations().ifPresent(t -> t.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final CompilationUnit c, Visitor arg) {
  }

  @Override
  public void visit(final Import i, Visitor arg) {
    writer_.println(tabs(i.getLine()) + "Import: " + i.asString());
  }

  @Override
  public void visit(final Name n, Visitor arg) {
    writer_.println(tabs(n.getLine()) + "Name: " + n.asString());
  }

  @Override
  public void visit(final Package p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "Package: " + p.asString());
  }

  @Override
  public void visit(final Parameter p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "Parameter:");
    tabLevel_++;
    writer_.println(tabs(p.getLine()) + "Modifier: " + p.getModifiers());
    p.getASTType().accept(this, arg);
    writer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    tabLevel_--;
  }

  @Override
  public void visit(final Sequence<?> s, Visitor arg) {
    s.forEach(e -> e.accept(this, arg));
  }

  @Override
  public void visit(final VariableDeclarator v, Visitor arg) {
    writer_.println(tabs(v.getLine()) + "VariableDeclarator: ");
    tabLevel_++;
    writer_.println(tabs(v.getLine()) + "Modifier: " + v.getModifiers());
    v.getASTType().accept(this, arg);
    writer_.println(tabs(v.getLine()) + "Identifier: " + v.getName());
    if (v.getRightExpression() != null) {
      writer_.println(tabs(v.getLine()) + "Initializer: ");
      tabLevel_++;
      v.getRightExpression().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final Label l, Visitor arg) {
    writer_.println(tabs(l.getLine()) + "Label: " + l.getIdentifier());
  }

  @Override
  public void visit(final ArrayNode a, Visitor arg) {
    writer_.println(tabs(a.getLine()) + "ArrayNode:");
    tabLevel_++;
    a.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BarrierNode b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BarrierNode:");
    tabLevel_++;
    b.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelEndNode c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "ChannelEndNode:");
    tabLevel_++;
    c.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelNode c, Visitor arg) {
    writer_.print(tabs(c.getLine()) + "ChannelNode:");
    tabLevel_++;
    c.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalNode e, Visitor arg) {

  }

  @Override
  public void visit(final PrimitiveNode p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "PrimitiveNode:");
    tabLevel_++;
    p.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final TimerNode t, Visitor arg) {
    writer_.print(tabs(t.getLine()) + "TimerNode:");
    tabLevel_++;
    t.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final VoidNode v, Visitor arg) {
    writer_.print(tabs(v.getLine()) + "VoidNode:");
    tabLevel_++;
    v.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ConstructedNode c, Visitor arg) {
  }

  @Override
  public void visit(final ErrorNode e, Visitor arg) {
  }

  @Override
  public void visit(final ConstantDeclaration c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "ConstantDeclaration:");
    tabLevel_++;
    writer_.println(tabs(c.getLine()) + "Modifier: " + c.getModifiers());
    c.getASTType().accept(this, arg);
    writer_.println(tabs(c.getLine()) + "Identifier: " + c.getName());
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalDeclaration e, Visitor arg) {
  }

  @Override
  public void visit(final CallableDeclaration<?> m, Visitor arg) {
  }

  @Override
  public void visit(final ProcedureTopLevel p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "ProcedureTopLevel:");
    tabLevel_++;
    writer_.println(tabs(p.getLine()) + "Modifier: " + p.getModifiers());
    writer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    writer_.println(tabs(p.getLine()) + "Yields: " + p.doesYield());
    tabLevel_++;
    if (p.getParameters() != null) {
      p.getParameters().forEach(v -> v.accept(this, arg));
    }
    if (p.getBody() != null) {
      p.getBody().accept(this, arg);
    }
    tabLevel_--;
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolCaseDeclaration p, Visitor arg) {

  }

  @Override
  public void visit(final ProtocolDeclaration p, Visitor arg) {

  }

  @Override
  public void visit(final RecordDeclaration r, Visitor arg) {
    writer_.println(tabs(r.getLine()) + "RecordDeclaration:");
    tabLevel_++;
    writer_.println(tabs(r.getLine()) + "Modifier: " + r.getModifiers());
    writer_.println(tabs(r.getLine()) + "Identifier: " + r.getName());
    r.getDeclaredFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BlockStatement b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BlockStatement:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final BreakStatement b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "Statement: break");
  }

  @Override
  public void visit(final SwitchCaseStatement c, Visitor arg) {

  }

  @Override
  public void visit(final ContinueStatement c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "ContinueStatement");
  }

  @Override
  public void visit(final SkipStatement s, Visitor arg) {
    writer_.println(tabs(s.getLine()) + "SkipStatement");
  }

  @Override
  public void visit(final StopStatement s, Visitor arg) {
    writer_.println(tabs(s.getLine()) + "StopStatement");
  }

  @Override
  public void visit(final DoWhileStatement d, Visitor arg) {
    writer_.println(tabs(d.getLine()) + "DoWhileStatement:");
    tabLevel_++;
    d.getLoopBlock().ifPresent(l -> l.accept(this, arg));
    writer_.println(tabs(d.getLine()) + "Conditional:");
    tabLevel_++;
    d.getCondition().accept(this, arg);
    tabLevel_--;
    tabLevel_--;
  }

  @Override
  public void visit(final EmptyStatement e, Visitor arg) {
    writer_.println(tabs(e.getLine()) + "EmptyStatement");
  }

  @Override
  public void visit(final ExpressionStatement e, Visitor arg) {
    writer_.println(tabs(e.getLine()) + "ExpressionStatement:");
    tabLevel_++;
    e.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ForEachStatement f, Visitor arg) {

  }

  @Override
  public void visit(final ForStatement f, Visitor arg) {

  }

  @Override
  public void visit(final Guard g, Visitor arg) {

  }

  @Override
  public void visit(final IfStatement i, Visitor arg) {
    writer_.println(tabs(i.getLine()) + "IfStatement:");
    tabLevel_++;
    writer_.println(tabs(i.getLine()) + "Conditional:");
    tabLevel_++;
    i.getConditional().accept(this, arg);
    tabLevel_--;
    writer_.println(tabs(i.getLine()) + "If-then:");
    tabLevel_++;
    i.getIfPart().accept(this, arg);
    tabLevel_--;
    if (i.getElsePart().isPresent()) {
      writer_.println(tabs(i.getLine()) + "Else-then:");
      tabLevel_++;
      i.getElsePart().get().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final AltBlock r, Visitor arg) {

  }

  @Override
  public void visit(final ReturnStatement r, Visitor arg) {
    writer_.println(tabs(r.getLine()) + "ReturnStatement: ");
    tabLevel_++;
    r.getExpression().ifPresent(e -> e.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final SwitchStatement s, Visitor arg) {

  }

  @Override
  public void visit(final WhileStatement w, Visitor arg) {

  }

  @Override
  public void visit(final ReplicatedAltBlock r, Visitor arg) {

  }

  @Override
  public void visit(final AltCaseStatement a, Visitor arg) {

  }

  @Override
  public void visit(final ParBlock r, Visitor arg) {

  }

  @Override
  public void visit(final ParForBlock p, Visitor arg) {

  }

  @Override
  public void visit(final LabelStatement l, Visitor arg) {

  }

  @Override
  public void visit(final ClassDeclaration c, Visitor arg) {

  }

  @Override
  public void visit(final ConstructorDeclaration c, Visitor arg) {

  }

  @Override
  public void visit(final FieldDeclaration f, Visitor arg) {
    writer_.println(tabs(f.getLine()) + "FieldDeclaration:");
    tabLevel_++;
    f.getVariable().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MethodDeclaration<?> m, Visitor arg) {

  }

  @Override
  public void visit(final ArrayInitializer a, Visitor arg) {

  }

  @Override
  public void visit(final AssignmentExpression a, Visitor arg) {
    writer_.println(tabs(a.getLine()) + "AssignmentExpression:");
    tabLevel_++;
    writer_.println(tabs(a.getLine()) + "Operator: =");
    a.getLeftExpression().accept(this, arg);
    a.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BinaryExpression b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BinaryExpression:");
    tabLevel_++;
    writer_.println(tabs(b.getLine()) + "Operator: " + b.getOperator());
    b.getLeftExpression().accept(this, arg);
    b.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanExpression b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BooleanExpression:");
    tabLevel_++;
    b.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanLiteral b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BooleanLiteral: " + b.getValue());
  }

  @Override
  public void visit(final CallableExpression c, Visitor arg) {

  }

  @Override
  public void visit(final CastExpression c, Visitor arg) {

  }

  @Override
  public void visit(final CharacterLiteral c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "CharacterLiteral: " + c.getValue());
  }

  @Override
  public void visit(final ClassExpression c, Visitor arg) {

  }

  @Override
  public void visit(final ObjectCreationExpr c, Visitor arg) {

  }

  @Override
  public void visit(final DeclarationExpression d, Visitor arg) {
    writer_.println(tabs(d.getLine()) + "DeclarationExpression:");
    tabLevel_++;
    d.getDeclaration().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final DoubleLiteral d, Visitor arg) {
    writer_.println(tabs(d.getLine()) + "DoubleLiteral: " + d.getValue());
  }

  @Override
  public void visit(final EmptyExpression e, Visitor arg) {

  }

  @Override
  public void visit(final FieldExpression f, Visitor arg) {

  }

  @Override
  public void visit(final IntegerLiteral i, Visitor arg) {
    writer_.println(tabs(i.getLine()) + "IntegerLiteral: " + i.getValue());
  }

  @Override
  public void visit(final ListExpression<?> l, Visitor arg) {

  }

  @Override
  public void visit(final LongLiteral l, Visitor arg) {
    writer_.println(tabs(l.getLine()) + "LongLiteral: " + l.getValue());
  }

  @Override
  public void visit(final MapEntryExpression m, Visitor arg) {

  }

  @Override
  public void visit(final MapExpression m, Visitor arg) {

  }

  @Override
  public void visit(final MethodCallExpression m, Visitor arg) {

  }

  @Override
  public void visit(final NewArrayExpression n, Visitor arg) {

  }

  @Override
  public void visit(final NotExpression n, Visitor arg) {

  }

  @Override
  public void visit(final NullLiteral n, Visitor arg) {

  }

  @Override
  public void visit(final PostfixExpression p, Visitor arg) {

  }

  @Override
  public void visit(final PrefixExpression p, Visitor arg) {
  }

  @Override
  public void visit(final SkipExpression s, Visitor arg) {

  }

  @Override
  public void visit(final StopExpression s, Visitor arg) {

  }

  @Override
  public void visit(final TernaryExpression t, Visitor arg) {

  }

  @Override
  public void visit(final UnaryMinusExpression u, Visitor arg) {

  }

  @Override
  public void visit(final UnaryPlusExpression u, Visitor arg) {

  }

  @Override
  public void visit(final VariableExpression v, Visitor arg) {
    writer_.println(tabs(v.getLine()) + "Variable: " + v.getName());
  }

  @Override
  public void visit(final FloatLiteral f, Visitor arg) {
    writer_.println(tabs(f.getLine()) + "<float literal>");
    tabLevel_++;
    writer_.println(tabs(f.getLine()) + "\"" + f.getValue() + "\"");
    tabLevel_--;
    writer_.println(tabs(f.getLine()) + "</float literal>");
  }

  @Override
  public void visit(final StringLiteral s, Visitor arg) {
    writer_.println(tabs(s.getLine()) + "StringLiteral: " + s.getValue());
  }

  @Override
  public void visit(final BlockExpression b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BlockExpression:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelReadExpression c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "<channel-read expression>");
    tabLevel_++;
    writer_.print(tabs(c.getLine()) + "<channel>");
    c.getChannel().accept(this, arg);
    writer_.print(tabs(c.getLine()) + "</channel>");
    if (c.getExtendedRV() != null) {
      writer_.print(tabs(c.getLine()) + "<extended rendezvous>");
      c.getExtendedRV().accept(this, arg);
      writer_.print(tabs(c.getLine()) + "</extended rendezvous>");
    }
    tabLevel_--;
    writer_.println(tabs(c.getLine()) + "</channel-read expression>");
  }

  @Override
  public void visit(final ChannelWriteExpression c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "<channel-write expression>");
    tabLevel_++;
    writer_.print(tabs(c.getLine()) + "<channel>");
    c.getChannel().accept(this, arg);
    writer_.print(tabs(c.getLine()) + "</channel>");
    writer_.print(tabs(c.getLine()) + "<expression>");
    c.getExpression().accept(this, arg);
    writer_.print(tabs(c.getLine()) + "</expression>");
    tabLevel_--;
    writer_.println(tabs(c.getLine()) + "</channel-write expression>");
  }

  @Override
  public void visit(final GroupExpression g, Visitor arg) {
    writer_.println(tabs(g.getLine()) + "GroupExpression:");
    tabLevel_++;
    g.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ArrayAccess a, Visitor arg) {
    writer_.println(tabs(a.getLine()) + "ArrayAccess:");
    tabLevel_++;
    a.getName().accept(this, arg);
    a.getIndex().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final RecordLiteral r, Visitor arg) {
    writer_.println(tabs(r.getLine()) + "RecordLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    if (r.getMembers() != null) {
      r.getMembers().forEach(m -> m.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final RecordMemberLiteral r, Visitor arg) {
    writer_.println(tabs(r.getLine()) + "RecordMemberLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    r.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolLiteral p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "ProtocolLiteral");
    tabLevel_++;
    p.getName().accept(this, arg);
    p.getTag().accept(this, arg);
    if (p.getMembers() != null) {
      p.getMembers().forEach(m -> m.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final BlockComment b, Visitor arg) {
  }

  @Override
  public void visit(final LineComment l, Visitor arg) {
  }

  @Override
  public void visit(final ArrayType a, Visitor arg) {
    writer_.println(tabs(a.getLine()) + "ArrayType: " + a.asString());
    tabLevel_++;
    a.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BarrierType b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BarrierType: " + b.asString());
  }

  @Override
  public void visit(final ByteType b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "ByteType: " + b.asString());
  }

  @Override
  public void visit(final ChannelEndType c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "ChannelEndType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelType c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "ChannelType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CharType c, Visitor arg) {
    writer_.println(tabs(c.getLine()) + "CharType: char");
  }

  @Override
  public void visit(final DoubleType d, Visitor arg) {
    writer_.println(tabs(d.getLine()) + "DoubleType: double");
  }

  @Override
  public void visit(final ExternalType e, Visitor arg) {
  }

  @Override
  public void visit(final FloatType f, Visitor arg) {
    writer_.println(tabs(f.getLine()) + "FloatType: " + f.asString());
  }

  @Override
  public void visit(final IntegerType i, Visitor arg) {
    writer_.println(tabs(i.getLine()) + "IntegerType: " + i.asString());
  }

  @Override
  public void visit(final LongType l, Visitor arg) {
    writer_.println(tabs(l.getLine()) + "LongType: " + l.asString());
  }

  @Override
  public void visit(final MobileType m, Visitor arg) {
  }

  @Override
  public void visit(final NullType n, Visitor arg) {
    writer_.println(tabs(n.getLine()) + "NullType: " + n.asString());
  }

  @Override
  public void visit(final ProcedureType p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "ProcedureType: " + p.asString());
    tabLevel_++;
    p.getTSType().accept(this, arg);
    p.getParameterTypes().ifPresent(t -> t.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolTagType p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "ProtocolTagType: ");
    tabLevel_++;
    p.getTypeFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolType p, Visitor arg) {
    writer_.println(tabs(p.getLine()) + "ProtocolType: " + p.getName());
  }

  @Override
  public void visit(final RecordType r, Visitor arg) {
    writer_.println(tabs(r.getLine()) + "RecordType: " + r.getName());
  }

  @Override
  public void visit(final ShortType s, Visitor arg) {
    writer_.println(tabs(s.getLine()) + "ShortType: short");
  }

  @Override
  public void visit(final BooleanType b, Visitor arg) {
    writer_.println(tabs(b.getLine()) + "BooleanType: boolean");
  }

  @Override
  public void visit(final StringType s, Visitor arg) {
    writer_.println(tabs(s.getLine()) + "StringType: string");
  }

  @Override
  public void visit(final TimerType t, Visitor arg) {
    writer_.println(tabs(t.getLine()) + "TimerType: timer");
  }

  @Override
  public void visit(final UnknownType u, Visitor arg) {
    writer_.println(tabs(u.getLine()) + "UnknownType: ?");
  }

  @Override
  public void visit(final VoidType v, Visitor arg) {
    writer_.println(tabs(v.getLine()) + "VoidType: void");
  }

  @Override
  public void visit(final NamedType t, Visitor arg) {
    writer_.println(tabs(t.getLine()) + "TypeVariable:");
    tabLevel_++;
    t.getName().accept(this, arg);
    tabLevel_--;
  }
}
