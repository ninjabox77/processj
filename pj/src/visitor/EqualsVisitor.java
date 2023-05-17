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

import java.util.List;
import java.util.Optional;

/**
 * @author Ben
 */
public class EqualsVisitor implements GenericVisitor<Boolean, CodeVisitor> {

  private static final EqualsVisitor INSTANCE = new EqualsVisitor();

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
    return null;
  }

  @Override
  public Boolean visit(final ClassNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final CompileUnit c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Import i, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Name n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Node n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Package p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Parameter p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final Sequence<?> s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ArrayNode a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final BarrierNode b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ChannelEndNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ChannelNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ExternalNode e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final PrimitiveNode p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final TimerNode t, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ConstantTopLevel c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ExternalTopLevel e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final MethodCallTopLevel<?> m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final MobileDeclaration m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ProcedureTopLevel p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ProtocolTagDeclaration p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ProtocolTopLevel p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final RecordTopLevel r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final BlockStmt b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final BreakStmt b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final CaseStmt c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ContinueStmt c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final DoWhileStmt d, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final EmptyStmt e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(final ExpressionStmt e, CodeVisitor arg) {
    final ExpressionStmt e2 = (ExpressionStmt) arg;
    return objectEquals(e.getExpression(), e2.getExpression()) &&
        objectEquals(e.getComment(), e2.getComment());
  }

  @Override
  public Boolean visit(final ForEachStmt f, CodeVisitor arg) {
    final ForEachStmt f2 = (ForEachStmt) arg;
    return objectEquals(f.getVariable(), f2.getVariable()) &&
        objectEquals(f.getCollectionExpr(), f2.getCollectionExpr()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock());
  }

  @Override
  public Boolean visit(final ForStmt f, CodeVisitor arg) {
    final ForStmt f2 = (ForStmt) arg;
    return objectEquals(f.getInitialization(), f2.getInitialization()) &&
        objectEquals(f.getCondition(), f2.getCondition()) &&
        objectEquals(f.getUpdate(), f2.getUpdate()) &&
        objectEquals(f.getLoopBlock(), f2.getLoopBlock()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final GuardStmt g, CodeVisitor arg) {
    final GuardStmt g2 = (GuardStmt) arg;
    return objectEquals(g.getExpression(), g2.getExpression()) &&
        objectEquals(g.getStatement(), g2.getStatement()) &&
        objectEquals(g.getComment(), g2.getComment());
  }

  @Override
  public Boolean visit(final IfStmt i, CodeVisitor arg) {
    final IfStmt i2 = (IfStmt) arg;
    return objectEquals(i.getConditional(), i2.getConditional()) &&
        objectEquals(i.getIfPart(), i2.getIfPart()) &&
        objectEquals(i.getElsePart(), i2.getElsePart()) &&
        objectEquals(i.getComment(), i2.getComment());
  }

  @Override
  public Boolean visit(final RegularAltStmt r, CodeVisitor arg) {
    final RegularAltStmt r2 = (RegularAltStmt) arg;
    return objectsEquals(r.getGuards(), r2.getGuards()) &&
        objectEquals(r.getGuards(), r2.getGuards()) &&
        objectEquals(r.isFairAlt(), r2.isFairAlt()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final ReturnStmt r, CodeVisitor arg) {
    final ReturnStmt r2 = (ReturnStmt) arg;
    return objectEquals(r.getExpression(), r2.getExpression()) &&
        objectEquals(r.getComment(), r2.getComment());
  }

  @Override
  public Boolean visit(final SwitchStmt s, CodeVisitor arg) {
    final SwitchStmt s2 = (SwitchStmt) arg;
    return objectEquals(s.getSelector(), s2.getSelector()) &&
        objectsEquals(s.getSwitchCases(), s2.getSwitchCases()) &&
        objectEquals(s.getDefaultStmt(), s2.getDefaultStmt()) &&
        objectEquals(s.getComment(), s2.getComment());
  }

  @Override
  public Boolean visit(final WhileStmt w, CodeVisitor arg) {
    final WhileStmt w2 = (WhileStmt) arg;
    return objectEquals(w.getCondition(), w2.getCondition()) &&
        objectEquals(w.getLoopBlock(), w2.getLoopBlock()) &&
        objectEquals(w.getComment(), w2.getComment());
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
        objectEquals(c.getType(), c2.getType()) &&
        objectEquals(c.getName(), c2.getName()) &&
        objectEquals(c.getBody(), c2.getBody()) &&
        objectsEquals(c.getParameters(), c2.getParameters()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final FieldDeclaration f, CodeVisitor arg) {
    final FieldDeclaration f2 = (FieldDeclaration) arg;
    return objectEquals(f.getModifiers(), f2.getModifiers()) &&
        objectEquals(f.getType(), f2.getType()) &&
        objectEquals(f.getName(), f2.getName()) &&
        objectEquals(f.getComment(), f2.getComment());
  }

  @Override
  public Boolean visit(final MethodDeclaration<?> m, CodeVisitor arg) {
    final MethodDeclaration<?> m2 = (MethodDeclaration<?>) arg;
    return objectEquals(m.getType(), m2.getType()) &&
        objectEquals(m.getName(), m2.getName()) &&
        objectEquals(m.getBody(), m2.getBody()) &&
        objectsEquals(m.getParameters(), m2.getParameters()) &&
        objectEquals(m.getComment(), m2.getComment());
  }

  @Override
  public Boolean visit(final ArrayExpr a, CodeVisitor arg) {
    final ArrayExpr a2 = (ArrayExpr) arg;
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
  public Boolean visit(final CallableExpr c, CodeVisitor arg) {
    final CallableExpr c2 = (CallableExpr) arg;
    return objectEquals(c.getObjectExpression(), c2.getObjectExpression()) &&
        objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
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
    return objectEquals(c.getType(), c2.getType()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final ConstructorCallExpr c, CodeVisitor arg) {
    final ConstructorCallExpr c2 = (ConstructorCallExpr) arg;
    return objectEquals(c.getObjectExpression(), c2.getObjectExpression()) &&
        objectEquals(c.getMethodExpression(), c2.getMethodExpression()) &&
        objectsEquals(c.getArguments(), c2.getArguments()) &&
        objectsEquals(c.getTypeArguments(), c2.getTypeArguments()) &&
        objectEquals(c.getComment(), c2.getComment());
  }

  @Override
  public Boolean visit(final DeclarationExpr d, CodeVisitor arg) {
    final DeclarationExpr d2 = (DeclarationExpr) arg;
    return objectEquals(d.getLeftExpression(), d2.getLeftExpression()) &&
        objectEquals(d.getRightExpression(), d2.getRightExpression()) &&
        objectEquals(d.getOperation(), d2.getOperation()) &&
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
  public Boolean visit(final FieldAccessExpr f, CodeVisitor arg) {
    final FieldAccessExpr f2 = (FieldAccessExpr) arg;
    return objectEquals(f.getObjectExpression(), f2.getObjectExpression()) &&
        objectEquals(f.getFieldExpression(), f2.getFieldExpression()) &&
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
    return objectEquals(m.getObjectExpression(), m2.getObjectExpression()) &&
        objectEquals(m.getMethodExpression(), m2.getMethodExpression()) &&
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
  public Boolean visit(final ProtocolExpr p, CodeVisitor arg) {
    final ProtocolExpr p2 = (ProtocolExpr) arg;
    return objectEquals(p.getTagExpression(), p2.getTagExpression()) &&
        objectsEquals(p.getValues(), p2.getValues()) &&
        objectEquals(p.getComment(), p2.getComment());
  }

  @Override
  public Boolean visit(final RecordExpr r, CodeVisitor arg) {
    final RecordExpr r2 = (RecordExpr) arg;
    return objectsEquals(r.getValues(), r2.getValues()) &&
        objectEquals(r.getComment(), r2.getComment());
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
        objectEquals(m.getType(), m2.getType()) &&
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
        objectEquals(p.getType(), p2.getType()) &&
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
}
