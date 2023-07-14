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
 * Visitor used to compute the hash value of each node in the AST.
 *
 * @author Ben
 */
public class HashcodeVisitor implements DefaultVisitor<Integer> {

  private static HashcodeVisitor INSTANCE = new HashcodeVisitor();

  private HashcodeVisitor() {
  }

  public static int hashCode(final Node node) {
    return node.accept(INSTANCE);
  }

  @Override
  public Integer visit(final AnnotationNode a) {
    return null;
  }

  @Override
  public Integer visit(final ArrayDimension a) {
    return (a.getDimension().isPresent() ? a.getDimension().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ClassNode c) {
    return null;
  }

  @Override
  public Integer visit(final CompileUnit c) {
    return null;
  }

  @Override
  public Integer visit(final Import i) {
    return null;
  }

  @Override
  public Integer visit(final Name n) {
    return null;
  }

  @Override
  public Integer visit(final Package p) {
    return null;
  }

  @Override
  public Integer visit(final Parameter p) {
    return null;
  }

  @Override
  public Integer visit(final Sequence<?> s) {
    int hash = 0;
    for (Object object : s)
      hash += ((CodeVisitor) object).accept(this) * 31;
    return hash;
  }

  @Override
  public Integer visit(final VariableDecl v) {
    return null;
  }

  @Override
  public Integer visit(final ArrayNode a) {
    return null;
  }

  @Override
  public Integer visit(final BarrierNode b) {
    return null;
  }

  @Override
  public Integer visit(final ChannelEndNode c) {
    return null;
  }

  @Override
  public Integer visit(final ChannelNode c) {
    return null;
  }

  @Override
  public Integer visit(final ExternalNode e) {
    return null;
  }

  @Override
  public Integer visit(final PrimitiveNode p) {
    return null;
  }

  @Override
  public Integer visit(final TimerNode t) {
    return null;
  }

  @Override
  public Integer visit(final VoidNode v) {
    return null;
  }

  @Override
  public Integer visit(final ConstructedNode c) {
    return null;
  }

  @Override
  public Integer visit(final ConstantDecl c) {
    return null;
  }

  @Override
  public Integer visit(final ExternalDecl e) {
    return null;
  }

  @Override
  public Integer visit(final CallableDecl<?> m) {
    return null;
  }

  @Override
  public Integer visit(final ProcedureTopLevel p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolTagDecl p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolDecl p) {
    return null;
  }

  @Override
  public Integer visit(final RecordDecl r) {
    return null;
  }

  @Override
  public Integer visit(final TypeVariableDecl t) {
    return null;
  }

  @Override
  public Integer visit(final BlockStmt b) {
    return null;
  }

  @Override
  public Integer visit(final BreakStmt b) {
    return null;
  }

  @Override
  public Integer visit(final CaseStmt c) {
    return null;
  }

  @Override
  public Integer visit(final ContinueStmt c) {
    return null;
  }

  @Override
  public Integer visit(final DoWhileStmt d) {
    return null;
  }

  @Override
  public Integer visit(final EmptyStmt e) {
    return null;
  }

  @Override
  public Integer visit(final ExpressionStmt e) {
    return null;
  }

  @Override
  public Integer visit(final ForEachStmt f) {
    return null;
  }

  @Override
  public Integer visit(final ForStmt f) {
    return null;
  }

  @Override
  public Integer visit(final GuardStmt g) {
    return null;
  }

  @Override
  public Integer visit(final IfStmt i) {
    return null;
  }

  @Override
  public Integer visit(final RegularAltStmt r) {
    return null;
  }

  @Override
  public Integer visit(final ReturnStmt r) {
    return null;
  }

  @Override
  public Integer visit(final SwitchStmt s) {
    return null;
  }

  @Override
  public Integer visit(final WhileStmt w) {
    return null;
  }

  @Override
  public Integer visit(final ReplicatedAltStmt r) {
    return null;
  }

  @Override
  public Integer visit(final ClassDeclaration c) {
    return null;
  }

  @Override
  public Integer visit(final ConstructorDeclaration c) {
    return null;
  }

  @Override
  public Integer visit(final FieldDeclaration f) {
    return null;
  }

  @Override
  public Integer visit(final MethodDeclaration<?> m) {
    return null;
  }

  @Override
  public Integer visit(final ArrayExpr a) {
    return null;
  }

  @Override
  public Integer visit(final BinaryExpr b) {
    return null;
  }

  @Override
  public Integer visit(final BooleanExpr b) {
    return null;
  }

  @Override
  public Integer visit(final BooleanLiteral b) {
    return null;
  }

  @Override
  public Integer visit(final CallableExpr c) {
    return null;
  }

  @Override
  public Integer visit(final CastExpr c) {
    return null;
  }

  @Override
  public Integer visit(final CharLiteral c) {
    return null;
  }

  @Override
  public Integer visit(final ClassExpr c) {
    return null;
  }

  @Override
  public Integer visit(final ConstructorCallExpr c) {
    return null;
  }

  @Override
  public Integer visit(final DeclarationExpr d) {
    return null;
  }

  @Override
  public Integer visit(final DoubleLiteral d) {
    return null;
  }

  @Override
  public Integer visit(final EmptyExpr e) {
    return null;
  }

  @Override
  public Integer visit(final FieldExpr f) {
    return null;
  }

  @Override
  public Integer visit(final IntegerLiteral i) {
    return null;
  }

  @Override
  public Integer visit(final ListExpression<?> l) {
    return null;
  }

  @Override
  public Integer visit(final LongLiteral l) {
    return null;
  }

  @Override
  public Integer visit(final MapEntryExpr m) {
    return null;
  }

  @Override
  public Integer visit(final MapExpr m) {
    return null;
  }

  @Override
  public Integer visit(final MethodCallExpr m) {
    return null;
  }

  @Override
  public Integer visit(final NewArrayExpr n) {
    return null;
  }

  @Override
  public Integer visit(final NotExpression n) {
    return null;
  }

  @Override
  public Integer visit(final NullLiteral n) {
    return null;
  }

  @Override
  public Integer visit(final PostfixExpr p) {
    return null;
  }

  @Override
  public Integer visit(final PrefixExpr p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolExpr p) {
    return null;
  }

  @Override
  public Integer visit(final RecordExpr r) {
    return null;
  }

  @Override
  public Integer visit(final SkipExpr s) {
    return null;
  }

  @Override
  public Integer visit(final StopExpr s) {
    return null;
  }

  @Override
  public Integer visit(final TernaryExpr t) {
    return null;
  }

  @Override
  public Integer visit(final UnaryMinusExpr u) {
    return null;
  }

  @Override
  public Integer visit(final UnaryPlusExpr u) {
    return null;
  }

  @Override
  public Integer visit(final VariableExpr v) {
    return null;
  }

  @Override
  public Integer visit(final FloatLiteral f) {
    return null;
  }

  @Override
  public Integer visit(final StringLiteral s) {
    return null;
  }

  @Override
  public Integer visit(final BlockExpr b) {
    return null;
  }

  @Override
  public Integer visit(final ChannelReadExpr c) {
    return null;
  }

  @Override
  public Integer visit(final ChannelWriteExpr c) {
    return null;
  }

  @Override
  public Integer visit(final BlockComment b) {
    return null;
  }

  @Override
  public Integer visit(final LineComment l) {
    return null;
  }

  @Override
  public Integer visit(final ArrayType a) {
    return null;
  }

  @Override
  public Integer visit(final BarrierType b) {
    return null;
  }

  @Override
  public Integer visit(final ByteType b) {
    return null;
  }

  @Override
  public Integer visit(final ChannelEndType c) {
    return null;
  }

  @Override
  public Integer visit(final ChannelType c) {
    return null;
  }

  @Override
  public Integer visit(final CharType c) {
    return (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final DoubleType d) {
    return (d.getComment().isPresent() ? d.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ExternalType e) {
    return (e.getComment().isPresent() ? e.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final FloatType f) {
    return (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final IntegerType i) {
    return (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final LongType l) {
    return (l.getComment().isPresent() ? l.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final MobileType m) {
    return (m.getComment().isPresent() ? m.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final NullType n) {
    return (n.getComment().isPresent() ? n.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ProcedureType p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolTagType p) {
    return p.getName().hashCode() * 31 +
        (p.getTypeFields().isPresent() ? p.getTypeFields().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ProtocolType p) {
    return p.getName().hashCode() * 31 +
        (p.getTypeFields().isPresent() ? p.getTypeFields().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final RecordType r) {
    return r.getName().hashCode() * 31 +
        (r.getTypeFields().isPresent() ? r.getTypeFields().get().accept(this) : 0) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ShortType s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BooleanType b) {
    return (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final StringType s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TimerType t) {
    return (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final UnknownType u) {
    return (u.getComment().isPresent() ? u.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final VoidType v) {
    return (v.getComment().isPresent() ? v.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TypeVariable t) {
    return (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }
}
