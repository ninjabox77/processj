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
  public Boolean visit(AnnotationNode a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ArrayDimension a, CodeVisitor arg) {
    final ArrayDimension a2 = (ArrayDimension) arg;
    return objectEquals(a.getDimension(), a2.getDimension()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(ClassNode c, CodeVisitor arg) {
    final ClassNode c2 = (ClassNode) arg;
    return objectEquals(c.getName(), c2.getName()) &&
        Arrays.equals(c.getBytes(), c2.getBytes()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(CompileUnit c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Import i, CodeVisitor arg) {
    final Import i2 = (Import) arg;
    return objectEquals(i.getName(), i2.getName()) &&
        objectEquals(i.getAlias(), i2.getAlias()) &&
        objectEquals(i.getFieldName(), i2.getFieldName()) &&
        objectEquals(i.isJavaImport(), i2.isJavaImport()) &&
        objectEquals(i.isStar(), i2.isStar()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(Name n, CodeVisitor arg) {
    final Name n2 = (Name) arg;
    return objectEquals(n.getIdentifier(), n2.getIdentifier()) &&
        objectEquals(n.getQualifier(), n2.getQualifier()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(Package p, CodeVisitor arg) {
    final Package p2 = (Package) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(Parameter p, CodeVisitor arg) {
    final Parameter p2 = (Parameter) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(Sequence<?> s, CodeVisitor arg) {
    return objectEquals(s, arg);
  }

  @Override
  public Boolean visit(ArrayNode a, CodeVisitor arg) {
    final ArrayNode a2 = (ArrayNode) arg;
    return objectEquals(a.getTSType(), a2.getTSType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(BarrierNode b, CodeVisitor arg) {
    final BarrierNode b2 = (BarrierNode) arg;
    return objectEquals(b.getTSType(), b2.getTSType()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(ChannelEndNode c, CodeVisitor arg) {
    final ChannelEndNode c2 = (ChannelEndNode) arg;
    return objectEquals(c.getTSType(), c2.getTSType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ChannelNode c, CodeVisitor arg) {
    final ChannelNode c2 = (ChannelNode) arg;
    return objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ExternalNode e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(PrimitiveNode p, CodeVisitor arg) {
    final PrimitiveNode p2 = (PrimitiveNode) arg;
    return objectEquals(p.getTSType(), p2.getTSType()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(TimerNode t, CodeVisitor arg) {
    final TimerNode t2 = (TimerNode) arg;
    return objectEquals(t.getTSType(), t2.getTSType()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(ConstantTopLevel c, CodeVisitor arg) {
    final ConstantTopLevel c2 = (ConstantTopLevel) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectEquals(c.getRightExpression(), c2.getRightExpression()) &&
        objectsEquals(c.getImplementedNames(), c2.getImplementedNames()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ExternalTopLevel e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CallableTopLevel<?> m, CodeVisitor arg) {
    final CallableTopLevel<?> m2 = (CallableTopLevel<?>) arg;
    return objectEquals(m.getModifiers(), m2.getModifiers()) &&
        objectEquals(m.getASTType(), m2.getASTType()) &&
        objectEquals(m.getName(), m2.getName()) &&
        objectEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getImplementedNames(), m2.getImplementedNames()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(ProcedureTopLevel p, CodeVisitor arg) {
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
  public Boolean visit(ProtocolTagDeclaration p, CodeVisitor arg) {
    final ProtocolTagDeclaration p2 = (ProtocolTagDeclaration) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getDeclaredFields(), p2.getDeclaredFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(ProtocolTopLevel p, CodeVisitor arg) {
    final ProtocolTopLevel p2 = (ProtocolTopLevel) arg;
    return objectEquals(p.getModifiers(), p2.getModifiers()) &&
        objectEquals(p.getASTType(), p2.getASTType()) &&
        objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getDeclaredTags(), p2.getDeclaredTags()) &&
        objectsEquals(p.getImplementedNames(), p2.getImplementedNames()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(RecordTopLevel r, CodeVisitor arg) {
    final RecordTopLevel r2 = (RecordTopLevel) arg;
    return objectEquals(r.getModifiers(), r2.getModifiers()) &&
        objectEquals(r.getASTType(), r2.getASTType()) &&
        objectEquals(r.getName(), r2.getName()) &&
        objectsEquals(r.getDeclaredFields(), r2.getDeclaredFields()) &&
        objectsEquals(r.getImplementedNames(), r2.getImplementedNames()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(BlockStmt b, CodeVisitor arg) {
    final BlockStmt b2 = (BlockStmt) arg;
    return objectEquals(b.getStatements(), b2.getStatements()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(BreakStmt b, CodeVisitor arg) {
    final BreakStmt b2 = (BreakStmt) arg;
    return objectEquals(b.getLabel(), b2.getLabel()) &&
        objectEquals(b.getComment(), b2.getComment()) &&
        objectsEquals(b.getStatementLabels(), b2.getStatementLabels());
  }

  @Override
  public Boolean visit(CaseStmt c, CodeVisitor arg) {
    final CaseStmt c2 = (CaseStmt) arg;
    return objectEquals(c.getExpression(), c2.getExpression()) &&
        objectEquals(c.getStatement(), c2.getStatement()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ContinueStmt c, CodeVisitor arg) {
    final ContinueStmt c2 = (ContinueStmt) arg;
    return objectEquals(c.getLabel(), c2.getLabel()) &&
        objectsEquals(c.getStatementLabels(), c2.getStatementLabels()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(DoWhileStmt d, CodeVisitor arg) {
    final DoWhileStmt d2 = (DoWhileStmt) arg;
    return objectEquals(d.getCondition(), d2.getCondition()) &&
        objectEquals(d.getLoopBlock(), d2.getLoopBlock()) &&
        objectsEquals(d.getStatementLabels(), d2.getStatementLabels()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(EmptyStmt e, CodeVisitor arg) {
    final EmptyStmt e2 = (EmptyStmt) arg;
    return objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(ExpressionStmt e, CodeVisitor arg) {
    final ExpressionStmt e2 = (ExpressionStmt) arg;
    return objectEquals(e.getExpression(), e2.getExpression()) &&
        objectEquals(e.getComment(), e2.getComment()) &&
        objectsEquals(e.getStatementLabels(), e2.getStatementLabels());
  }

  @Override
  public Boolean visit(ForEachStmt f, CodeVisitor arg) {
    final ForEachStmt f2 = (ForEachStmt) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getCollectionExpr(), f2.getCollectionExpr()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(ForStmt f, CodeVisitor arg) {
    final ForStmt f2 = (ForStmt) arg;
    return objectEquals(f.getInitialization(), f2.getInitialization()) &&
        objectEquals(f.getCondition(), f2.getCondition()) &&
        objectEquals(f.getUpdate(), f2.getUpdate()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment()) &&
        objectsEquals(f.getStatementLabels(), f2.getStatementLabels());
  }

  @Override
  public Boolean visit(GuardStmt g, CodeVisitor arg) {
    final GuardStmt g2 = (GuardStmt) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.getStatement(), g2.getStatement()) &&
        objectEquals(g.getComment(), g2.getComment()) &&
        objectsEquals(g.getStatementLabels(), g2.getStatementLabels());
  }

  @Override
  public Boolean visit(IfStmt i, CodeVisitor arg) {
    final IfStmt i2 = (IfStmt) arg;
    return objectEquals(i.getConditional(), i2.getConditional()) &&
        objectEquals(i.getIfPart(), i2.getIfPart()) &&
        objectEquals(i.getElsePart(), i2.getElsePart()) &&
        objectEquals(i.getComment(), i2.getComment()) &&
        objectsEquals(i.getStatementLabels(), i2.getStatementLabels());
  }

  @Override
  public Boolean visit(RegularAltStmt r, CodeVisitor arg) {
    final RegularAltStmt r2 = (RegularAltStmt) arg;
    return objectsEquals(r.getGuards(), r2.getGuards()) &&
        objectEquals(r.getGuards(), r2.getGuards()) &&
        objectEquals(r.isFairAlt(), r2.isFairAlt()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(ReturnStmt r, CodeVisitor arg) {
    final ReturnStmt r2 = (ReturnStmt) arg;
    return objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment()) &&
        objectsEquals(r.getStatementLabels(), r2.getStatementLabels());
  }

  @Override
  public Boolean visit(SwitchStmt s, CodeVisitor arg) {
    final SwitchStmt s2 = (SwitchStmt) arg;
    return objectEquals(s.getSelector(), s2.getSelector()) &&
        objectsEquals(s.getSwitchCases(), s2.getSwitchCases()) &&
        objectEquals(s.getDefaultStmt(), s2.getDefaultStmt()) &&
        objectEquals(s.getComment(), s2.getComment()) &&
        objectsEquals(s.getStatementLabels(), s2.getStatementLabels());
  }

  @Override
  public Boolean visit(WhileStmt w, CodeVisitor arg) {
    final WhileStmt w2 = (WhileStmt) arg;
    return objectEquals(w.getCondition(), w2.getCondition()) &&
        objectEquals(w.getLoopBlock(), w2.getLoopBlock()) &&
        objectEquals(w.getComment(), w2.getComment()) &&
        objectsEquals(w.getStatementLabels(), w2.getStatementLabels());
  }

  @Override
  public Boolean visit(ReplicatedAltStmt r, CodeVisitor arg) {
    final ReplicatedAltStmt r2 = (ReplicatedAltStmt) arg;
    return objectsEquals(r.getStatementLabels(), r2.getStatementLabels()) &&
        objectsEquals(r.getGuards(), r2.getGuards()) &&
        objectEquals(r.getInitialization(), r2.getInitialization()) &&
        objectEquals(r.getConditional(), r2.getConditional()) &&
        objectEquals(r.getUpdate(), r2.getUpdate()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(ClassDeclaration c, CodeVisitor arg) {
    final ClassDeclaration c2 = (ClassDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectsEquals(c.getDeclarations(), c2.getDeclarations()) &&
        objectEquals(c.isInterface(), c2.isInterface()) &&
        objectsEquals(c.getExtendedClasses(), c2.getExtendedClasses()) &&
        objectsEquals(c.getImplementedClasses(), c2.getImplementedClasses());
  }

  @Override
  public Boolean visit(ConstructorDeclaration c, CodeVisitor arg) {
    final ConstructorDeclaration c2 = (ConstructorDeclaration) arg;
    return objectEquals(c.getModifiers(), c2.getModifiers()) &&
        objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectEquals(c.getBody(), c2.getBody()) &&
        objectsEquals(c.getParameters(), c2.getParameters()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(FieldDeclaration f, CodeVisitor arg) {
    final FieldDeclaration f2 = (FieldDeclaration) arg;
    return objectEquals(f.getModifiers(), f2.getModifiers()) &&
        objectEquals(f.getASTType(), f2.getASTType()) &&
        objectEquals(f.getName(), f2.getName()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(MethodDeclaration<?> m, CodeVisitor arg) {
    final MethodDeclaration<?> m2 = (MethodDeclaration<?>) arg;
    return objectEquals(m.getASTType(), m2.getASTType()) &&
        objectEquals(m.getName(), m2.getName()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(ArrayExpr a, CodeVisitor arg) {
    final ArrayExpr a2 = (ArrayExpr) arg;
    return objectsEquals(a.getValues(), a2.getValues()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(BinaryExpr b, CodeVisitor arg) {
    final BinaryExpr b2 = (BinaryExpr) arg;
    return objectEquals(b.getLeftExpression(), b2.getLeftExpression()) &&
        objectEquals(b.getRightExpression(), b2.getRightExpression()) &&
        objectEquals(b.getOperation(), b2.getOperation()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(BooleanExpr b, CodeVisitor arg) {
    final BooleanExpr b2 = (BooleanExpr) arg;
    return objectEquals(b.getExpression(), b2.getExpression()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(BooleanLiteral b, CodeVisitor arg) {
    final BooleanLiteral b2 = (BooleanLiteral) arg;
    return objectEquals(b.getValue(), b2.getValue()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(CallableExpr c, CodeVisitor arg) {
    final CallableExpr c2 = (CallableExpr) arg;
    return objectEquals(c.getObjectExpression(), c2.getObjectExpression()) &&
        objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
        objectEquals(c.getArguments(), c2.getArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(CastExpr c, CodeVisitor arg) {
    final CastExpr c2 = (CastExpr) arg;
    return objectEquals(c.getExpression(), c2.getExpression()) &&
        objectEquals(c.isCoerce(), c2.isCoerce()) &&
        objectEquals(c.isIgnoreAutoboxing(), c2.isIgnoreAutoboxing()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(CharLiteral c, CodeVisitor arg) {
    final CharLiteral c2 = (CharLiteral) arg;
    return objectEquals(c.getValue(), c2.getValue()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ClassExpr c, CodeVisitor arg) {
    final ClassExpr c2 = (ClassExpr) arg;
    return objectEquals(c.getASTType(), c2.getASTType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ConstructorCallExpr c, CodeVisitor arg) {
    final ConstructorCallExpr c2 = (ConstructorCallExpr) arg;
    return objectEquals(c.getObjectExpression(), c2.getObjectExpression()) &&
        objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
        objectsEquals(c.getArguments(), c2.getArguments()) &&
        objectsEquals(c.getTypeArguments(), c2.getTypeArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(DeclarationExpr d, CodeVisitor arg) {
    final DeclarationExpr d2 = (DeclarationExpr) arg;
    return objectEquals(d.getLeftExpression(), d2.getLeftExpression()) &&
        objectEquals(d.getRightExpression(), d2.getRightExpression()) &&
        objectEquals(d.getOperation(), d2.getOperation()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(DoubleLiteral d, CodeVisitor arg) {
    final DoubleLiteral d2 = (DoubleLiteral) arg;
    return objectEquals(d.getValue(), d2.getValue()) &&
        objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(EmptyExpr e, CodeVisitor arg) {
    final EmptyExpr e2 = (EmptyExpr) arg;
    return objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(FieldAccessExpr f, CodeVisitor arg) {
    final FieldAccessExpr f2 = (FieldAccessExpr) arg;
    return objectEquals(f.getObjectExpression(), f2.getObjectExpression()) &&
        objectEquals(f.getFieldExpression(), f2.getFieldExpression()) &&
        objectEquals(f.isImplicitThis(), f2.isImplicitThis()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(IntegerLiteral i, CodeVisitor arg) {
    final IntegerLiteral i2 = (IntegerLiteral) arg;
    return objectEquals(i.getValue(), i2.getValue()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(ListExpression<?> l, CodeVisitor arg) {
    final ListExpression<?> l2 = (ListExpression<?>) arg;
    return objectsEquals(l.getValues(), l2.getValues()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(LongLiteral l, CodeVisitor arg) {
    final LongLiteral l2 = (LongLiteral) arg;
    return objectEquals(l.getValue(), l2.getValue()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(MapEntryExpr m, CodeVisitor arg) {
    final MapEntryExpr m2 = (MapEntryExpr) arg;
    return objectEquals(m.getKeyExpression(), m2.getKeyExpression()) &&
        objectEquals(m.getValueExpression(), m2.getValueExpression()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(MapExpr m, CodeVisitor arg) {
    final MapExpr m2 = (MapExpr) arg;
    return objectsEquals(m.getMapEntry(), m2.getMapEntry()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(MethodCallExpr m, CodeVisitor arg) {
    final MethodCallExpr m2 = (MethodCallExpr) arg;
    return objectEquals(m.getObjectExpression(), m2.getObjectExpression()) &&
        objectEquals(m.getMethodExpression(), m2.getMethodExpression()) &&
        objectsEquals(m.getArguments(), m2.getArguments()) &&
        objectsEquals(m.getTypeArguments(), m2.getTypeArguments()) &&
        objectEquals(m.getImplicitThis(), m2.getImplicitThis()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(NewArrayExpr n, CodeVisitor arg) {
    final NewArrayExpr n2 = (NewArrayExpr) arg;
    return objectEquals(n.getArrayLiteral(), n2.getArrayLiteral()) &&
        objectsEquals(n.getLevels(), n2.getLevels()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(NotExpression n, CodeVisitor arg) {
    final NotExpression n2 = (NotExpression) arg;
    return objectEquals(n.getExpression(), n2.getExpression()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(NullLiteral n, CodeVisitor arg) {
    final NullLiteral n2 = (NullLiteral) arg;
    return objectEquals(n.getValue(), n2.getValue()) &&
        objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(PostfixExpr p, CodeVisitor arg) {
    final PostfixExpr p2 = (PostfixExpr) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperation(), p2.getOperation()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(PrefixExpr p, CodeVisitor arg) {
    final PrefixExpr p2 = (PrefixExpr) arg;
    return objectEquals(p.getExpression(), p2.getExpression()) &&
        objectEquals(p.getOperation(), p2.getOperation()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(ProtocolExpr p, CodeVisitor arg) {
    final ProtocolExpr p2 = (ProtocolExpr) arg;
    return objectEquals(p.getTagExpression(), p2.getTagExpression()) &&
        objectsEquals(p.getValues(), p2.getValues()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(RecordExpr r, CodeVisitor arg) {
    final RecordExpr r2 = (RecordExpr) arg;
    return objectsEquals(r.getValues(), r2.getValues()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(SkipExpr s, CodeVisitor arg) {
    final SkipExpr s2 = (SkipExpr) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(StopExpr s, CodeVisitor arg) {
    final StopExpr s2 = (StopExpr) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(TernaryExpr t, CodeVisitor arg) {
    final TernaryExpr t2 = (TernaryExpr) arg;
    return objectEquals(t.getCondition(), t2.getCondition()) &&
        objectEquals(t.getTrueExpression(), t2.getTrueExpression()) &&
        objectEquals(t.getFalseExpression(), t2.getFalseExpression()) &&
        objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(UnaryMinusExpr u, CodeVisitor arg) {
    final UnaryMinusExpr u2 = (UnaryMinusExpr) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(UnaryPlusExpr u, CodeVisitor arg) {
    final UnaryPlusExpr u2 = (UnaryPlusExpr) arg;
    return objectEquals(u.getExpression(), u2.getExpression()) &&
        objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(VariableExpr v, CodeVisitor arg) {
    final VariableExpr v2 = (VariableExpr) arg;
    return objectEquals(v.getModifiers(), v2.getModifiers()) &&
        objectEquals(v.getName(), v2.getName()) &&
        objectEquals(v.getAccessVariable(), v2.getAccessVariable()) &&
        objectEquals(v.getComment(), v2.getComment());
  }

  @Override
  public Boolean visit(BlockComment b, CodeVisitor arg) {
    final BlockComment b2 = (BlockComment) arg;
    return objectEquals(b.getContent(), b2.getContent()) &&
        objectEquals(b.getCommentedNode(), b2.getCommentedNode()) &&
        objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(LineComment l, CodeVisitor arg) {
    final LineComment l2 = (LineComment) arg;
    return objectEquals(l.getContent(), l2.getContent()) &&
        objectEquals(l.getCommentedNode(), l2.getCommentedNode()) &&
        objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(ArrayType a, CodeVisitor arg) {
    final ArrayType a2 = (ArrayType) arg;
    return objectEquals(a.getElementType(), a2.getElementType()) &&
        objectEquals(a.getComment(), a2.getComment());
  }

  @Override
  public Boolean visit(BarrierType b, CodeVisitor arg) {
    final BarrierType b2 = (BarrierType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(ByteType b, CodeVisitor arg) {
    final ByteType b2 = (ByteType) arg;
    return objectEquals(b.getComment(), b2.getComment());
  }

  @Override
  public Boolean visit(ChannelEndType c, CodeVisitor arg) {
    final ChannelEndType c2 = (ChannelEndType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(ChannelType c, CodeVisitor arg) {
    final ChannelType c2 = (ChannelType) arg;
    return objectEquals(c.getChannelEnd(), c2.getChannelEnd()) &&
        objectEquals(c.getComponentType(), c2.getComponentType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(CharType c, CodeVisitor arg) {
    final CharType c2 = (CharType) arg;
    return objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(DoubleType d, CodeVisitor arg) {
    final DoubleType d2 = (DoubleType) arg;
    return objectEquals(d.getComment(), d2.getComment());
  }

  @Override
  public Boolean visit(ExternalType e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(FloatType f, CodeVisitor arg) {
    final FloatType f2 = (FloatType) arg;
    return objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(IntegerType i, CodeVisitor arg) {
    final IntegerType i2 = (IntegerType) arg;
    return objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(LongType l, CodeVisitor arg) {
    final LongType l2 = (LongType) arg;
    return objectEquals(l.getComment(), l2.getComment());
  }

  @Override
  public Boolean visit(MobileType m, CodeVisitor arg) {
    final MobileType m2 = (MobileType) arg;
    return objectEquals(m.doesYield(), m2.doesYield()) &&
        objectEquals(m.getTSType(), m2.getTSType()) &&
        objectsEquals(m.getTypeArguments(), m2.getTypeArguments()) &&
        objectsEquals(m.getImplementedTypes(), m2.getImplementedTypes()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(NullType n, CodeVisitor arg) {
    final NullType n2 = (NullType) arg;
    return objectEquals(n.getComment(), n2.getComment());
  }

  @Override
  public Boolean visit(ProcedureType p, CodeVisitor arg) {
    final ProcedureType p2 = (ProcedureType) arg;
    return objectEquals(p.doesYield(), p2.doesYield()) &&
        objectEquals(p.getTSType(), p2.getTSType()) &&
        objectsEquals(p.getTypeArguments(), p2.getTypeArguments()) &&
        objectsEquals(p.getImplementedTypes(), p2.getImplementedTypes()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(ProtocolTagType p, CodeVisitor arg) {
    final ProtocolTagType p2 = (ProtocolTagType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getTypeFields(), p2.getTypeFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(ProtocolType p, CodeVisitor arg) {
    final ProtocolType p2 = (ProtocolType) arg;
    return objectEquals(p.getName(), p2.getName()) &&
        objectsEquals(p.getTypeFields(), p2.getTypeFields()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(RecordType r, CodeVisitor arg) {
    final RecordType r2 = (RecordType) arg;
    return objectEquals(r.getName(), r2.getName()) &&
        objectsEquals(r.getTypeFields(), r2.getTypeFields()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(ShortType s, CodeVisitor arg) {
    final ShortType s2 = (ShortType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(StringType s, CodeVisitor arg) {
    final StringType s2 = (StringType) arg;
    return objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(TimerType t, CodeVisitor arg) {
    final TimerType t2 = (TimerType) arg;
    return objectEquals(t.getComment(), t2.getComment());
  }

  @Override
  public Boolean visit(UnknownType u, CodeVisitor arg) {
    final UnknownType u2 = (UnknownType) arg;
    return objectEquals(u.getComment(), u2.getComment());
  }

  @Override
  public Boolean visit(VoidType v, CodeVisitor arg) {
    final VoidType v2 = (VoidType) arg;
    return objectEquals(v.getComment(), v2.getComment());
  }
}
