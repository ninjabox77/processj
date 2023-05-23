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
import typesystem.*;

/**
 * Visitor used to compute the hash value of each node
 * in the AST.
 *
 * @author Ben
 */
public class HashcodeVisitor implements DefaultVisitor<Integer> {

  @Override
  public Integer visit(AnnotationNode a) {
    return null;
  }

  @Override
  public Integer visit(ArrayDimension a) {
    return null;
  }

  @Override
  public Integer visit(ClassNode c) {
    return null;
  }

  @Override
  public Integer visit(CompileUnit c) {
    return null;
  }

  @Override
  public Integer visit(Import i) {
    return null;
  }

  @Override
  public Integer visit(Name n) {
    return null;
  }

  @Override
  public Integer visit(Package p) {
    return null;
  }

  @Override
  public Integer visit(Parameter p) {
    return null;
  }

  @Override
  public Integer visit(Sequence<?> s) {
    return null;
  }

  @Override
  public Integer visit(ArrayNode a) {
    return null;
  }

  @Override
  public Integer visit(BarrierNode b) {
    return null;
  }

  @Override
  public Integer visit(ChannelEndNode c) {
    return null;
  }

  @Override
  public Integer visit(ChannelNode c) {
    return null;
  }

  @Override
  public Integer visit(ExternalNode e) {
    return null;
  }

  @Override
  public Integer visit(PrimitiveNode p) {
    return null;
  }

  @Override
  public Integer visit(TimerNode t) {
    return null;
  }

  @Override
  public Integer visit(ConstantTopLevel c) {
    return null;
  }

  @Override
  public Integer visit(ExternalTopLevel e) {
    return null;
  }

  @Override
  public Integer visit(MethodCallableTopLevel<?> m) {
    return null;
  }

  @Override
  public Integer visit(ProcedureTopLevel p) {
    return null;
  }

  @Override
  public Integer visit(ProtocolTagDeclaration p) {
    return null;
  }

  @Override
  public Integer visit(ProtocolTopLevel p) {
    return null;
  }

  @Override
  public Integer visit(RecordTopLevel r) {
    return null;
  }

  @Override
  public Integer visit(BlockStmt b) {
    return null;
  }

  @Override
  public Integer visit(BreakStmt b) {
    return null;
  }

  @Override
  public Integer visit(CaseStmt c) {
    return null;
  }

  @Override
  public Integer visit(ContinueStmt c) {
    return null;
  }

  @Override
  public Integer visit(DoWhileStmt d) {
    return null;
  }

  @Override
  public Integer visit(EmptyStmt e) {
    return null;
  }

  @Override
  public Integer visit(ExpressionStmt e) {
    return null;
  }

  @Override
  public Integer visit(ForEachStmt f) {
    return null;
  }

  @Override
  public Integer visit(ForStmt f) {
    return null;
  }

  @Override
  public Integer visit(GuardStmt g) {
    return null;
  }

  @Override
  public Integer visit(IfStmt i) {
    return null;
  }

  @Override
  public Integer visit(RegularAltStmt r) {
    return null;
  }

  @Override
  public Integer visit(ReturnStmt r) {
    return null;
  }

  @Override
  public Integer visit(SwitchStmt s) {
    return null;
  }

  @Override
  public Integer visit(WhileStmt w) {
    return null;
  }

  @Override
  public Integer visit(ClassDeclaration c) {
    return null;
  }

  @Override
  public Integer visit(ConstructorDeclaration c) {
    return null;
  }

  @Override
  public Integer visit(FieldDeclaration f) {
    return null;
  }

  @Override
  public Integer visit(MethodDeclaration<?> m) {
    return null;
  }

  @Override
  public Integer visit(ArrayExpr a) {
    return null;
  }

  @Override
  public Integer visit(BinaryExpr b) {
    return null;
  }

  @Override
  public Integer visit(BooleanExpr b) {
    return null;
  }

  @Override
  public Integer visit(CallableExpr c) {
    return null;
  }

