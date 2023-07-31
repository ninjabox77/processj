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

public interface VoidVisitor<A> {

  /*********************************************************
   * Ast nodes
   *********************************************************/

  void visit(final AnnotationNode a, A arg);

  void visit(final ArrayDimension a, A arg);

  void visit(final BytecodeAST c, A arg);

  void visit(final CompileUnit c, A arg);

  void visit(final CompilationUnit c, A arg);

  void visit(final Import i, A arg);

  void visit(final Name n, A arg);

  void visit(final Package p, A arg);

  void visit(final Parameter p, A arg);

  void visit(final Sequence<?> s, A arg);

  void visit(final VariableDeclarator v, A arg);

  void visit(final Label s, A arg);

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

  void visit(final ConstantDeclaration c, A arg);

  void visit(final ExternalDeclaration e, A arg);

  void visit(final CallableDeclaration<?> m, A arg);

  void visit(final ProcedureTopLevel p, A arg);

  void visit(final ProtocolCaseDeclaration p, A arg);

  void visit(final ProtocolDeclaration p, A arg);

  void visit(final RecordDeclaration r, A arg);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  void visit(final BlockStatement b, A arg);

  void visit(final BreakStatement b, A arg);

  void visit(final SwitchCaseStatement c, A arg);

  void visit(final ContinueStatement c, A arg);

  void visit(final SkipStatement s, A arg);

  void visit(final StopStatement s, A arg);

  void visit(final DoWhileStatement d, A arg);

  void visit(final EmptyStatement e, A arg);

  void visit(final ExpressionStatement e, A arg);

  void visit(final ForEachStatement f, A arg);

  void visit(final ForStatement f, A arg);

  void visit(final Guard g, A arg);

  void visit(final IfStatement i, A arg);

  void visit(final AltBlock r, A arg);

  void visit(final ReturnStatement r, A arg);

  void visit(final SwitchStatement s, A arg);

  void visit(final WhileStatement w, A arg);

  void visit(final ReplicatedAltBlock r, A arg);

  void visit(final AltCaseStatement a, A arg);

  void visit(final ParBlock r, A arg);

  void visit(final ParForBlock p, A arg);

  void visit(final LabelStatement l, A arg);

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

  void visit(final AssignmentExpression a, A arg);

  void visit(final BinaryExpression b, A arg);

  void visit(final BooleanExpression b, A arg);

  void visit(final BooleanLiteral b, A arg);

  void visit(final CallableExpression c, A arg);

  void visit(final CastExpression c, A arg);

  void visit(final CharacterLiteral c, A arg);

  void visit(final ClassExpression c, A arg);

  void visit(final ObjectCreationExpr c, A arg);

  void visit(final DeclarationExpression d, A arg);

  void visit(final DoubleLiteral d, A arg);

  void visit(final EmptyExpression e, A arg);

  void visit(final FieldExpression f, A arg);

  void visit(final IntegerLiteral i, A arg);

  void visit(final ListExpression<?> l, A arg);

  void visit(final LongLiteral l, A arg);

  void visit(final MapEntryExpression m, A arg);

  void visit(final MapExpression m, A arg);

  void visit(final MethodCallExpression m, A arg);

  void visit(final NewArrayExpression n, A arg);

  void visit(final NotExpression n, A arg);

  void visit(final NullLiteral n, A arg);

  void visit(final PostfixExpression p, A arg);

  void visit(final PrefixExpression p, A arg);

  void visit(final SkipExpression s, A arg);

  void visit(final StopExpression s, A arg);

  void visit(final TernaryExpression t, A arg);

  void visit(final UnaryMinusExpression u, A arg);

  void visit(final UnaryPlusExpression u, A arg);

  void visit(final VariableExpression v, A arg);

  void visit(final FloatLiteral f, A arg);

  void visit(final StringLiteral s, A arg);

  void visit(final BlockExpression b, A arg);

  void visit(final ChannelReadExpression c, A arg);

  void visit(final ChannelWriteExpression c, A arg);

  void visit(final GroupExpression g, A arg);

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
   * Type system nodes
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

  void visit(final NamedType t, A arg);
}
