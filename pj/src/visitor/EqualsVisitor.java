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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Visitor used to check for equality in the AST.
 *
 * @author Ben
 */
public class EqualsVisitor implements GenericVisitor<Boolean, Visitor> {

  private static final EqualsVisitor INSTANCE = new EqualsVisitor();

  private EqualsVisitor() {
  }

  public static boolean equals(final SourceAST n1, final SourceAST n2) {
    return INSTANCE.objectEquals(n1, n2);
  }

  private <N extends SourceAST> Boolean objectEquals(final Sequence<N> nodes1, final Sequence<N> nodes2) {
    if (nodes1 == null) {
      return nodes2 == null;
    } else if (nodes2 == null) {
      return Boolean.FALSE;
    }
    if (nodes1 == nodes2) {
      return Boolean.TRUE;
    }
    if (nodes1.size() != nodes2.size()) {
      return Boolean.FALSE;
    }
    for (int i = 0; i < nodes1.size(); ++i) {
      if (!objectEquals(nodes1.get(i), nodes2.get(i))) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  private <T> Boolean objectEquals(final List<T> n1, final List<T> n2) {
    if (n1 == null) {
      return n2 == null;
    } else if (n2 == null) {
      return Boolean.FALSE;
    }
    if (n1 == n2) {
      return Boolean.TRUE;
    }
    if (n1.size() != n2.size()) {
      return Boolean.FALSE;
    }
    for (int i = 0; i < n1.size(); ++i) {
      if (!objectEquals(n1.get(i), n2.get(i))) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  private <N extends SourceAST> Boolean objectEquals(final N n1, final N n2) {
    if (n1 == null) {
      return n2 == null;
    } else if (n2 == null) {
      return Boolean.FALSE;
    }
    if (n1 == n2) {
      return Boolean.TRUE;
    }
    if (n1.getClass() != n2.getClass()) {
      return Boolean.FALSE;
    }
    return n1.accept(this, n2);
  }

  private Boolean objectEquals(final Object o1, final Object o2) {
    if (o1 == null) {
      return o2 == null;
    } else if (o2 == null) {
      return Boolean.FALSE;
    }
    if (o1 == o2) {
      return Boolean.TRUE;
    }
    return o1.equals(o2);
  }

  private <N extends SourceAST> Boolean objectEquals(final Optional<N> n1, final Optional<N> n2) {
    return objectEquals(n1.orElse(null), n2.orElse(null));
  }

  private <N extends SourceAST> Boolean objectsEquals(final Optional<Sequence<N>> n1, final Optional<Sequence<N>> n2) {
    return objectEquals(n1.orElse(null), n2.orElse(null));
  }

  @Override
  public Boolean visit(final AnnotationNode a, Visitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ArrayDimension a, Visitor arg) {
    final ArrayDimension a2 = (ArrayDimension) arg;
    return objectEquals(a.getDimension(), a2.getDimension()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BytecodeAST c, Visitor arg) {
    final BytecodeAST c2 = (BytecodeAST) arg;
    return objectEquals(c.getName(), c2.getName()) &&
        Arrays.equals(c.getBytes(), c2.getBytes()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final Compilation c, Visitor arg) {
    final Compilation c2 = (Compilation) arg;
    return objectEquals(c.getPackage(), c2.getPackage()) &&
        objectEquals(c.getImports(), c2.getImports()) &&
        objectEquals(c.getTypeDeclarations(), c2.getTypeDeclarations()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CompilationUnit c, Visitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Import i, Visitor arg) {
    final Import i2 = (Import) arg;
    return objectEquals(i.getName(), i2.getName()) &&
        objectEquals(i.getAlias(), i2.getAlias()) &&
        objectEquals(i.getFieldName(), i2.getFieldName()) &&
        objectEquals(i.isJavaImport(), i2.isJavaImport()) &&
        objectEquals(i.isStar(), i2.isStar()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final Name n, Visitor arg) {
    final Name n2 = (Name) arg;
    return objectEquals(n.getIdentifier(), n2.getIdentifier()) &&
        objectEquals(n.getQualifier(), n2.getQualifier()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final Package p, Visitor arg) {
    final Package p2 = (Package) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final Parameter p, Visitor arg) {
    final Parameter p2 = (Parameter) arg;
    return objectEquals(p.getIdentifier(), p2.getIdentifier()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final Sequence<?> s, Visitor arg) {
    return objectEquals(s, arg);
  }

  @Override
  public Boolean visit(final VariableDeclarator v, Visitor arg) {
    final VariableDeclarator v2 = (VariableDeclarator) arg;
    return objectEquals(v.getModifiers(), v2.getModifiers()) &&
        objectEquals(v.getASTType(), v2.getASTType()) &&
        objectEquals(v.getIdentifier(), v2.getIdentifier()) &&
        objectEquals(v.getRightExpression(), v2.getRightExpression()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final Label s, Visitor arg) {
    final Label s2 = (Label) arg;
    return objectEquals(s.getIdentifier(), s2.getIdentifier()) &&
        objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final ArrayNode a, Visitor arg) {
    final ArrayNode a2 = (ArrayNode) arg;
    return objectEquals(a.getType(), a2.getType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BarrierNode b, Visitor arg) {
    final BarrierNode b2 = (BarrierNode) arg;
    return objectEquals(b.getType(), b2.getType()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelEndNode c, Visitor arg) {
    final ChannelEndNode c2 = (ChannelEndNode) arg;
    return objectEquals(c.getType(), c2.getType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelNode c, Visitor arg) {
    final ChannelNode c2 = (ChannelNode) arg;
    return objectEquals(c.getType(), c2.getType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ExternalNode e, Visitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final PrimitiveNode p, Visitor arg) {
    final PrimitiveNode p2 = (PrimitiveNode) arg;
    return objectEquals(p.getType(), p2.getType()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final TimerNode t, Visitor arg) {
    final TimerNode t2 = (TimerNode) arg;
    return objectEquals(t.getType(), t2.getType()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final VoidNode v, Visitor arg) {
    final VoidNode v2 = (VoidNode) arg;
    return objectEquals(v.getType(), v2.getType()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final ConstructedNode c, Visitor arg) {
    final ConstructedNode c2 = (ConstructedNode) arg;
    return objectEquals(c.getType(), c2.getType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final UnknownNode e, Visitor arg) {
    final UnknownNode e2 = (UnknownNode) arg;
    return objectEquals(e.getType(), e2.getType()) &&
        objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(final ConstantDeclaration c, Visitor arg) {
    final ConstantDeclaration c2 = (ConstantDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectEquals(c.getRightExpression(), c2.getRightExpression()) &&
        objectsEquals(c.getImplementedNames(), c2.getImplementedNames()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ExternalDeclaration e, Visitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ProcedureDeclaration p, Visitor arg) {
    final ProcedureDeclaration p2 = (ProcedureDeclaration) arg;
    return objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getIdentifier(), p2.getIdentifier()) &&
        objectEquals(p.getParameters(), p2.getParameters()) &&
        objectEquals(p.getBody(), p2.getBody()) &&
        objectEquals(p.doesYield(), p2.doesYield()) &&
        objectsEquals(p.getImplementedNames(), p2.getImplementedNames()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolCaseDeclaration p, Visitor arg) {
    final ProtocolCaseDeclaration p2 = (ProtocolCaseDeclaration) arg;
    return objectEquals(p.getIdentifier(), p2.getIdentifier()) &&
        objectsEquals(p.getDeclaredFields(), p2.getDeclaredFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolDeclaration p, Visitor arg) {
    final ProtocolDeclaration p2 = (ProtocolDeclaration) arg;
    return objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getIdentifier(), p2.getIdentifier()) &&
        objectsEquals(p.getDeclaredTags(), p2.getDeclaredTags()) &&
        objectsEquals(p.getImplementedNames(), p2.getImplementedNames()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final RecordDeclaration r, Visitor arg) {
    final RecordDeclaration r2 = (RecordDeclaration) arg;
    return objectEquals(r.getModifiers(), r2.getModifiers()) &&
        objectEquals(r.getASTType(), r2.getASTType()) &&
        objectEquals(r.getIdentifier(), r2.getIdentifier()) &&
        objectsEquals(r.getDeclaredFields(), r2.getDeclaredFields()) &&
        objectsEquals(r.getImplementedNames(), r2.getImplementedNames()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final BlockStatement b, Visitor arg) {
    final BlockStatement b2 = (BlockStatement) arg;
    return objectEquals(b.getStatements(), b2.getStatements()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(final SequentialBlock s, Visitor arg) {
    final SequentialBlock s2 = (SequentialBlock) arg;
    return objectEquals(s.getStatements(), s2.getStatements()) &&
        objectEquals(s.getComment(), s2.getComment()) &&
        objectsEquals(s.getStatementLabels(), s2.getStatementLabels());
  }

  @Override
  public Boolean visit(final BreakStatement b, Visitor arg) {
    final BreakStatement b2 = (BreakStatement) arg;
    return objectEquals(b.getLabel(), b2.getLabel()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(final SwitchCaseStatement c, Visitor arg) {
    final SwitchCaseStatement c2 = (SwitchCaseStatement) arg;
    return objectEquals(c.getExpressions(), c2.getExpressions()) &&
        objectEquals(c.getStatements(), c2.getStatements()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ContinueStatement c, Visitor arg) {
    final ContinueStatement c2 = (ContinueStatement) arg;
    return objectEquals(c.getLabel(), c2.getLabel()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final SkipStatement s, Visitor arg) {
    final SkipStatement s2 = (SkipStatement) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final StopStatement s, Visitor arg) {
    final StopStatement s2 = (StopStatement) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final DoWhileStatement d, Visitor arg) {
    final DoWhileStatement d2 = (DoWhileStatement) arg;
    return objectEquals(d.getCondition(), d2.getCondition()) &&
        objectEquals(d.getLoopBlock(), d2.getLoopBlock()) &&
        objectsEquals(d.getStatementLabels(), d2.getStatementLabels()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final EmptyStatement e, Visitor arg) {
    final EmptyStatement e2 = (EmptyStatement) arg;
    return objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ExpressionStatement e, Visitor arg) {
    final ExpressionStatement e2 = (ExpressionStatement) arg;
    return objectEquals(e.getExpression(), e2.getExpression()) &&
        objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ForEachStatement f, Visitor arg) {
    final ForEachStatement f2 = (ForEachStatement) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getCollectionExpr(), f2.getCollectionExpr()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ForStatement f, Visitor arg) {
    final ForStatement f2 = (ForStatement) arg;
    return objectEquals(f.getInitialization(), f2.getInitialization()) &&
        objectEquals(f.getConditional(), f2.getConditional()) &&
        objectEquals(f.getUpdate(), f2.getUpdate()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(final Guard g, Visitor arg) {
    final Guard g2 = (Guard) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.isSkip(), g2.isSkip()) &&
        objectEquals(g.getComment(), g2.getComment());
  }

  @Override
  public Boolean visit(final IfStatement i, Visitor arg) {
    final IfStatement i2 = (IfStatement) arg;
    return objectEquals(i.getConditional(), i2.getConditional()) &&
        objectEquals(i.getIfPart(), i2.getIfPart()) &&
        objectEquals(i.getElsePart(), i2.getElsePart()) &&
        objectEquals(i.getComment(), i2.getComment()) &&
        objectsEquals(i.getStatementLabels(), i2.getStatementLabels());
  }

  @Override
  public Boolean visit(final AltBlock r, Visitor arg) {
    final AltBlock r2 = (AltBlock) arg;
    return objectEquals(r.getLoopBlock(), r2.getLoopBlock()) &&
        objectEquals(r.isFairAlt(), r2.isFairAlt()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ReturnStatement r, Visitor arg) {
    final ReturnStatement r2 = (ReturnStatement) arg;
    return objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(final SwitchStatement s, Visitor arg) {
    final SwitchStatement s2 = (SwitchStatement) arg;
    return objectEquals(s.getSelector(), s2.getSelector()) &&
        objectEquals(s.getSwitchCases(), s2.getSwitchCases()) &&
        objectEquals(s.getDefaultStmt(), s2.getDefaultStmt()) &&
        objectEquals(s.getComment(), s2.getComment()) &&
        objectsEquals(s.getStatementLabels(), s2.getStatementLabels());
  }

  @Override
  public Boolean visit(final WhileStatement w, Visitor arg) {
    final WhileStatement w2 = (WhileStatement) arg;
    return objectEquals(w.getCondition(), w2.getCondition()) &&
        objectEquals(w.getLoopBlock(), w2.getLoopBlock()) &&
        objectEquals(w.getComment(), w2.getComment()) &&
        objectsEquals(w.getStatementLabels(), w2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ReplicatedAltBlock r, Visitor arg) {
    final ReplicatedAltBlock r2 = (ReplicatedAltBlock) arg;
    return objectsEquals(r.getStatementLabels(), r2.getStatementLabels()) &&
        objectEquals(r.getLoopBlock(), r2.getLoopBlock()) &&
        objectEquals(r.getInitialization(), r2.getInitialization()) &&
        objectEquals(r.getConditional(), r2.getConditional()) &&
        objectEquals(r.getUpdate(), r2.getUpdate()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final AltCaseStatement a, Visitor arg) {
    final AltCaseStatement a2 = (AltCaseStatement) arg;
    return objectEquals(a.getExpression(), a2.getExpression()) &&
        objectEquals(a.getGuard(), a2.getGuard()) &&
        objectEquals(a.getStatement(), a2.getStatement()) &&
        objectEquals(a.getStatementLabels(), a2.getStatementLabels()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final ParBlock r, Visitor arg) {
    final ParBlock r2 = (ParBlock) arg;
    return objectEquals(r.getBarriers(), r2.getBarriers()) &&
        objectEquals(r.getLoopBlock(), r2.getLoopBlock()) &&
        objectEquals(r.getStatementLabels(), r2.getLoopBlock()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ParForBlock p, Visitor arg) {
    final ParForBlock p2 = (ParForBlock) arg;
    return objectEquals(p.getInitialization(), p2.getInitialization()) &&
        objectEquals(p.getConditional(), p2.getConditional()) &&
        objectEquals(p.getUpdate(), p2.getUpdate()) &&
        objectEquals(p.getLoopBlock(), p2.getLoopBlock()) &&
        objectEquals(p.getStatementLabels(), p2.getStatementLabels()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final LabelStatement l, Visitor arg) {
    final LabelStatement l2 = (LabelStatement) arg;
    return objectEquals(l.getStatement(), l2.getStatement()) &&
        objectEquals(l.getStatementLabels(), l2.getStatementLabels()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final ClassDeclaration c, Visitor arg) {
    final ClassDeclaration c2 = (ClassDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectsEquals(c.getDeclarations(), c2.getDeclarations()) &&
        objectEquals(c.isInterface(), c2.isInterface()) &&
        objectsEquals(c.getExtendedClasses(), c2.getExtendedClasses()) &&
        objectsEquals(c.getImplementedClasses(), c2.getImplementedClasses());
  }

  @Override
  public Boolean visit(final ConstructorDeclaration c, Visitor arg) {
    final ConstructorDeclaration c2 = (ConstructorDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectEquals(c.getBody(), c2.getBody()) &&
        objectsEquals(c.getParameters(), c2.getParameters()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final FieldDeclaration f, Visitor arg) {
    final FieldDeclaration f2 = (FieldDeclaration) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final MethodDeclaration<?> m, Visitor arg) {
    final MethodDeclaration<?> m2 = (MethodDeclaration<?>) arg;
    return objectEquals(m.getASTType(), m2.getASTType()) &&
        objectEquals(m.getIdentifier(), m2.getIdentifier()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final AssignmentExpression a, Visitor arg) {
    final AssignmentExpression a2 = (AssignmentExpression) arg;
    return objectEquals(a.getLeftExpression(), a2.getRightExpression()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final ArrayInitializer a, Visitor arg) {
    final ArrayInitializer a2 = (ArrayInitializer) arg;
    return objectEquals(a.getValues(), a2.getValues()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final TupleExpression t, Visitor arg) {
    final TupleExpression t2 = (TupleExpression) arg;
    return objectEquals(t.getValues(), t2.getValues()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final BinaryExpression b, Visitor arg) {
    final BinaryExpression b2 = (BinaryExpression) arg;
    return objectEquals(b.getLeftExpression(), b2.getLeftExpression()) &&
        objectEquals(b.getRightExpression(), b2.getRightExpression()) &&
        objectEquals(b.getOperator(), b2.getOperator()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final BooleanExpression b, Visitor arg) {
    final BooleanExpression b2 = (BooleanExpression) arg;
    return objectEquals(b.getExpression(), b2.getExpression()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final BooleanLiteral b, Visitor arg) {
    final BooleanLiteral b2 = (BooleanLiteral) arg;
    return objectEquals(b.getValue(), b2.getValue()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final Invocation c, Visitor arg) {
    final Invocation c2 = (Invocation) arg;
    return objectEquals(c.getScope(), c2.getScope()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectEquals(c.isImplicitThis(), c2.isImplicitThis()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CastExpression c, Visitor arg) {
    final CastExpression c2 = (CastExpression) arg;
    return objectEquals(c.getExpression(), c2.getExpression()) &&
        objectEquals(c.isCoerce(), c2.isCoerce()) &&
        objectEquals(c.isIgnoreAutoboxing(), c2.isIgnoreAutoboxing()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CharacterLiteral c, Visitor arg) {
    final CharacterLiteral c2 = (CharacterLiteral) arg;
    return objectEquals(c.getValue(), c2.getValue()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ClassExpression c, Visitor arg) {
    final ClassExpression c2 = (ClassExpression) arg;
    return objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ObjectCreationExpr c, Visitor arg) {
    final ObjectCreationExpr c2 = (ObjectCreationExpr) arg;
    return objectEquals(c.getScope(), c2.getScope()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectsEquals(c.getTypeArguments(), c2.getTypeArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DeclarationExpression d, Visitor arg) {
    final DeclarationExpression d2 = (DeclarationExpression) arg;
    return objectEquals(d.getVariable(), d2.getVariable()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final DoubleLiteral d, Visitor arg) {
    final DoubleLiteral d2 = (DoubleLiteral) arg;
    return objectEquals(d.getValue(), d2.getValue()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final EmptyExpression e, Visitor arg) {
    final EmptyExpression e2 = (EmptyExpression) arg;
    return objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(final FieldExpression f, Visitor arg) {
    final FieldExpression f2 = (FieldExpression) arg;
    return objectEquals(f.getScope(), f2.getScope()) &&
        objectEquals(f.getIdentifier(), f2.getIdentifier()) &&
        objectEquals(f.isImplicitThis(), f2.isImplicitThis()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final IntegerLiteral i, Visitor arg) {
    final IntegerLiteral i2 = (IntegerLiteral) arg;
    return objectEquals(i.getValue(), i2.getValue()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final ListExpression<?> l, Visitor arg) {
    final ListExpression<?> l2 = (ListExpression<?>) arg;
    return objectEquals(l.getValues(), l2.getValues()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final LongLiteral l, Visitor arg) {
    final LongLiteral l2 = (LongLiteral) arg;
    return objectEquals(l.getValue(), l2.getValue()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final MapEntryExpression m, Visitor arg) {
    final MapEntryExpression m2 = (MapEntryExpression) arg;
    return objectEquals(m.getKeyExpression(), m2.getKeyExpression()) &&
        objectEquals(m.getValueExpression(), m2.getValueExpression()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final MapExpression m, Visitor arg) {
    final MapExpression m2 = (MapExpression) arg;
    return objectEquals(m.getMapEntry(), m2.getMapEntry()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final MethodCallExpression m, Visitor arg) {
    final MethodCallExpression m2 = (MethodCallExpression) arg;
    return objectEquals(m.getScope(), m2.getScope()) &&
        objectEquals(m.getIdentifier(), m2.getIdentifier()) &&
        objectEquals(m.getArguments(), m2.getArguments()) &&
        objectsEquals(m.getTypeArguments(), m2.getTypeArguments()) &&
        objectEquals(m.isImplicitThis(), m2.isImplicitThis()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final NewArrayExpression n, Visitor arg) {
    final NewArrayExpression n2 = (NewArrayExpression) arg;
    return objectEquals(n.getArrayInitializer(), n2.getArrayInitializer()) &&
        objectsEquals(n.getLevels(), n2.getLevels()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final NotExpression n, Visitor arg) {
    final NotExpression n2 = (NotExpression) arg;
    return objectEquals(n.getExpression(), n2.getExpression()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final NullLiteral n, Visitor arg) {
    final NullLiteral n2 = (NullLiteral) arg;
    return objectEquals(n.getValue(), n2.getValue()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final PostfixExpression p, Visitor arg) {
    final PostfixExpression p2 = (PostfixExpression) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperator(), p2.getOperator()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final PrefixExpression p, Visitor arg) {
    final PrefixExpression p2 = (PrefixExpression) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperator(), p2.getOperator()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final SkipExpression s, Visitor arg) {
    final SkipExpression s2 = (SkipExpression) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final StopExpression s, Visitor arg) {
    final StopExpression s2 = (StopExpression) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final TernaryExpression t, Visitor arg) {
    final TernaryExpression t2 = (TernaryExpression) arg;
    return objectEquals(t.getCondition(), t2.getCondition()) &&
        objectEquals(t.getTrueExpression(), t2.getTrueExpression()) &&
        objectEquals(t.getFalseExpression(), t2.getFalseExpression()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final UnaryMinusExpression u, Visitor arg) {
    final UnaryMinusExpression u2 = (UnaryMinusExpression) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final UnaryPlusExpression u, Visitor arg) {
    final UnaryPlusExpression u2 = (UnaryPlusExpression) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final VariableExpression v, Visitor arg) {
    final VariableExpression v2 = (VariableExpression) arg;
    return objectEquals(v.getIdentifier(), v2.getIdentifier()) &&
        objectEquals(v.getAccessVariable(), v2.getAccessVariable()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final FloatLiteral f, Visitor arg) {
    final FloatLiteral f2 = (FloatLiteral) arg;
    return objectEquals(f.getValue(), f2.getValue()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final StringLiteral s, Visitor arg) {
    final StringLiteral s2 = (StringLiteral) arg;
    return objectEquals(s.getValue(), s2.getValue()) &&
        objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final BlockExpression b, Visitor arg) {
    final BlockExpression b2 = (BlockExpression) arg;
    return objectEquals(b.getStatements(), b2.getStatements()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelReadExpression c, Visitor arg) {
    final ChannelReadExpression c2 = (ChannelReadExpression) arg;
    return objectEquals(c.getChannel(), c2.getChannel()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelWriteExpression c, Visitor arg) {
    final ChannelWriteExpression c2 = (ChannelWriteExpression) arg;
    return objectEquals(c.getChannel(), c2.getChannel()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final GroupExpression g, Visitor arg) {
    final GroupExpression g2 = (GroupExpression) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.getComment(), g2.getComment());
  }

  @Override
  public Boolean visit(final ArrayAccess a, Visitor arg) {
    final ArrayAccess a2 = (ArrayAccess) arg;
    return objectEquals(a.getName(), a2.getName()) &&
        objectEquals(a.getIndex(), a2.getIndex()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final RecordLiteral r, Visitor arg) {
    final RecordLiteral r2 = (RecordLiteral) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectEquals(r.getMembers(), r2.getMembers()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final RecordMemberLiteral r, Visitor arg) {
    final RecordMemberLiteral r2 = (RecordMemberLiteral) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolLiteral p, Visitor arg) {
    final ProtocolLiteral p2 = (ProtocolLiteral) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getTag(), p2.getTag()) &&
        objectEquals(p.getMembers(), p2.getMembers()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final BlockComment b, Visitor arg) {
    final BlockComment b2 = (BlockComment) arg;
    return objectEquals(b.getContent(), b2.getContent()) &&
        objectEquals(b.getCommentedNode(), b2.getCommentedNode()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final LineComment l, Visitor arg) {
    final LineComment l2 = (LineComment) arg;
    return objectEquals(l.getContent(), l2.getContent()) &&
        objectEquals(l.getCommentedNode(), l2.getCommentedNode()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final ArrayType a, Visitor arg) {
    final ArrayType a2 = (ArrayType) arg;
    return objectEquals(a.getElementType(), a2.getElementType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BarrierType b, Visitor arg) {
    final BarrierType b2 = (BarrierType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ByteType b, Visitor arg) {
    final ByteType b2 = (ByteType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelEndType c, Visitor arg) {
    final ChannelEndType c2 = (ChannelEndType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelType c, Visitor arg) {
    final ChannelType c2 = (ChannelType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CharType c, Visitor arg) {
    final CharType c2 = (CharType) arg;
    return objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DoubleType d, Visitor arg) {
    final DoubleType d2 = (DoubleType) arg;
    return objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final ExternalType e, Visitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final FloatType f, Visitor arg) {
    final FloatType f2 = (FloatType) arg;
    return objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final IntegerType i, Visitor arg) {
    final IntegerType i2 = (IntegerType) arg;
    return objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final LongType l, Visitor arg) {
    final LongType l2 = (LongType) arg;
    return objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final MobileType m, Visitor arg) {
    final MobileType m2 = (MobileType) arg;
    return objectEquals(m.doesYield(), m2.doesYield()) &&
        objectEquals(m.getReturnType(), m2.getReturnType()) &&
        objectEquals(m.getParameterTypes(), m2.getParameterTypes()) &&
        objectsEquals(m.getImplementedTypes(), m2.getImplementedTypes()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final NullType n, Visitor arg) {
    final NullType n2 = (NullType) arg;
    return objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final ProcedureType p, Visitor arg) {
    final ProcedureType p2 = (ProcedureType) arg;
    return objectEquals(p.doesYield(), p2.doesYield()) &&
        objectEquals(p.getReturnType(), p2.getReturnType()) &&
        objectEquals(p.getParameterTypes(), p2.getParameterTypes()) &&
        objectsEquals(p.getImplementedTypes(), p2.getImplementedTypes()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolTagType p, Visitor arg) {
    final ProtocolTagType p2 = (ProtocolTagType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getTypeFields(), p2.getTypeFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolType p, Visitor arg) {
    final ProtocolType p2 = (ProtocolType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getFieldTypes(), p2.getFieldTypes()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final RecordType r, Visitor arg) {
    final RecordType r2 = (RecordType) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectsEquals(r.getTypeFields(), r2.getTypeFields()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ShortType s, Visitor arg) {
    final ShortType s2 = (ShortType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final BooleanType b, Visitor arg) {
    final BooleanType b2 = (BooleanType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final StringType s, Visitor arg) {
    final StringType s2 = (StringType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final TimerType t, Visitor arg) {
    final TimerType t2 = (TimerType) arg;
    return objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final UnknownType u, Visitor arg) {
    final UnknownType u2 = (UnknownType) arg;
    return objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final VoidType v, Visitor arg) {
    final VoidType v2 = (VoidType) arg;
    return objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final NamedType t, Visitor arg) {
    final NamedType t2 = (NamedType) arg;
    return objectEquals(t.getName(), t2.getName()) &&
        objectEquals(t.getComment(), t2.getComment());
  }
}
