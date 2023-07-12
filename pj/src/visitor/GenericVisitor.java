package visitor;

import ast.*;
import ast.Package;
import ast.comments.BlockComment;
import ast.comments.LineComment;
import ast.expr.*;
import ast.java.*;
import ast.stmt.*;
import ast.toplevel.*;
import ast.types.*;
import typesystem.*;

/**
 * Generic visitor.
 *
 * @author Ben
 */
public interface GenericVisitor<R, A> {

  /*********************************************************
   * Ast nodes
   *********************************************************/

  R visit(final AnnotationNode a, A arg);

  R visit(final ArrayDimension a, A arg);

  R visit(final ClassNode c, A arg);

  R visit(final CompileUnit c, A arg);

  R visit(final Import i, A arg);

  R visit(final Name n, A arg);

  R visit(final Package p, A arg);

  R visit(final Parameter p, A arg);

  R visit(final Sequence<?> s, A arg);

  /*********************************************************
   * Type nodes
   *********************************************************/

  R visit(final ArrayNode a, A arg);

  R visit(final BarrierNode b, A arg);

  R visit(final ChannelEndNode c, A arg);

  R visit(final ChannelNode c, A arg);

  R visit(final ExternalNode e, A arg);

  R visit(final PrimitiveNode p, A arg);

  R visit(final TimerNode t, A arg);

  R visit(final VoidNode v, A arg);

  R visit(final ConstructedNode c, A arg);

  /*********************************************************
   * Top-level nodes
   *********************************************************/

  R visit(final ConstantTopLevel c, A arg);

  R visit(final ExternalTopLevel e, A arg);

  R visit(final CallableTopLevel<?> m, A arg);

  R visit(final ProcedureTopLevel p, A arg);

  R visit(final ProtocolTagDeclaration p, A arg);

  R visit(final ProtocolTopLevel p, A arg);

  R visit(final RecordTopLevel r, A arg);

  R visit(final TypeDeclarationTopLevel t, A arg);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  R visit(final BlockStmt b, A arg);

  R visit(final BreakStmt b, A arg);

  R visit(final CaseStmt c, A arg);

  R visit(final ContinueStmt c, A arg);

  R visit(final DoWhileStmt d, A arg);

  R visit(final EmptyStmt e, A arg);

  R visit(final ExpressionStmt e, A arg);

  R visit(final ForEachStmt f, A arg);

  R visit(final ForStmt f, A arg);

  R visit(final GuardStmt g, A arg);

  R visit(final IfStmt i, A arg);

  R visit(final RegularAltStmt r, A arg);

  R visit(final ReturnStmt r, A arg);

  R visit(final SwitchStmt s, A arg);

  R visit(final WhileStmt w, A arg);

  R visit(final ReplicatedAltStmt r, A arg);

  /*********************************************************
   * Java nodes
   *********************************************************/

  R visit(final ClassDeclaration c, A arg);

  R visit(final ConstructorDeclaration c, A arg);

  R visit(final FieldDeclaration f, A arg);

  R visit(final MethodDeclaration<?> m, A arg);

  /*********************************************************
   * Expression nodes
   *********************************************************/

  R visit(final ArrayExpr a, A arg);

  R visit(final BinaryExpr b, A arg);

  R visit(final BooleanExpr b, A arg);

  R visit(final BooleanLiteral b, A arg);

  R visit(final CallableExpr c, A arg);

  R visit(final CastExpr c, A arg);

  R visit(final CharLiteral c, A arg);

  R visit(final ClassExpr c, A arg);

  R visit(final ConstructorCallExpr c, A arg);

  R visit(final DeclarationExpr d, A arg);

  R visit(final DoubleLiteral d, A arg);

  R visit(final EmptyExpr e, A arg);

  R visit(final FieldExpr f, A arg);

  R visit(final IntegerLiteral i, A arg);

  R visit(final ListExpression<?> l, A arg);

  R visit(final LongLiteral l, A arg);

  R visit(final MapEntryExpr m, A arg);

  R visit(final MapExpr m, A arg);

  R visit(final MethodCallExpr m, A arg);

  R visit(final NewArrayExpr n, A arg);

  R visit(final NotExpression n, A arg);

  R visit(final NullLiteral n, A arg);

  R visit(final PostfixExpr p, A arg);

  R visit(final PrefixExpr p, A arg);

  R visit(final ProtocolExpr p, A arg);

  R visit(final RecordExpr r, A arg);

  R visit(final SkipExpr s, A arg);

  R visit(final StopExpr s, A arg);

  R visit(final TernaryExpr t, A arg);

  R visit(final UnaryMinusExpr u, A arg);

  R visit(final UnaryPlusExpr u, A arg);

  R visit(final VariableExpr v, A arg);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  R visit(final BlockComment b, A arg);

  R visit(final LineComment l, A arg);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  R visit(final ArrayType a, A arg);

  R visit(final BarrierType b, A arg);

  R visit(final ByteType b, A arg);

  R visit(final ChannelEndType c, A arg);

  R visit(final ChannelType c, A arg);

  R visit(final CharType c, A arg);

  R visit(final DoubleType d, A arg);

  R visit(final ExternalType e, A arg);

  R visit(final FloatType f, A arg);

  R visit(final IntegerType i, A arg);

  R visit(final LongType l, A arg);

  R visit(final MobileType m, A arg);

  R visit(final NullType n, A arg);

  R visit(final ProcedureType p, A arg);

  R visit(final ProtocolTagType p, A arg);

  R visit(final ProtocolType p, A arg);

  R visit(final RecordType r, A arg);

  R visit(final ShortType s, A arg);

  R visit(final BooleanType b, A arg);

  R visit(final StringType s, A arg);

  R visit(final TimerType t, A arg);

  R visit(final UnknownType u, A arg);

  R visit(final VoidType v, A arg);

  R visit(final TypeVariable t, A arg);
}