  @Override
  public Integer visit(CastExpr c) {
    return null;
  }

  @Override
  public Integer visit(CharLiteral c) {
    return null;
  }

  @Override
  public Integer visit(ClassExpr c) {
    return null;
  }

  @Override
  public Integer visit(ConstructorCallExpr c) {
    return null;
  }

  @Override
  public Integer visit(DeclarationExpr d) {
    return null;
  }

  @Override
  public Integer visit(DoubleLiteral d) {
    return null;
  }

  @Override
  public Integer visit(EmptyExpr e) {
    return null;
  }

  @Override
  public Integer visit(FieldAccessExpr f) {
    return null;
  }

  @Override
  public Integer visit(IntegerLiteral i) {
    return null;
  }

  @Override
  public Integer visit(ListExpression<?> l) {
    return null;
  }

  @Override
  public Integer visit(LongLiteral l) {
    return null;
  }

  @Override
  public Integer visit(MapEntryExpr m) {
    return null;
  }

  @Override
  public Integer visit(MapExpr m) {
    return null;
  }

  @Override
  public Integer visit(MethodCallExpr m) {
    return null;
  }

  @Override
  public Integer visit(NewArrayExpr n) {
    return null;
  }

  @Override
  public Integer visit(NotExpression n) {
    return null;
  }

  @Override
  public Integer visit(NullLiteral n) {
    return null;
  }

  @Override
  public Integer visit(PostfixExpr p) {
    return null;
  }

  @Override
  public Integer visit(PrefixExpr p) {
    return null;
  }

  @Override
  public Integer visit(ProtocolExpr p) {
    return null;
  }

  @Override
  public Integer visit(RecordExpr r) {
    return null;
  }

  @Override
  public Integer visit(SkipExpr s) {
    return null;
  }

  @Override
  public Integer visit(StopExpr s) {
    return null;
  }

  @Override
  public Integer visit(TernaryExpr t) {
    return null;
  }

  @Override
  public Integer visit(UnaryMinusExpr u) {
    return null;
  }

  @Override
  public Integer visit(UnaryPlusExpr u) {
    return null;
  }

  @Override
  public Integer visit(BlockComment b) {
    return null;
  }

  @Override
  public Integer visit(LineComment l) {
    return null;
  }

  @Override
  public Integer visit(ArrayType a) {
    return null;
  }

  @Override
  public Integer visit(BarrierType b) {
    return null;
  }

  @Override
  public Integer visit(ByteType b) {
    return null;
  }

  @Override
  public Integer visit(ChannelEndType c) {
    return null;
  }

  @Override
  public Integer visit(ChannelType c) {
    return null;
  }

  @Override
  public Integer visit(CharType c) {
    return null;
  }

  @Override
  public Integer visit(DoubleType d) {
    return null;
  }

  @Override
  public Integer visit(ExternalType e) {
    return null;
  }

  @Override
  public Integer visit(FloatType f) {
    return null;
  }

  @Override
  public Integer visit(IntegerType i) {
    return null;
  }

  @Override
  public Integer visit(LongType l) {
    return null;
  }

  @Override
  public Integer visit(MobileType m) {
    return null;
  }

  @Override
  public Integer visit(NullType n) {
    return null;
  }

  @Override
  public Integer visit(ProcedureType p) {
    return null;
  }

  @Override
  public Integer visit(ProtocolTagType p) {
    return null;
  }

  @Override
  public Integer visit(ProtocolType p) {
    return null;
  }

  @Override
  public Integer visit(RecordType r) {
    return null;
  }

  @Override
  public Integer visit(ShortType s) {
    return null;
  }

  @Override
  public Integer visit(StringType s) {
    return null;
  }

  @Override
  public Integer visit(TimerType t) {
    return null;
  }

  @Override
  public Integer visit(UnknownType u) {
    return null;
  }

  @Override
  public Integer visit(VoidType v) {
    return null;
  }
}
