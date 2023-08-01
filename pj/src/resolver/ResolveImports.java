package resolver;

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
import visitor.Visitor;
import visitor.VoidVisitor;

/**
 * Visitor to resolve constants and procedure calls from imports.
 *
 * @author Ben
 */
public class ResolveImports implements VoidVisitor<Visitor> {

  @Override
  public void visit(AnnotationNode a, Visitor arg) {

  }

  @Override
  public void visit(ArrayDimension a, Visitor arg) {

  }

  @Override
  public void visit(BytecodeAST c, Visitor arg) {

  }

  @Override
  public void visit(Compilation c, Visitor arg) {

  }

  @Override
  public void visit(CompilationUnit c, Visitor arg) {

  }

  @Override
  public void visit(Import i, Visitor arg) {

  }

  @Override
  public void visit(Name n, Visitor arg) {

  }

  @Override
  public void visit(Package p, Visitor arg) {

  }

  @Override
  public void visit(Parameter p, Visitor arg) {

  }

  @Override
  public void visit(Sequence<?> s, Visitor arg) {

  }

  @Override
  public void visit(VariableDeclarator v, Visitor arg) {

  }

  @Override
  public void visit(Label s, Visitor arg) {

  }

  @Override
  public void visit(ArrayNode a, Visitor arg) {

  }

  @Override
  public void visit(BarrierNode b, Visitor arg) {

  }

  @Override
  public void visit(ChannelEndNode c, Visitor arg) {

  }

  @Override
  public void visit(ChannelNode c, Visitor arg) {

  }

  @Override
  public void visit(ExternalNode e, Visitor arg) {

  }

  @Override
  public void visit(PrimitiveNode p, Visitor arg) {

  }

  @Override
  public void visit(TimerNode t, Visitor arg) {

  }

  @Override
  public void visit(VoidNode v, Visitor arg) {

  }

  @Override
  public void visit(ConstructedNode c, Visitor arg) {

  }

  @Override
  public void visit(ErrorNode e, Visitor arg) {

  }

  @Override
  public void visit(ConstantDeclaration c, Visitor arg) {

  }

  @Override
  public void visit(ExternalDeclaration e, Visitor arg) {

  }

  @Override
  public void visit(ProcedureDeclaration p, Visitor arg) {

  }

  @Override
  public void visit(ProtocolCaseDeclaration p, Visitor arg) {

  }

  @Override
  public void visit(ProtocolDeclaration p, Visitor arg) {

  }

  @Override
  public void visit(RecordDeclaration r, Visitor arg) {

  }

  @Override
  public void visit(BlockStatement b, Visitor arg) {

  }

  @Override
  public void visit(SequentialBlock s, Visitor arg) {

  }

  @Override
  public void visit(BreakStatement b, Visitor arg) {

  }

  @Override
  public void visit(SwitchCaseStatement c, Visitor arg) {

  }

  @Override
  public void visit(ContinueStatement c, Visitor arg) {

  }

  @Override
  public void visit(SkipStatement s, Visitor arg) {

  }

  @Override
  public void visit(StopStatement s, Visitor arg) {

  }

  @Override
  public void visit(DoWhileStatement d, Visitor arg) {

  }

  @Override
  public void visit(EmptyStatement e, Visitor arg) {

  }

  @Override
  public void visit(ExpressionStatement e, Visitor arg) {

  }

  @Override
  public void visit(ForEachStatement f, Visitor arg) {

  }

  @Override
  public void visit(ForStatement f, Visitor arg) {

  }

  @Override
  public void visit(Guard g, Visitor arg) {

  }

  @Override
  public void visit(IfStatement i, Visitor arg) {

  }

  @Override
  public void visit(AltBlock r, Visitor arg) {

  }

  @Override
  public void visit(ReturnStatement r, Visitor arg) {

  }

  @Override
  public void visit(SwitchStatement s, Visitor arg) {

  }

  @Override
  public void visit(WhileStatement w, Visitor arg) {

  }

  @Override
  public void visit(ReplicatedAltBlock r, Visitor arg) {

  }

  @Override
  public void visit(AltCaseStatement a, Visitor arg) {

  }

  @Override
  public void visit(ParBlock r, Visitor arg) {

  }

  @Override
  public void visit(ParForBlock p, Visitor arg) {

  }

  @Override
  public void visit(LabelStatement l, Visitor arg) {

  }

  @Override
  public void visit(ClassDeclaration c, Visitor arg) {

  }

  @Override
  public void visit(ConstructorDeclaration c, Visitor arg) {

  }

  @Override
  public void visit(FieldDeclaration f, Visitor arg) {

  }

  @Override
  public void visit(MethodDeclaration<?> m, Visitor arg) {

  }

  @Override
  public void visit(ArrayInitializer a, Visitor arg) {

  }

  @Override
  public void visit(TupleExpression t, Visitor arg) {

  }

  @Override
  public void visit(AssignmentExpression a, Visitor arg) {

  }

  @Override
  public void visit(BinaryExpression b, Visitor arg) {

  }

