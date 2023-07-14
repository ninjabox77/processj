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

public interface DefaultVisitor<R> {

  /*********************************************************
   * Ast nodes
   *********************************************************/

  R visit(final AnnotationNode a);

  R visit(final ArrayDimension a);

  R visit(final ClassNode c);

  R visit(final CompileUnit c);

  R visit(final Import i);

  R visit(final Name n);

  R visit(final Package p);

  R visit(final Parameter p);

  R visit(final Sequence<?> s);

  R visit(final VariableDecl v);

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

  /*********************************************************
   * Top-level nodes
   *********************************************************/

  R visit(final ConstantDecl c);

  R visit(final ExternalDecl e);

  R visit(final CallableDecl<?> m);

  R visit(final ProcedureTopLevel p);

  R visit(final ProtocolTagDecl p);

  R visit(final ProtocolDecl p);

  R visit(final RecordDecl r);

  R visit(final TypeVariableDecl t);

  /*********************************************************
   * Statement nodes
   *********************************************************/

  R visit(final BlockStmt b);

  R visit(final BreakStmt b);

  R visit(final CaseStmt c);

  R visit(final ContinueStmt c);

  R visit(final DoWhileStmt d);

  R visit(final EmptyStmt e);

  R visit(final ExpressionStmt e);

  R visit(final ForEachStmt f);

  R visit(final ForStmt f);

  R visit(final GuardStmt g);

  R visit(final IfStmt i);

  R visit(final RegularAltStmt r);

  R visit(final ReturnStmt r);

  R visit(final SwitchStmt s);

  R visit(final WhileStmt w);

  R visit(final ReplicatedAltStmt r);

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

  R visit(final ArrayExpr a);

  R visit(final BinaryExpr b);

  R visit(final BooleanExpr b);

  R visit(final BooleanLiteral b);

  R visit(final CallableExpr c);

  R visit(final CastExpr c);

  R visit(final CharLiteral c);

  R visit(final ClassExpr c);

  R visit(final ConstructorCallExpr c);

  R visit(final DeclarationExpr d);

  R visit(final DoubleLiteral d);

  R visit(final EmptyExpr e);

  R visit(final FieldExpr f);

  R visit(final IntegerLiteral i);

  R visit(final ListExpression<?> l);

  R visit(final LongLiteral l);

  R visit(final MapEntryExpr m);

  R visit(final MapExpr m);

  R visit(final MethodCallExpr m);

  R visit(final NewArrayExpr n);

  R visit(final NotExpression n);

  R visit(final NullLiteral n);

  R visit(final PostfixExpr p);

  R visit(final PrefixExpr p);

  R visit(final ProtocolExpr p);

  R visit(final RecordExpr r);

  R visit(final SkipExpr s);

  R visit(final StopExpr s);

  R visit(final TernaryExpr t);

  R visit(final UnaryMinusExpr u);

  R visit(final UnaryPlusExpr u);

  R visit(final VariableExpr v);

  R visit(final FloatLiteral f);

  R visit(final StringLiteral s);

  R visit(final BlockExpr b);

  R visit(final ChannelReadExpr c);

  R visit(final ChannelWriteExpr c);

  /*********************************************************
   * Comments nodes
   *********************************************************/

  R visit(final BlockComment b);

  R visit(final LineComment l);

  /*********************************************************
   * Comments nodes
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

  R visit(final TypeVariable t);
}
