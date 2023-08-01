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

public interface DefaultVisitor<R> {

  /*********************************************************
   * Ast nodes
   *********************************************************/

  R visit(final AnnotationNode a);

  R visit(final ArrayDimension a);

  R visit(final BytecodeAST c);

  R visit(final CompileUnit c);

  R visit(final CompilationUnit c);

  R visit(final Import i);

  R visit(final Name n);

  R visit(final Package p);

  R visit(final Parameter p);

  R visit(final Sequence<?> s);

  R visit(final VariableDeclarator v);

  R visit(final Label s);

  /*********************************************************
   * Type nodes
   *********************************************************/

  R visit(final ArrayNode a);

  R visit(final BarrierNode b);

  R visit(final ChannelEndNode c);

  R visit(final ChannelNode c);

  R visit(final ExternalNode e);

  R visit(final PrimitiveNode p);

  R visit(final TimerNode t);

  R visit(final VoidNode v);

  R visit(final ConstructedNode c);

  R visit(final ErrorNode e);

  /*********************************************************
   * Top-level nodes
   *********************************************************/

  R visit(final ConstantDeclaration c);

  R visit(final ExternalDeclaration e);

  R visit(final CallabelDeclaration<?> m);

  R visit(final ProcedureTopLevel p);

  R visit(final ProtocolCaseDeclaration p);

  R visit(final ProtocolDeclaration p);

  R visit(final RecordDeclaration r);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  R visit(final BlockStatement b);

  R visit(final BreakStatement b);

  R visit(final SwitchCaseStatement c);

  R visit(final ContinueStatement c);

  R visit(final SkipStatement s);

  R visit(final StopStatement s);

  R visit(final DoWhileStatement d);

  R visit(final EmptyStatement e);

  R visit(final ExpressionStatement e);

  R visit(final ForEachStatement f);

  R visit(final ForStatement f);

  R visit(final Guard g);

  R visit(final IfStatement i);

  R visit(final AltBlock r);

  R visit(final ReturnStatement r);

  R visit(final SwitchStatement s);

  R visit(final WhileStatement w);

  R visit(final ReplicatedAltBlock r);

  R visit(final AltCaseStatement a);

  R visit(final ParBlock r);

  R visit(final ParForBlock p);

  R visit(final LabelStatement l);

  /*********************************************************
   * Java nodes
   *********************************************************/

  R visit(final ClassDeclaration c);

  R visit(final ConstructorDeclaration c);

  R visit(final FieldDeclaration f);

  R visit(final MethodDeclaration<?> m);

  /*********************************************************
   * Expression nodes
   *********************************************************/

  R visit(final ArrayInitializer a);

  R visit(final AssignmentExpression a);

  R visit(final BinaryExpression b);

  R visit(final BooleanExpression b);

  R visit(final BooleanLiteral b);

  R visit(final CallabelExpression c);

  R visit(final CastExpression c);

  R visit(final CharacterLiteral c);

  R visit(final ClassExpression c);

  R visit(final ObjectCreationExpr c);

  R visit(final DeclarationExpression d);

  R visit(final DoubleLiteral d);

  R visit(final EmptyExpression e);

  R visit(final FieldExpression f);

  R visit(final IntegerLiteral i);

  R visit(final ListExpression<?> l);

  R visit(final LongLiteral l);

  R visit(final MapEntryExpression m);

  R visit(final MapExpression m);

  R visit(final MethodCallExpression m);

  R visit(final NewArrayExpression n);

  R visit(final NotExpression n);

  R visit(final NullLiteral n);

  R visit(final PostfixExpression p);

  R visit(final PrefixExpression p);

  R visit(final SkipExpression s);

  R visit(final StopExpression s);

  R visit(final TernaryExpression t);

  R visit(final UnaryMinusExpression u);

  R visit(final UnaryPlusExpression u);

  R visit(final VariableExpression v);

  R visit(final FloatLiteral f);

  R visit(final StringLiteral s);

  R visit(final BlockExpression b);

  R visit(final ChannelReadExpression c);

  R visit(final ChannelWriteExpression c);

  R visit(final GroupExpression g);

  R visit(final ArrayAccess a);

  R visit(final RecordLiteral r);

  R visit(final RecordMemberLiteral r);

  R visit(final ProtocolLiteral p);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  R visit(final BlockComment b);

  R visit(final LineComment l);

  /*********************************************************
   * Type system nodes
   *********************************************************/

  R visit(final ArrayType a);

  R visit(final BarrierType b);

  R visit(final ByteType b);

  R visit(final ChannelEndType c);

  R visit(final ChannelType c);

  R visit(final CharType c);

  R visit(final DoubleType d);

  R visit(final ExternalType e);

  R visit(final FloatType f);

  R visit(final IntegerType i);

  R visit(final LongType l);

  R visit(final MobileType m);

  R visit(final NullType n);

  R visit(final ProcedureType p);

  R visit(final ProtocolTagType p);

  R visit(final ProtocolType p);

  R visit(final RecordType r);

  R visit(final ShortType s);

  R visit(final BooleanType b);

  R visit(final StringType s);

  R visit(final TimerType t);

  R visit(final UnknownType u);

  R visit(final VoidType v);

  R visit(final NamedType t);
}
