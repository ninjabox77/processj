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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Visitor used to check for equality in the AST.
 *
 * @author Ben
 */
public class EqualsVisitor implements GenericVisitor<Boolean, CodeVisitor> {

  private static final EqualsVisitor INSTANCE = new EqualsVisitor();

  private EqualsVisitor() {
  }

  public static boolean equals(final Node n1, final Node n2) {
    return INSTANCE.objectEquals(n1, n2);
  }

  private <N extends Node> Boolean objectEquals(final Sequence<N> nodes1, final Sequence<N> nodes2) {
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

  private <N extends Node> Boolean objectEquals(final N n1, final N n2) {
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

  private <N extends Node> Boolean objectEquals(final Optional<N> n1, final Optional<N> n2) {
    return objectEquals(n1.orElse(null), n2.orElse(null));
  }

  private <N extends Node> Boolean objectsEquals(final Optional<Sequence<N>> n1, final Optional<Sequence<N>> n2) {
    return objectEquals(n1.orElse(null), n2.orElse(null));
  }

  @Override
  public Boolean visit(final AnnotationNode a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ArrayDimension a, CodeVisitor arg) {
    final ArrayDimension a2 = (ArrayDimension) arg;
    return objectEquals(a.getDimension(), a2.getDimension()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final ClassNode c, CodeVisitor arg) {
    final ClassNode c2 = (ClassNode) arg;
    return objectEquals(c.getName(), c2.getName()) &&
        Arrays.equals(c.getBytes(), c2.getBytes()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CompileUnit c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Import i, CodeVisitor arg) {
    final Import i2 = (Import) arg;
    return objectEquals(i.getName(), i2.getName()) &&
        objectEquals(i.getAlias(), i2.getAlias()) &&
        objectEquals(i.getFieldName(), i2.getFieldName()) &&
        objectEquals(i.isJavaImport(), i2.isJavaImport()) &&
        objectEquals(i.isStar(), i2.isStar()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final Name n, CodeVisitor arg) {
    final Name n2 = (Name) arg;
    return objectEquals(n.getIdentifier(), n2.getIdentifier()) &&
        objectEquals(n.getQualifier(), n2.getQualifier()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final Package p, CodeVisitor arg) {
    final Package p2 = (Package) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final Parameter p, CodeVisitor arg) {
    final Parameter p2 = (Parameter) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final Sequence<?> s, CodeVisitor arg) {
    return objectEquals(s, arg);
  }

  @Override
  public Boolean visit(final VariableDecl v, CodeVisitor arg) {
    final VariableDecl v2 = (VariableDecl) arg;
    return objectEquals(v.getModifiers(), v2.getModifiers()) &&
        objectEquals(v.getASTType(), v2.getASTType()) &&
        objectEquals(v.getName(), v2.getName()) &&
        objectEquals(v.getInitialExpression(), v2.getInitialExpression()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final ArrayNode a, CodeVisitor arg) {
    final ArrayNode a2 = (ArrayNode) arg;
    return objectEquals(a.getTSType(), a2.getTSType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BarrierNode b, CodeVisitor arg) {
    final BarrierNode b2 = (BarrierNode) arg;
    return objectEquals(b.getTSType(), b2.getTSType()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelEndNode c, CodeVisitor arg) {
    final ChannelEndNode c2 = (ChannelEndNode) arg;
    return objectEquals(c.getTSType(), c2.getTSType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelNode c, CodeVisitor arg) {
    final ChannelNode c2 = (ChannelNode) arg;
    return objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ExternalNode e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final PrimitiveNode p, CodeVisitor arg) {
    final PrimitiveNode p2 = (PrimitiveNode) arg;
    return objectEquals(p.getTSType(), p2.getTSType()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final TimerNode t, CodeVisitor arg) {
    final TimerNode t2 = (TimerNode) arg;
    return objectEquals(t.getTSType(), t2.getTSType()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final VoidNode v, CodeVisitor arg) {
    final VoidNode v2 = (VoidNode) arg;
    return objectEquals(v.getTSType(), v2.getTSType()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final ConstructedNode c, CodeVisitor arg) {
    final ConstructedNode c2 = (ConstructedNode) arg;
    return objectEquals(c.getTSType(), c2.getTSType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ErrorNode e, CodeVisitor arg) {
    final ErrorNode e2 = (ErrorNode) arg;
    return objectEquals(e.getTSType(), e2.getTSType()) &&
        objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(final ConstantDecl c, CodeVisitor arg) {
    final ConstantDecl c2 = (ConstantDecl) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectEquals(c.getRightExpression(), c2.getRightExpression()) &&
        objectsEquals(c.getImplementedNames(), c2.getImplementedNames()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ExternalDecl e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final CallableDecl<?> m, CodeVisitor arg) {
    final CallableDecl<?> m2 = (CallableDecl<?>) arg;
    return objectEquals(m.getModifiers(), m2.getModifiers()) &&
        objectEquals(m.getASTType(), m2.getASTType()) &&
        objectEquals(m.getName(), m2.getName()) &&
        objectEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getImplementedNames(), m2.getImplementedNames()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final ProcedureTopLevel p, CodeVisitor arg) {
    final ProcedureTopLevel p2 = (ProcedureTopLevel) arg;
    return objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getParameters(), p2.getParameters()) &&
        objectEquals(p.getBody(), p2.getBody()) &&
        objectEquals(p.doesYield(), p2.doesYield()) &&
        objectsEquals(p.getImplementedNames(), p2.getImplementedNames()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolCaseDecl p, CodeVisitor arg) {
    final ProtocolCaseDecl p2 = (ProtocolCaseDecl) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getDeclaredFields(), p2.getDeclaredFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolDecl p, CodeVisitor arg) {
    final ProtocolDecl p2 = (ProtocolDecl) arg;
    return objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getDeclaredTags(), p2.getDeclaredTags()) &&
        objectsEquals(p.getImplementedNames(), p2.getImplementedNames()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final RecordDecl r, CodeVisitor arg) {
    final RecordDecl r2 = (RecordDecl) arg;
    return objectEquals(r.getModifiers(), r2.getModifiers()) &&
        objectEquals(r.getASTType(), r2.getASTType()) &&
        objectEquals(r.getName(), r2.getName()) &&
        objectsEquals(r.getDeclaredFields(), r2.getDeclaredFields()) &&
        objectsEquals(r.getImplementedNames(), r2.getImplementedNames()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final TypeVariableDecl t, CodeVisitor arg) {
    final TypeVariableDecl t2 = (TypeVariableDecl) arg;
    return objectEquals(t.getModifiers(), t2.getModifiers()) &&
        objectEquals(t.getASTType(), t2.getASTType()) &&
        objectEquals(t.getName(), t2.getName()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final BlockStmt b, CodeVisitor arg) {
    final BlockStmt b2 = (BlockStmt) arg;
    return objectEquals(b.getStatements(), b2.getStatements()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(final BreakStmt b, CodeVisitor arg) {
    final BreakStmt b2 = (BreakStmt) arg;
    return objectEquals(b.getLabel(), b2.getLabel()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(final SwitchCaseStmt c, CodeVisitor arg) {
    final SwitchCaseStmt c2 = (SwitchCaseStmt) arg;
    return objectEquals(c.getExpressions(), c2.getExpressions()) &&
        objectEquals(c.getStatements(), c2.getStatements()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ContinueStmt c, CodeVisitor arg) {
    final ContinueStmt c2 = (ContinueStmt) arg;
    return objectEquals(c.getLabel(), c2.getLabel()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DoWhileStmt d, CodeVisitor arg) {
    final DoWhileStmt d2 = (DoWhileStmt) arg;
    return objectEquals(d.getCondition(), d2.getCondition()) &&
        objectEquals(d.getLoopBlock(), d2.getLoopBlock()) &&
        objectsEquals(d.getStatementLabels(), d2.getStatementLabels()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final EmptyStmt e, CodeVisitor arg) {
    final EmptyStmt e2 = (EmptyStmt) arg;
    return objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ExpressionStmt e, CodeVisitor arg) {
    final ExpressionStmt e2 = (ExpressionStmt) arg;
    return objectEquals(e.getExpression(), e2.getExpression()) &&
        objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ForEachStmt f, CodeVisitor arg) {
    final ForEachStmt f2 = (ForEachStmt) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getCollectionExpr(), f2.getCollectionExpr()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ForStmt f, CodeVisitor arg) {
    final ForStmt f2 = (ForStmt) arg;
    return objectEquals(f.getInitialization(), f2.getInitialization()) &&
        objectEquals(f.getConditional(), f2.getConditional()) &&
        objectEquals(f.getUpdate(), f2.getUpdate()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(final Guard g, CodeVisitor arg) {
    final Guard g2 = (Guard) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.isSkip(), g2.isSkip()) &&
        objectEquals(g.getComment(), g2.getComment());
  }

  @Override
  public Boolean visit(final IfStmt i, CodeVisitor arg) {
    final IfStmt i2 = (IfStmt) arg;
    return objectEquals(i.getConditional(), i2.getConditional()) &&
        objectEquals(i.getIfPart(), i2.getIfPart()) &&
        objectEquals(i.getElsePart(), i2.getElsePart()) &&
        objectEquals(i.getComment(), i2.getComment()) &&
        objectsEquals(i.getStatementLabels(), i2.getStatementLabels());
  }

  @Override
  public Boolean visit(final RegularAltStmt r, CodeVisitor arg) {
    final RegularAltStmt r2 = (RegularAltStmt) arg;
    return objectsEquals(r.getAltCases(), r2.getAltCases()) &&
        objectEquals(r.getAltCases(), r2.getAltCases()) &&
        objectEquals(r.isFairAlt(), r2.isFairAlt()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ReturnStmt r, CodeVisitor arg) {
    final ReturnStmt r2 = (ReturnStmt) arg;
    return objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(final SwitchStmt s, CodeVisitor arg) {
    final SwitchStmt s2 = (SwitchStmt) arg;
    return objectEquals(s.getSelector(), s2.getSelector()) &&
        objectsEquals(s.getSwitchCases(), s2.getSwitchCases()) &&
        objectEquals(s.getDefaultStmt(), s2.getDefaultStmt()) &&
        objectEquals(s.getComment(), s2.getComment()) &&
        objectsEquals(s.getStatementLabels(), s2.getStatementLabels());
  }

  @Override
  public Boolean visit(final WhileStmt w, CodeVisitor arg) {
    final WhileStmt w2 = (WhileStmt) arg;
    return objectEquals(w.getCondition(), w2.getCondition()) &&
        objectEquals(w.getLoopBlock(), w2.getLoopBlock()) &&
        objectEquals(w.getComment(), w2.getComment()) &&
        objectsEquals(w.getStatementLabels(), w2.getStatementLabels());
  }

  @Override
  public Boolean visit(final ReplicatedAltStmt r, CodeVisitor arg) {
    final ReplicatedAltStmt r2 = (ReplicatedAltStmt) arg;
    return objectsEquals(r.getStatementLabels(), r2.getStatementLabels()) &&
        objectsEquals(r.getAltCases(), r2.getAltCases()) &&
        objectEquals(r.getInitialization(), r2.getInitialization()) &&
        objectEquals(r.getConditional(), r2.getConditional()) &&
        objectEquals(r.getUpdate(), r2.getUpdate()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final AltCase a, CodeVisitor arg) {
    final AltCase a2 = (AltCase) arg;
    return objectEquals(a.getExpression(), a2.getExpression()) &&
        objectEquals(a.getGuard(), a2.getGuard()) &&
        objectEquals(a.getStatement(), a2.getStatement()) &&
        objectEquals(a.getStatementLabels(), a2.getStatementLabels()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final RegularParBlock r, CodeVisitor arg) {
    final RegularParBlock r2 = (RegularParBlock) arg;
    return objectEquals(r.getBarriers(), r2.getBarriers()) &&
        objectEquals(r.getStatements(), r2.getStatements()) &&
        objectEquals(r.getStatementLabels(), r2.getStatements()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ParForBlock p, CodeVisitor arg) {
    final ParForBlock p2 = (ParForBlock) arg;
    return objectEquals(p.getInitialization(), p2.getInitialization()) &&
        objectEquals(p.getConditional(), p2.getConditional()) &&
        objectEquals(p.getUpdate(), p2.getUpdate()) &&
        objectEquals(p.getStatements(), p2.getStatements()) &&
        objectEquals(p.getStatementLabels(), p2.getStatementLabels()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ClassDeclaration c, CodeVisitor arg) {
    final ClassDeclaration c2 = (ClassDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectsEquals(c.getDeclarations(), c2.getDeclarations()) &&
        objectEquals(c.isInterface(), c2.isInterface()) &&
        objectsEquals(c.getExtendedClasses(), c2.getExtendedClasses()) &&
        objectsEquals(c.getImplementedClasses(), c2.getImplementedClasses());
  }

  @Override
  public Boolean visit(final ConstructorDeclaration c, CodeVisitor arg) {
    final ConstructorDeclaration c2 = (ConstructorDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectEquals(c.getBody(), c2.getBody()) &&
        objectsEquals(c.getParameters(), c2.getParameters()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final FieldDeclaration f, CodeVisitor arg) {
    final FieldDeclaration f2 = (FieldDeclaration) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final MethodDeclaration<?> m, CodeVisitor arg) {
    final MethodDeclaration<?> m2 = (MethodDeclaration<?>) arg;
    return objectEquals(m.getASTType(), m2.getASTType()) &&
        objectEquals(m.getName(), m2.getName()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final ArrayInitializer a, CodeVisitor arg) {
    final ArrayInitializer a2 = (ArrayInitializer) arg;
    return objectsEquals(a.getValues(), a2.getValues()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BinaryExpr b, CodeVisitor arg) {
    final BinaryExpr b2 = (BinaryExpr) arg;
    return objectEquals(b.getLeftExpression(), b2.getLeftExpression()) &&
        objectEquals(b.getRightExpression(), b2.getRightExpression()) &&
        objectEquals(b.getOperation(), b2.getOperation()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final BooleanExpr b, CodeVisitor arg) {
    final BooleanExpr b2 = (BooleanExpr) arg;
    return objectEquals(b.getExpression(), b2.getExpression()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final BooleanLiteral b, CodeVisitor arg) {
    final BooleanLiteral b2 = (BooleanLiteral) arg;
    return objectEquals(b.getValue(), b2.getValue()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final CallableExpr c, CodeVisitor arg) {
    final CallableExpr c2 = (CallableExpr) arg;
    return objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectEquals(c.isImplicitThis(), c2.isImplicitThis()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CastExpr c, CodeVisitor arg) {
    final CastExpr c2 = (CastExpr) arg;
    return objectEquals(c.getExpression(), c2.getExpression()) &&
        objectEquals(c.isCoerce(), c2.isCoerce()) &&
        objectEquals(c.isIgnoreAutoboxing(), c2.isIgnoreAutoboxing()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CharLiteral c, CodeVisitor arg) {
    final CharLiteral c2 = (CharLiteral) arg;
    return objectEquals(c.getValue(), c2.getValue()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ClassExpr c, CodeVisitor arg) {
    final ClassExpr c2 = (ClassExpr) arg;
    return objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ObjectCreationExpr c, CodeVisitor arg) {
    final ObjectCreationExpr c2 = (ObjectCreationExpr) arg;
    return objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
        objectEquals(c.getIdentifier(), c2.getIdentifier()) &&
        objectsEquals(c.getArguments(), c2.getArguments()) &&
        objectsEquals(c.getTypeArguments(), c2.getTypeArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DeclarationExpr d, CodeVisitor arg) {
    final DeclarationExpr d2 = (DeclarationExpr) arg;
    return objectEquals(d.getDeclaration(), d2.getDeclaration()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final DoubleLiteral d, CodeVisitor arg) {
    final DoubleLiteral d2 = (DoubleLiteral) arg;
    return objectEquals(d.getValue(), d2.getValue()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final EmptyExpr e, CodeVisitor arg) {
    final EmptyExpr e2 = (EmptyExpr) arg;
    return objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(final FieldExpr f, CodeVisitor arg) {
    final FieldExpr f2 = (FieldExpr) arg;
    return objectEquals(f.getFieldExpression(), f2.getFieldExpression()) &&
        objectEquals(f.getIdentifier(), f2.getIdentifier()) &&
        objectEquals(f.isImplicitThis(), f2.isImplicitThis()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final IntegerLiteral i, CodeVisitor arg) {
    final IntegerLiteral i2 = (IntegerLiteral) arg;
    return objectEquals(i.getValue(), i2.getValue()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final ListExpression<?> l, CodeVisitor arg) {
    final ListExpression<?> l2 = (ListExpression<?>) arg;
    return objectsEquals(l.getValues(), l2.getValues()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final LongLiteral l, CodeVisitor arg) {
    final LongLiteral l2 = (LongLiteral) arg;
    return objectEquals(l.getValue(), l2.getValue()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final MapEntryExpr m, CodeVisitor arg) {
    final MapEntryExpr m2 = (MapEntryExpr) arg;
    return objectEquals(m.getKeyExpression(), m2.getKeyExpression()) &&
        objectEquals(m.getValueExpression(), m2.getValueExpression()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final MapExpr m, CodeVisitor arg) {
    final MapExpr m2 = (MapExpr) arg;
    return objectsEquals(m.getMapEntry(), m2.getMapEntry()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final MethodCallExpr m, CodeVisitor arg) {
    final MethodCallExpr m2 = (MethodCallExpr) arg;
    return objectEquals(m.getMethodExpression(), m2.getMethodExpression()) &&
        objectEquals(m.getIdentifier(), m2.getIdentifier()) &&
        objectsEquals(m.getArguments(), m2.getArguments()) &&
        objectsEquals(m.getTypeArguments(), m2.getTypeArguments()) &&
        objectEquals(m.getImplicitThis(), m2.getImplicitThis()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final NewArrayExpr n, CodeVisitor arg) {
    final NewArrayExpr n2 = (NewArrayExpr) arg;
    return objectEquals(n.getArrayLiteral(), n2.getArrayLiteral()) &&
        objectsEquals(n.getLevels(), n2.getLevels()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final NotExpression n, CodeVisitor arg) {
    final NotExpression n2 = (NotExpression) arg;
    return objectEquals(n.getExpression(), n2.getExpression()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final NullLiteral n, CodeVisitor arg) {
    final NullLiteral n2 = (NullLiteral) arg;
    return objectEquals(n.getValue(), n2.getValue()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final PostfixExpr p, CodeVisitor arg) {
    final PostfixExpr p2 = (PostfixExpr) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperation(), p2.getOperation()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final PrefixExpr p, CodeVisitor arg) {
    final PrefixExpr p2 = (PrefixExpr) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperation(), p2.getOperation()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final SkipExpr s, CodeVisitor arg) {
    final SkipExpr s2 = (SkipExpr) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final StopExpr s, CodeVisitor arg) {
    final StopExpr s2 = (StopExpr) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final TernaryExpr t, CodeVisitor arg) {
    final TernaryExpr t2 = (TernaryExpr) arg;
    return objectEquals(t.getCondition(), t2.getCondition()) &&
        objectEquals(t.getTrueExpression(), t2.getTrueExpression()) &&
        objectEquals(t.getFalseExpression(), t2.getFalseExpression()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final UnaryMinusExpr u, CodeVisitor arg) {
    final UnaryMinusExpr u2 = (UnaryMinusExpr) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final UnaryPlusExpr u, CodeVisitor arg) {
    final UnaryPlusExpr u2 = (UnaryPlusExpr) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final VariableExpr v, CodeVisitor arg) {
    final VariableExpr v2 = (VariableExpr) arg;
    return objectEquals(v.getName(), v2.getName()) &&
        objectEquals(v.getAccessVariable(), v2.getAccessVariable()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final FloatLiteral f, CodeVisitor arg) {
    final FloatLiteral f2 = (FloatLiteral) arg;
    return objectEquals(f.getValue(), f2.getValue()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final StringLiteral s, CodeVisitor arg) {
    final StringLiteral s2 = (StringLiteral) arg;
    return objectEquals(s.getValue(), s2.getValue()) &&
        objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final BlockExpr b, CodeVisitor arg) {
    final BlockExpr b2 = (BlockExpr) arg;
    return objectEquals(b.getStatements(), b2.getStatements()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelReadExpr c, CodeVisitor arg) {
    final ChannelReadExpr c2 = (ChannelReadExpr) arg;
    return objectEquals(c.getChannel(), c2.getChannel()) &&
        objectEquals(c.getExternalRV(), c2.getExternalRV()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelWriteExpr c, CodeVisitor arg) {
    final ChannelWriteExpr c2 = (ChannelWriteExpr) arg;
    return objectEquals(c.getChannel(), c2.getChannel()) &&
        objectEquals(c.getExpression(), c2.getExpression()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final GroupExpr g, CodeVisitor arg) {
    final GroupExpr g2 = (GroupExpr) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.getComment(), g2.getComment());
  }

  @Override
  public Boolean visit(final ArrayAccess a, CodeVisitor arg) {
    final ArrayAccess a2 = (ArrayAccess) arg;
    return objectEquals(a.getName(), a2.getName()) &&
        objectEquals(a.getIndex(), a2.getIndex()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final RecordLiteral r, CodeVisitor arg) {
    final RecordLiteral r2 = (RecordLiteral) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectEquals(r.getMembers(), r2.getMembers()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final RecordMemberLiteral r, CodeVisitor arg) {
    final RecordMemberLiteral r2 = (RecordMemberLiteral) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolLiteral p, CodeVisitor arg) {
    final ProtocolLiteral p2 = (ProtocolLiteral) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getTag(), p2.getTag()) &&
        objectEquals(p.getMembers(), p2.getMembers()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final BlockComment b, CodeVisitor arg) {
    final BlockComment b2 = (BlockComment) arg;
    return objectEquals(b.getContent(), b2.getContent()) &&
        objectEquals(b.getCommentedNode(), b2.getCommentedNode()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final LineComment l, CodeVisitor arg) {
    final LineComment l2 = (LineComment) arg;
    return objectEquals(l.getContent(), l2.getContent()) &&
        objectEquals(l.getCommentedNode(), l2.getCommentedNode()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final ArrayType a, CodeVisitor arg) {
    final ArrayType a2 = (ArrayType) arg;
    return objectEquals(a.getElementType(), a2.getElementType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(final BarrierType b, CodeVisitor arg) {
    final BarrierType b2 = (BarrierType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ByteType b, CodeVisitor arg) {
    final ByteType b2 = (ByteType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final ChannelEndType c, CodeVisitor arg) {
    final ChannelEndType c2 = (ChannelEndType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ChannelType c, CodeVisitor arg) {
    final ChannelType c2 = (ChannelType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final CharType c, CodeVisitor arg) {
    final CharType c2 = (CharType) arg;
    return objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DoubleType d, CodeVisitor arg) {
    final DoubleType d2 = (DoubleType) arg;
    return objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(final ExternalType e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final FloatType f, CodeVisitor arg) {
    final FloatType f2 = (FloatType) arg;
    return objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final IntegerType i, CodeVisitor arg) {
    final IntegerType i2 = (IntegerType) arg;
    return objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final LongType l, CodeVisitor arg) {
    final LongType l2 = (LongType) arg;
    return objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(final MobileType m, CodeVisitor arg) {
    final MobileType m2 = (MobileType) arg;
    return objectEquals(m.doesYield(), m2.doesYield()) &&
        objectEquals(m.getTSType(), m2.getTSType()) &&
        objectsEquals(m.getTypeArguments(), m2.getTypeArguments()) &&
        objectsEquals(m.getImplementedTypes(), m2.getImplementedTypes()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final NullType n, CodeVisitor arg) {
    final NullType n2 = (NullType) arg;
    return objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(final ProcedureType p, CodeVisitor arg) {
    final ProcedureType p2 = (ProcedureType) arg;
    return objectEquals(p.doesYield(), p2.doesYield()) &&
        objectEquals(p.getTSType(), p2.getTSType()) &&
        objectsEquals(p.getTypeArguments(), p2.getTypeArguments()) &&
        objectsEquals(p.getImplementedTypes(), p2.getImplementedTypes()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolTagType p, CodeVisitor arg) {
    final ProtocolTagType p2 = (ProtocolTagType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getTypeFields(), p2.getTypeFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final ProtocolType p, CodeVisitor arg) {
    final ProtocolType p2 = (ProtocolType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getTypeFields(), p2.getTypeFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final RecordType r, CodeVisitor arg) {
    final RecordType r2 = (RecordType) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectsEquals(r.getTypeFields(), r2.getTypeFields()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ShortType s, CodeVisitor arg) {
    final ShortType s2 = (ShortType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final BooleanType b, CodeVisitor arg) {
    final BooleanType b2 = (BooleanType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(final StringType s, CodeVisitor arg) {
    final StringType s2 = (StringType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final TimerType t, CodeVisitor arg) {
    final TimerType t2 = (TimerType) arg;
    return objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(final UnknownType u, CodeVisitor arg) {
    final UnknownType u2 = (UnknownType) arg;
    return objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(final VoidType v, CodeVisitor arg) {
    final VoidType v2 = (VoidType) arg;
    return objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(final TypeVariable t, CodeVisitor arg) {
    final TypeVariable t2 = (TypeVariable) arg;
    return objectEquals(t.getName(), t2.getName()) &&
        objectEquals(t.getComment(), t2.getComment());
  }
}
