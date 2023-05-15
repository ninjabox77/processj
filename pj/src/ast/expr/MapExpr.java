package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a map expression in Java.
 *
 * @author Ben
 */
public class MapExpr extends Expression<MapExpr> {

  private Sequence<MapEntryExpr> mapEntryExpression_;

  public MapExpr() {
    this(null);
  }

  public MapExpr(Sequence<MapEntryExpr> mapEntryExpression) {
    this(null, mapEntryExpression);
  }

  public MapExpr(Token token, Sequence<MapEntryExpr> mapEntryExpression) {
    super(token);
    setMapEntry(mapEntryExpression);
  }

  public MapExpr setMapEntry(Sequence<MapEntryExpr> mapEntryExpression) {
    if (mapEntryExpression == mapEntryExpression_) {
      return this;
    }
    if (mapEntryExpression_ != null) {
      mapEntryExpression_.setParentNode(null);
    }
    mapEntryExpression_ = mapEntryExpression;
    setAsParentNodeOf(mapEntryExpression);
    return this;
  }

  public Optional<Sequence<MapEntryExpr>> getMapEntry() {
    return Optional.ofNullable(mapEntryExpression_);
  }

  @Override
  public boolean isMapExp() {
    return true;
  }

  @Override
  public MapExpr asMapExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (mapEntryExpression_ != null) {
      for (int i = 0; i < mapEntryExpression_.size(); ++i) {
        if (node == mapEntryExpression_.get(i)) {
          mapEntryExpression_.set(i, (MapEntryExpr) replaceWith);
          return true;
        }
      }
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (mapEntryExpression_ != null) {
      for (int i = 0; i < mapEntryExpression_.size(); ++i) {
        if (node == mapEntryExpression_.get(i)) {
          mapEntryExpression_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
