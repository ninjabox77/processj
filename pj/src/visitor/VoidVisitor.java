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

public interface VoidVisitor<A> {

  /*********************************************************
   * Ast nodes
   *********************************************************/

  void visit(final AnnotationNode a, A arg);

  void visit(final ArrayDimension a, A arg);

  void visit(final ClassNode c, A arg);

  void visit(final CompileUnit c, A arg);

  void visit(final Import i, A arg);

  void visit(final Name n, A arg);

  void visit(final Package p, A arg);

  void visit(final Parameter p, A arg);

  void visit(final Sequence<?> s, A arg);

  void visit(final VariableDecl v, A arg);

  /*********************************************************
   * Type nodes
   *********************************************************/

  void visit(final ArrayNode a, A arg);

  void visit(final BarrierNode b, A arg);

  void visit(final ChannelEndNode c, A arg);

  void visit(final ChannelNode c, A arg);

  void visit(final ExternalNode e, A arg);

  void visit(final PrimitiveNode p, A arg);

  void visit(final TimerNode t, A arg);

  void visit(final VoidNode v, A arg);

  void visit(final ConstructedNode c, A arg);

  void visit(final ErrorNode e, A arg);

  /*********************************************************
   * Top-level nodes
   *********************************************************/

  void visit(final ConstantDecl c, A arg);

  void visit(final ExternalDecl e, A arg);

  void visit(final CallableDecl<?> m, A arg);

  void visit(final ProcedureTopLevel p, A arg);

  void visit(final ProtocolCaseDecl p, A arg);

  void visit(final ProtocolDecl p, A arg);

  void visit(final RecordDecl r, A arg);

  void visit(final TypeVariableDecl t, A arg);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  void visit(final BlockStmt b, A arg);

  void visit(final BreakStmt b, A arg);

  void visit(final SwitchCaseStmt c, A arg);

  void visit(final ContinueStmt c, A arg);

  void visit(final DoWhileStmt d, A arg);

  void visit(final EmptyStmt e, A arg);

  void visit(final ExpressionStmt e, A arg);

  void visit(final ForEachStmt f, A arg);

  void visit(final ForStmt f, A arg);

  void visit(final Guard g, A arg);

  void visit(final IfStmt i, A arg);

  void visit(final RegularAltStmt r, A arg);

  void visit(final ReturnStmt r, A arg);

  void visit(final SwitchStmt s, A arg);

  void visit(final WhileStmt w, A arg);

  void visit(final ReplicatedAltStmt r, A arg);

  void visit(final AltCase a, A arg);

  void visit(final RegularParBlock r, A arg);

  void visit(final ParForBlock p, A arg);

  /*********************************************************
   * Java nodes
   *********************************************************/

  void visit(final ClassDeclaration c, A arg);

  void visit(final ConstructorDeclaration c, A arg);

  void visit(final FieldDeclaration f, A arg);

  void visit(final MethodDeclaration<?> m, A arg);

  /*********************************************************
   * Expression nodes
   *********************************************************/

  void visit(final ArrayInitializer a, A arg);

  void visit(final BinaryExpr b, A arg);

  void visit(final BooleanExpr b, A arg);

  void visit(final BooleanLiteral b, A arg);

  void visit(final CallableExpr c, A arg);

  void visit(final CastExpr c, A arg);

  void visit(final CharLiteral c, A arg);

  void visit(final ClassExpr c, A arg);

  void visit(final ObjectCreationExpr c, A arg);

  void visit(final DeclarationExpr d, A arg);

  void visit(final DoubleLiteral d, A arg);

  void visit(final EmptyExpr e, A arg);

  void visit(final FieldExpr f, A arg);

  void visit(final IntegerLiteral i, A arg);

  void visit(final ListExpression<?> l, A arg);

  void visit(final LongLiteral l, A arg);

  void visit(final MapEntryExpr m, A arg);

  void visit(final MapExpr m, A arg);

  void visit(final MethodCallExpr m, A arg);

  void visit(final NewArrayExpr n, A arg);

  void visit(final NotExpression n, A arg);

  void visit(final NullLiteral n, A arg);

  void visit(final PostfixExpr p, A arg);

  void visit(final PrefixExpr p, A arg);

  void visit(final SkipExpr s, A arg);

  void visit(final StopExpr s, A arg);

  void visit(final TernaryExpr t, A arg);

  void visit(final UnaryMinusExpr u, A arg);

  void visit(final UnaryPlusExpr u, A arg);

  void visit(final VariableExpr v, A arg);

  void visit(final FloatLiteral f, A arg);

  void visit(final StringLiteral s, A arg);

  void visit(final BlockExpr b, A arg);

  void visit(final ChannelReadExpr c, A arg);

  void visit(final ChannelWriteExpr c, A arg);

  void visit(final PathExpression p, A arg);

  void visit(final GroupExpr g, A arg);

  void visit(final ArrayAccess a, A arg);

  void visit(final RecordLiteral r, A arg);

  void visit(final RecordMemberLiteral r, A arg);

  void visit(final ProtocolLiteral p, A arg);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  void visit(final BlockComment b, A arg);

  void visit(final LineComment l, A arg);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  void visit(final ArrayType a, A arg);

  void visit(final BarrierType b, A arg);

  void visit(final ByteType b, A arg);

  void visit(final ChannelEndType c, A arg);

  void visit(final ChannelType c, A arg);

  void visit(final CharType c, A arg);

  void visit(final DoubleType d, A arg);

  void visit(final ExternalType e, A arg);

  void visit(final FloatType f, A arg);

  void visit(final IntegerType i, A arg);

  void visit(final LongType l, A arg);

  void visit(final MobileType m, A arg);

  void visit(final NullType n, A arg);

  void visit(final ProcedureType p, A arg);

  void visit(final ProtocolTagType p, A arg);

  void visit(final ProtocolType p, A arg);

  void visit(final RecordType r, A arg);

  void visit(final ShortType s, A arg);

  void visit(final BooleanType b, A arg);

  void visit(final StringType s, A arg);

  void visit(final TimerType t, A arg);

  void visit(final UnknownType u, A arg);

  void visit(final VoidType v, A arg);

  void visit(final TypeVariable t, A arg);
}