  @Override
  public void visit(BooleanExpression b, Visitor arg) {

  }

  @Override
  public void visit(BooleanLiteral b, Visitor arg) {

  }

  @Override
  public void visit(Invocation c, Visitor arg) {

  }

  @Override
  public void visit(CastExpression c, Visitor arg) {

  }

  @Override
  public void visit(CharacterLiteral c, Visitor arg) {

  }

  @Override
  public void visit(ClassExpression c, Visitor arg) {

  }

  @Override
  public void visit(ObjectCreationExpr c, Visitor arg) {

  }

  @Override
  public void visit(DeclarationExpression d, Visitor arg) {

  }

  @Override
  public void visit(DoubleLiteral d, Visitor arg) {

  }

  @Override
  public void visit(EmptyExpression e, Visitor arg) {

  }

  @Override
  public void visit(FieldExpression f, Visitor arg) {

  }

  @Override
  public void visit(IntegerLiteral i, Visitor arg) {

  }

  @Override
  public void visit(ListExpression<?> l, Visitor arg) {

  }

  @Override
  public void visit(LongLiteral l, Visitor arg) {

  }

  @Override
  public void visit(MapEntryExpression m, Visitor arg) {

  }

  @Override
  public void visit(MapExpression m, Visitor arg) {

  }

  @Override
  public void visit(MethodCallExpression m, Visitor arg) {

  }

  @Override
  public void visit(NewArrayExpression n, Visitor arg) {

  }

  @Override
  public void visit(NotExpression n, Visitor arg) {

  }

  @Override
  public void visit(NullLiteral n, Visitor arg) {

  }

  @Override
  public void visit(PostfixExpression p, Visitor arg) {

  }

  @Override
  public void visit(PrefixExpression p, Visitor arg) {

  }

  @Override
  public void visit(SkipExpression s, Visitor arg) {

  }

  @Override
  public void visit(StopExpression s, Visitor arg) {

  }

  @Override
  public void visit(TernaryExpression t, Visitor arg) {

  }

  @Override
  public void visit(UnaryMinusExpression u, Visitor arg) {

  }

  @Override
  public void visit(UnaryPlusExpression u, Visitor arg) {

  }

  @Override
  public void visit(VariableExpression v, Visitor arg) {

  }

  @Override
  public void visit(FloatLiteral f, Visitor arg) {

  }

  @Override
  public void visit(StringLiteral s, Visitor arg) {

  }

  @Override
  public void visit(BlockExpression b, Visitor arg) {

  }

  @Override
  public void visit(ChannelReadExpression c, Visitor arg) {

  }

  @Override
  public void visit(ChannelWriteExpression c, Visitor arg) {

  }

  @Override
  public void visit(GroupExpression g, Visitor arg) {

  }

  @Override
  public void visit(ArrayAccess a, Visitor arg) {

  }

  @Override
  public void visit(RecordLiteral r, Visitor arg) {

  }

  @Override
  public void visit(RecordMemberLiteral r, Visitor arg) {

  }

  @Override
  public void visit(ProtocolLiteral p, Visitor arg) {

  }

  @Override
  public void visit(BlockComment b, Visitor arg) {

  }

  @Override
  public void visit(LineComment l, Visitor arg) {

  }

  @Override
  public void visit(ArrayType a, Visitor arg) {

  }

  @Override
  public void visit(BarrierType b, Visitor arg) {

  }

  @Override
  public void visit(ByteType b, Visitor arg) {

  }

  @Override
  public void visit(ChannelEndType c, Visitor arg) {

  }

  @Override
  public void visit(ChannelType c, Visitor arg) {

  }

  @Override
  public void visit(CharType c, Visitor arg) {

  }

  @Override
  public void visit(DoubleType d, Visitor arg) {

  }

  @Override
  public void visit(ExternalType e, Visitor arg) {

  }

  @Override
  public void visit(FloatType f, Visitor arg) {

  }

  @Override
  public void visit(IntegerType i, Visitor arg) {

  }

  @Override
  public void visit(LongType l, Visitor arg) {

  }

  @Override
  public void visit(MobileType m, Visitor arg) {

  }

  @Override
  public void visit(NullType n, Visitor arg) {

  }

  @Override
  public void visit(ProcedureType p, Visitor arg) {

  }

  @Override
  public void visit(ProtocolTagType p, Visitor arg) {

  }

  @Override
  public void visit(ProtocolType p, Visitor arg) {

  }

  @Override
  public void visit(RecordType r, Visitor arg) {

  }

  @Override
  public void visit(ShortType s, Visitor arg) {

  }

  @Override
  public void visit(BooleanType b, Visitor arg) {

  }

  @Override
  public void visit(StringType s, Visitor arg) {

  }

  @Override
  public void visit(TimerType t, Visitor arg) {

  }

  @Override
  public void visit(UnknownType u, Visitor arg) {

  }

  @Override
  public void visit(VoidType v, Visitor arg) {

  }

  @Override
  public void visit(NamedType t, Visitor arg) {

  }
}
