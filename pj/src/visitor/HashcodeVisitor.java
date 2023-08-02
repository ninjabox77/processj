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
  public Integer visit(final Compilation c) {
    return c.getFile().hashCode() * 31 +
        c.getPackage().accept(this) * 31 +
        (c.getImports().isPresent() ? c.getImports().get().accept(this) : 0) * 31 +
        (c.getTypeDeclarations().isPresent() ? c.getTypeDeclarations().get().accept(this) : 0) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final CompilationUnit c) {
    return null;
  }

  @Override
  public Integer visit(final Import i) {
    return i.getName().accept(this) * 31 +
        (i.getAlias() != null ? i.getAlias().hashCode() : 0) * 31 +
        (i.getFieldName() != null ? i.getFieldName().hashCode() : 0) * 31 +
        (i.isJavaImport() ? 1 : 0) * 31 +
        (i.isStar() ? 1 : 0) * 31 +
        (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Name n) {
    return n.getIdentifier().hashCode() * 31 +
        (n.getQualifier().isPresent() ? n.getQualifier().get().accept(this) : 0) * 31 +
        (n.getComment().isPresent() ? n.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Package p) {
    return p.getName().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Parameter p) {
    return p.getModifiers() * 31 +
        p.getASTType().accept(this) * 31 +
        p.getIdentifier().hashCode() * 31 +
        (p.isVarArgs() ? 1 : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Sequence<?> s) {
    int hash = 0;
    for (Visitor object : s)
      hash += object.accept(this) * 31;
    return hash;
  }

  @Override
  public Integer visit(final VariableDeclarator v) {
    return v.getModifiers() * 31 +
        v.getASTType().accept(this) * 31 +
        v.getIdentifier().hashCode() * 31 +
        (v.getRightExpression() != null ? v.getRightExpression().accept(this) : 0) * 31 +
        (v.getComment().isPresent() ? v.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Label s) {
    return s.getIdentifier().hashCode() * 31 +
        (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ArrayNode a) {
    return a.getType().accept(this) * 31 +
        a.getBracketPosition().hashCode() * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BarrierNode b) {
    return b.getType().accept(this) * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelEndNode c) {
    return c.getType().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelNode c) {
    return c.getType().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ExternalNode e) {
    return null;
  }

  @Override
  public Integer visit(final PrimitiveNode p) {
    return p.getType().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TimerNode t) {
    return t.getType().accept(this) * 31 +
        (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final VoidNode v) {
    return v.getType().accept(this) * 31 +
        (v.getComment().isPresent() ? v.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ConstructedNode c) {
    return c.getType().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final UnknownNode u) {
    return u.getType().accept(this) * 31 +
        (u.getComment().isPresent() ? u.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ConstantDeclaration c) {
    return c.getModifiers() * 31 +
        c.getASTType().accept(this) * 31 +
        c.getIdentifier().hashCode() * 31 +
        (c.getRightExpression().isPresent() ? c.getRightExpression().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ExternalDeclaration e) {
    return null;
  }

  @Override
  public Integer visit(final ProcedureDeclaration p) {
    return p.getModifiers() * 31 +
        p.getASTType().accept(this) * 31 +
        p.getIdentifier().hashCode() * 31 +
        (p.getImplementedNames().isPresent() ? p.getImplementedNames().get().accept(this) : 0) * 31 +
        p.getParameters().accept(this) * 31 +
        p.getBody().accept(this) * 31 +
        (p.doesYield() ? 1 : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ProtocolCaseDeclaration p) {
    return p.getIdentifier().hashCode() * 31 +
        (p.getDeclaredFields().isPresent() ? p.getDeclaredFields().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ProtocolDeclaration p) {
    return p.getModifiers() * 31 +
        p.getASTType().accept(this) * 31 +
        p.getIdentifier().hashCode() * 31 +
        (p.getImplementedNames().isPresent() ? p.getImplementedNames().get().accept(this) : 0) * 31 +
        (p.getDeclaredTags().isPresent() ? p.getDeclaredTags().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final RecordDeclaration r) {
    return r.getModifiers() * 31 +
        r.getASTType().accept(this) * 31 +
        r.getIdentifier().hashCode() * 31 +
        (r.getImplementedNames().isPresent() ? r.getImplementedNames().get().accept(this) : 0) * 31 +
        (r.getDeclaredFields().isPresent() ? r.getDeclaredFields().get().accept(this) : 0) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BlockStatement b) {
    return b.getStatements().accept(this) * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final SequentialBlock s) {
    return s.getStatements().accept(this) * 31 +
        (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BreakStatement b) {
    return (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31 +
        (b.getLabel().isPresent() ? b.getLabel().get().hashCode() : 0) * 31;
  }

  @Override
  public Integer visit(final SwitchCaseStatement s) {
    return s.getExpressions().accept(this) * 31 +
        (s.getStatements().isPresent() ? s.getStatements().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ContinueStatement c) {
    return (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31 +
        (c.getLabel().isPresent() ? c.getLabel().get().hashCode() : 0) * 31;
  }

  @Override
  public Integer visit(final SkipStatement s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final StopStatement s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final DoWhileStatement d) {
    return d.getCondition().accept(this) * 31 +
        (d.getLoopBlock().isPresent() ? d.getLoopBlock().get().accept(this) : 0) * 31 +
        (d.getComment().isPresent() ? d.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final EmptyStatement e) {
    return (e.getComment().isPresent() ? e.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ExpressionStatement e) {
    return e.getExpression().accept(this) * 31 +
        (e.getComment().isPresent() ? e.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ForEachStatement f) {
    return f.getVariable().accept(this) * 31 +
        f.getCollectionExpr().accept(this) * 31 +
        (f.getLoopBlock().isPresent() ? f.getLoopBlock().get().accept(this) : 0) * 31 +
        (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ForStatement f) {
    return f.getInitialization().accept(this) * 31 +
        f.getConditional().accept(this) * 31 +
        f.getUpdate().accept(this) * 31 +
        (f.getLoopBlock().isPresent() ? f.getLoopBlock().get().accept(this) : 0) * 31 +
        (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Guard g) {
    return g.getExpression().accept(this) * 31 +
        (g.isSkip() ? 1 : 0) * 31 +
        (g.getComment().isPresent() ? g.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final IfStatement i) {
    return i.getConditional().accept(this) * 31 +
        i.getIfPart().accept(this) * 31 +
        (i.getElsePart().isPresent() ? i.getElsePart().get().accept(this) : 0) * 31 +
        (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final AltBlock r) {
    return r.getLoopBlock().accept(this) * 31 +
        (r.isFairAlt() ? 1 : 0) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ReturnStatement r) {
    return (r.getExpression().isPresent() ? r.getExpression().get().accept(this) : 0) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final SwitchStatement s) {
    return s.getSelector().accept(this) * 31 +
        s.getSwitchCases().accept(this) * 31 +
        (s.getDefaultStmt().isPresent() ? 1 : 0) * 31 +
        (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final WhileStatement w) {
    return w.getCondition().accept(this) * 31 +
        (w.getLoopBlock().isPresent() ? w.getLoopBlock().get().accept(this) : 0) * 31 +
        (w.getComment().isPresent() ? w.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ReplicatedAltBlock r) {
    return r.getInitialization().accept(this) * 31 +
        r.getConditional().accept(this) * 31 +
        r.getUpdate().accept(this) * 31 +
        r.getLoopBlock().accept(this) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final AltCaseStatement a) {
    return (a.getExpression().isPresent() ? a.getExpression().get().accept(this) : 0) * 31 +
        a.getGuard().accept(this) * 31 +
        a.getStatement().accept(this) * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ParBlock p) {
    return (p.getBarriers().isPresent() ? p.getBarriers().get().accept(this) : 0) * 31 +
        p.getLoopBlock().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ParForBlock p) {
    return (p.getBarriers().isPresent() ? p.getBarriers().get().accept(this) : 0) * 31 +
        p.getLoopBlock().accept(this) * 31 +
        p.getInitialization().accept(this) * 31 +
        p.getConditional().accept(this) * 31 +
        p.getUpdate().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final LabelStatement l) {
    return (l.getStatementLabels().isPresent() ? l.getStatementLabels().get().accept(this) : 0) * 31 +
        l.getStatement().accept(this) * 31 +
        (l.getComment().isPresent() ? l.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ClassDeclaration c) {
    return c.getModifiers() * 31 +
        c.getIdentifier().hashCode() * 31 +
        (c.getExtendedClasses().isPresent() ? c.getExtendedClasses().get().accept(this) : 0) * 31 +
        (c.getImplementedClasses().isPresent() ? c.getImplementedClasses().get().accept(this) : 0) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ConstructorDeclaration c) {
    return c.getModifiers() * 31 +
        c.getASTType().accept(this) * 31 +
        c.getIdentifier().hashCode() * 31 +
        c.getBody().accept(this) * 31 +
        (c.getParameters().isPresent() ? c.getParameters().get().accept(this) : 0) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final FieldDeclaration f) {
    return f.getModifiers() * 31 +
        f.getVariable().accept(this) * 31 +
        (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final MethodDeclaration<?> m) {
    return m.getModifiers() * 31 +
        m.getASTType().accept(this) * 31 +
        m.getIdentifier().hashCode() * 31 +
        m.getBody().accept(this) * 31 +
        (m.getParameters().isPresent() ? m.getParameters().get().accept(this) : 0) * 31 +
        (m.getComment().isPresent() ? m.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ArrayInitializer a) {
    return a.getValues().accept(this) * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TupleExpression t) {
    return t.getValues().accept(this) * 31 +
        (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final AssignmentExpression a) {
    return a.getOperator() * 31 +
        a.getLeftExpression().accept(this) * 31 +
        a.getRightExpression().accept(this) * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BinaryExpression b) {
    return b.getOperator() * 31 +
        b.getLeftExpression().accept(this) * 31 +
        b.getRightExpression().accept(this) * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BooleanExpression b) {
    return b.getExpression().accept(this) * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BooleanLiteral b) {
    return b.getValue().hashCode() * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final Invocation i) {
    return (i.getScope().isPresent() ? i.getScope().get().accept(this) : 0) * 31 +
        i.getArguments().accept(this) * 31 +
        (i.isImplicitThis() ? 1 : 0) * 31 +
        i.getIdentifier().hashCode() * 31 +
        (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final CastExpression c) {
    return c.getExpression().accept(this) * 31 +
        (c.isCoerce() ? 1 : 0) * 31 +
        (c.isIgnoreAutoboxing() ? 1 : 0) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final CharacterLiteral c) {
    return c.getValue().hashCode() * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ClassExpression c) {
    return (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ObjectCreationExpr o) {
    return (o.getScope().isPresent() ? o.getScope().get().accept(this) : 0) * 31 +
        o.getArguments().accept(this) * 31 +
        (o.getTypeArguments().isPresent() ? o.getTypeArguments().get().accept(this) : 0) * 31 +
        o.getIdentifier().hashCode() * 31 +
        (o.isImplicitThis() ? 1 : 0) * 31 +
        (o.getComment().isPresent() ? o.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final DeclarationExpression d) {
    return d.getVariable().accept(this) * 31 +
        (d.getComment().isPresent() ? d.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final DoubleLiteral d) {
    return d.getValue().hashCode() * 31 +
        (d.getComment().isPresent() ? d.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final EmptyExpression e) {
    return (e.getComment().isPresent() ? e.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final FieldExpression f) {
    return (f.getScope().isPresent() ? f.getScope().get().accept(this) : 0) * 31 +
        f.getIdentifier().hashCode() * 31 +
        (f.isImplicitThis() ? 1 : 0) * 31 +
        (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final IntegerLiteral i) {
    return i.getValue().hashCode() * 31 +
        (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ListExpression<?> l) {
    return l.getValues().accept(this) * 31 +
        (l.getComment().isPresent() ? l.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final LongLiteral l) {
    return l.getValue().hashCode() * 31 +
        (l.getComment().isPresent() ? l.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final MapEntryExpression m) {
    return m.getKeyExpression().accept(this) * 31 +
        m.getValueExpression().accept(this) * 31 +
        (m.getComment().isPresent() ? m.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final MapExpression m) {
    return m.getMapEntry().accept(this) * 31 +
        (m.getComment().isPresent() ? m.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final MethodCallExpression m) {
    return (m.getScope().isPresent() ? m.getScope().get().accept(this) : 0) * 31 +
        m.getArguments().accept(this) * 31 +
        (m.getTypeArguments().isPresent() ? m.getTypeArguments().get().accept(this) : 0) * 31 +
        m.getIdentifier().hashCode() * 31 +
        (m.isImplicitThis() ? 1 : 0) * 31 +
        (m.getComment().isPresent() ? m.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final NewArrayExpression n) {
    return (n.getLevels().isPresent() ? n.getLevels().get().accept(this) : 0) * 31 +
        n.getArrayInitializer().accept(this) * 31 +
        (n.getComment().isPresent() ? n.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final NotExpression n) {
    return n.getExpression().accept(this) * 31 +
        (n.getComment().isPresent() ? n.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final NullLiteral n) {
    return n.getValue().hashCode() * 31 +
        (n.getComment().isPresent() ? n.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final PostfixExpression p) {
    return p.getOperator() * 31 +
        p.getExpression().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final PrefixExpression p) {
    return p.getOperator() * 31 +
        p.getExpression().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final SkipExpression s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final StopExpression s) {
    return (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TernaryExpression t) {
    return t.getCondition().accept(this) * 31 +
        t.getTrueExpression().accept(this) * 31 +
        t.getFalseExpression().accept(this) * 31 +
        (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final UnaryMinusExpression u) {
    return u.getExpression().accept(this) * 31 +
        (u.getComment().isPresent() ? u.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final UnaryPlusExpression u) {
    return u.getExpression().accept(this) * 31 +
        (u.getComment().isPresent() ? u.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final VariableExpression v) {
    return v.getIdentifier().hashCode() * 31 +
        (v.getAccessVariable().isPresent() ? 1 : 0) * 31 +
        (v.getComment().isPresent() ? v.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final FloatLiteral f) {
    return f.getValue().hashCode() * 31 +
        (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final StringLiteral s) {
    return s.getValue().hashCode() * 31 +
        (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BlockExpression b) {
    return b.getStatements().accept(this) * 31 +
        (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelReadExpression c) {
    return c.getChannel().accept(this) * 31 +
        c.getArguments().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelWriteExpression c) {
    return c.getChannel().accept(this) * 31 +
        c.getArguments().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final GroupExpression g) {
    return g.getExpression().accept(this) * 31 +
        (g.getComment().isPresent() ? g.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ArrayAccess a) {
    return a.getName().accept(this) * 31 +
        a.getIndex().accept(this) * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final RecordLiteral r) {
    return r.getName().accept(this) * 31 +
        r.getMembers().accept(this) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final RecordMemberLiteral r) {
    return r.getName().accept(this) * 31 +
        r.getExpression().accept(this) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ProtocolLiteral p) {
    return p.getName().accept(this) * 31 +
        p.getTag().accept(this) * 31 +
        p.getMembers().accept(this) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
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
    return a.getArrayLevel() * 31 +
        a.getComponentType().accept(this) * 31 +
        (a.getComment().isPresent() ? a.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BarrierType b) {
    return b.getKind() * 31 + (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ByteType b) {
    return b.getKind() * 31 + (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelEndType c) {
    return c.getChannelEnd() * 31 +
        c.getComponentType().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ChannelType c) {
    return c.getChannelEnd() * 31 +
        c.getComponentType().accept(this) * 31 +
        (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final CharType c) {
    return c.getKind() * 31 + (c.getComment().isPresent() ? c.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final DoubleType d) {
    return d.getKind() * 31 + (d.getComment().isPresent() ? d.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ExternalType e) {
    return (e.getComment().isPresent() ? e.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final FloatType f) {
    return f.getKind() * 31 + (f.getComment().isPresent() ? f.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final IntegerType i) {
    return i.getKind() * 31 + (i.getComment().isPresent() ? i.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final LongType l) {
    return l.getKind() * 31 + (l.getComment().isPresent() ? l.getComment().get().accept(this) : 0) * 31;
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
    return (p.doesYield() ? 1 : 0) * 31 +
        p.getReturnType().accept(this) * 31 +
        p.getParameterTypes().accept(this) * 31 +
        (p.getImplementedTypes().isPresent() ? p.getImplementedTypes().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
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
        (p.getFieldTypes().isPresent() ? p.getFieldTypes().get().accept(this) : 0) * 31 +
        (p.getImplementedTypes().isPresent() ? p.getImplementedTypes().get().accept(this) : 0) * 31 +
        (p.getComment().isPresent() ? p.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final RecordType r) {
    return r.getName().hashCode() * 31 +
        (r.getTypeFields().isPresent() ? r.getTypeFields().get().accept(this) : 0) * 31 +
        (r.getImplementedTypes().isPresent() ? r.getImplementedTypes().get().accept(this) : 0) * 31 +
        (r.getComment().isPresent() ? r.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final ShortType s) {
    return s.getKind() * 31 + (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final BooleanType b) {
    return b.getKind() * 31 + (b.getComment().isPresent() ? b.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final StringType s) {
    return s.getKind() * 31 + (s.getComment().isPresent() ? s.getComment().get().accept(this) : 0) * 31;
  }

  @Override
  public Integer visit(final TimerType t) {
    return t.getKind() * 31 + (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
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
    return t.getName().accept(this) * 31 +
        (t.getComment().isPresent() ? t.getComment().get().accept(this) : 0) * 31;
  }
}
