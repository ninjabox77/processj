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
import control.CompilationUnit;
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

  R visit(final BytecodeAST c, A arg);

  R visit(final Compilation c, A arg);

  R visit(final CompilationUnit c, A arg);

  R visit(final Import i, A arg);

  R visit(final Name n, A arg);

  R visit(final Package p, A arg);

  R visit(final Parameter p, A arg);

  R visit(final Sequence<?> s, A arg);

  R visit(final VariableDeclarator v, A arg);

  R visit(final Label s, A arg);

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

  R visit(final UnknownNode e, A arg);

  /*********************************************************
   * Top-level nodes
   *********************************************************/

  R visit(final ConstantDeclaration c, A arg);

  R visit(final ExternalDeclaration e, A arg);

  R visit(final ProcedureDeclaration p, A arg);

  R visit(final ProtocolCaseDeclaration p, A arg);

  R visit(final ProtocolDeclaration p, A arg);

  R visit(final RecordDeclaration r, A arg);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  R visit(final BlockStatement b, A arg);

  R visit(final SequentialBlock s, A arg);

  R visit(final BreakStatement b, A arg);

  R visit(final SwitchCaseStatement c, A arg);

  R visit(final ContinueStatement c, A arg);

  R visit(final SkipStatement s, A arg);

  R visit(final StopStatement s, A arg);

  R visit(final DoWhileStatement d, A arg);

  R visit(final EmptyStatement e, A arg);

  R visit(final ExpressionStatement e, A arg);

  R visit(final ForEachStatement f, A arg);

  R visit(final ForStatement f, A arg);

  R visit(final Guard g, A arg);

  R visit(final IfStatement i, A arg);

  R visit(final AltBlock r, A arg);

  R visit(final ReturnStatement r, A arg);

  R visit(final SwitchStatement s, A arg);

  R visit(final WhileStatement w, A arg);

  R visit(final ReplicatedAltBlock r, A arg);

  R visit(final AltCaseStatement a, A Arg);

  R visit(final ParBlock r, A arg);

  R visit(final ParForBlock p, A arg);

  R visit(final LabelStatement l, A arg);

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

  R visit(final ArrayInitializer a, A arg);

  R visit(final TupleExpression t, A arg);

  R visit(final AssignmentExpression a, A arg);

  R visit(final BinaryExpression b, A arg);

  R visit(final BooleanExpression b, A arg);

  R visit(final BooleanLiteral b, A arg);

  R visit(final Invocation c, A arg);

  R visit(final CastExpression c, A arg);

  R visit(final CharacterLiteral c, A arg);

  R visit(final ClassExpression c, A arg);

  R visit(final ObjectCreationExpr c, A arg);

  R visit(final DeclarationExpression d, A arg);

  R visit(final DoubleLiteral d, A arg);

  R visit(final EmptyExpression e, A arg);

  R visit(final FieldExpression f, A arg);

  R visit(final IntegerLiteral i, A arg);

  R visit(final ListExpression<?> l, A arg);

  R visit(final LongLiteral l, A arg);

  R visit(final MapEntryExpression m, A arg);

  R visit(final MapExpression m, A arg);

  R visit(final MethodCallExpression m, A arg);

  R visit(final NewArrayExpression n, A arg);

  R visit(final NotExpression n, A arg);

  R visit(final NullLiteral n, A arg);

  R visit(final PostfixExpression p, A arg);

  R visit(final PrefixExpression p, A arg);

  R visit(final SkipExpression s, A arg);

  R visit(final StopExpression s, A arg);

  R visit(final TernaryExpression t, A arg);

  R visit(final UnaryMinusExpression u, A arg);

  R visit(final UnaryPlusExpression u, A arg);

  R visit(final VariableExpression v, A arg);

  R visit(final FloatLiteral f, A arg);

  R visit(final StringLiteral s, A arg);

  R visit(final BlockExpression b, A arg);

  R visit(final ChannelReadExpression c, A arg);

  R visit(final ChannelWriteExpression c, A arg);

  R visit(final GroupExpression g, A arg);

  R visit(final ArrayAccess a, A arg);

  R visit(final RecordLiteral r, A arg);

  R visit(final RecordMemberLiteral r, A arg);

  R visit(final ProtocolLiteral p, A arg);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  R visit(final BlockComment b, A arg);

  R visit(final LineComment l, A arg);

  /*********************************************************
   * Type system nodes
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

  R visit(final NamedType t, A arg);
}
