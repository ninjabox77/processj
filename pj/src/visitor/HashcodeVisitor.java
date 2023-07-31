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

/**
 * Visitor used to compute the hash value of each node in the AST.
 *
 * @author Ben
 */
public class HashcodeVisitor implements DefaultVisitor<Integer> {

  private static HashcodeVisitor INSTANCE = new HashcodeVisitor();

  private HashcodeVisitor() {
  }

  public static int hashCode(final SourceAST node) {
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
  public Integer visit(final BytecodeAST c) {
    return null;
  }

  @Override
  public Integer visit(final CompileUnit c) {
    return null;
  }

  @Override
  public Integer visit(final CompilationUnit c) {
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
      hash += ((Visitor) object).accept(this) * 31;
    return hash;
  }

  @Override
  public Integer visit(final VariableDeclarator v) {
    return null;
  }

  @Override
  public Integer visit(final Label s) {
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
  public Integer visit(final ErrorNode e) {
    return null;
  }

  @Override
  public Integer visit(final ConstantDeclaration c) {
    return null;
  }

  @Override
  public Integer visit(final ExternalDeclaration e) {
    return null;
  }

  @Override
  public Integer visit(final CallableDeclaration<?> m) {
    return null;
  }

  @Override
  public Integer visit(final ProcedureTopLevel p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolCaseDeclaration p) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolDeclaration p) {
    return null;
  }

  @Override
  public Integer visit(final RecordDeclaration r) {
    return null;
  }

  @Override
  public Integer visit(final BlockStatement b) {
    return null;
  }

  @Override
  public Integer visit(final BreakStatement b) {
    return null;
  }

  @Override
  public Integer visit(final SwitchCaseStatement c) {
    return null;
  }

  @Override
  public Integer visit(final ContinueStatement c) {
    return null;
  }

  @Override
  public Integer visit(final SkipStatement s) {
    return null;
  }

  @Override
  public Integer visit(final StopStatement s) {
    return null;
  }

  @Override
  public Integer visit(final DoWhileStatement d) {
    return null;
  }

  @Override
  public Integer visit(final EmptyStatement e) {
    return null;
  }

  @Override
  public Integer visit(final ExpressionStatement e) {
    return null;
  }

  @Override
  public Integer visit(final ForEachStatement f) {
    return null;
  }

  @Override
  public Integer visit(final ForStatement f) {
    return null;
  }

  @Override
  public Integer visit(final Guard g) {
    return null;
  }

  @Override
  public Integer visit(final IfStatement i) {
    return null;
  }

  @Override
  public Integer visit(final AltBlock r) {
    return null;
  }

  @Override
  public Integer visit(final ReturnStatement r) {
    return null;
  }

  @Override
  public Integer visit(final SwitchStatement s) {
    return null;
  }

  @Override
  public Integer visit(final WhileStatement w) {
    return null;
  }

  @Override
  public Integer visit(final ReplicatedAltBlock r) {
    return null;
  }

  @Override
  public Integer visit(final AltCaseStatement a) {
    return null;
  }

  @Override
  public Integer visit(final ParBlock r) {
    return null;
  }

  @Override
  public Integer visit(final ParForBlock p) {
    return null;
  }

  @Override
  public Integer visit(final LabelStatement l) {
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
  public Integer visit(final ArrayInitializer a) {
    return null;
  }

  @Override
  public Integer visit(final AssignmentExpression a) {
    return null;
  }

  @Override
  public Integer visit(final BinaryExpression b) {
    return null;
  }

  @Override
  public Integer visit(final BooleanExpression b) {
    return null;
  }

  @Override
  public Integer visit(final BooleanLiteral b) {
    return null;
  }

  @Override
  public Integer visit(final CallableExpression c) {
    return null;
  }

  @Override
  public Integer visit(final CastExpression c) {
    return null;
  }

  @Override
  public Integer visit(final CharacterLiteral c) {
    return null;
  }

  @Override
  public Integer visit(final ClassExpression c) {
    return null;
  }

  @Override
  public Integer visit(final ObjectCreationExpr c) {
    return null;
  }

  @Override
  public Integer visit(final DeclarationExpression d) {
    return null;
  }

  @Override
  public Integer visit(final DoubleLiteral d) {
    return null;
  }

  @Override
  public Integer visit(final EmptyExpression e) {
    return null;
  }

  @Override
  public Integer visit(final FieldExpression f) {
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
  public Integer visit(final MapEntryExpression m) {
    return null;
  }

  @Override
  public Integer visit(final MapExpression m) {
    return null;
  }

  @Override
  public Integer visit(final MethodCallExpression m) {
    return null;
  }

  @Override
  public Integer visit(final NewArrayExpression n) {
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
  public Integer visit(final PostfixExpression p) {
    return null;
  }

  @Override
  public Integer visit(final PrefixExpression p) {
    return null;
  }

  @Override
  public Integer visit(final SkipExpression s) {
    return null;
  }

  @Override
  public Integer visit(final StopExpression s) {
    return null;
  }

  @Override
  public Integer visit(final TernaryExpression t) {
    return null;
  }

  @Override
  public Integer visit(final UnaryMinusExpression u) {
    return null;
  }

  @Override
  public Integer visit(final UnaryPlusExpression u) {
    return null;
  }

  @Override
  public Integer visit(final VariableExpression v) {
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
  public Integer visit(final BlockExpression b) {
    return null;
  }

  @Override
  public Integer visit(final ChannelReadExpression c) {
    return null;
  }

  @Override
  public Integer visit(final ChannelWriteExpression c) {
    return null;
  }

  @Override
  public Integer visit(final GroupExpression g) {
    return null;
  }

  @Override
  public Integer visit(final ArrayAccess a) {
    return null;
  }

  @Override
  public Integer visit(final RecordLiteral r) {
    return null;
  }

  @Override
  public Integer visit(final RecordMemberLiteral r) {
    return null;
  }

  @Override
  public Integer visit(final ProtocolLiteral p) {
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
  public Integer visit(final NamedType t) {
    return (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }
}
