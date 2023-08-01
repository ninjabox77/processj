package ast.expr;

import ast.SourceAST;
import ast.Sequence;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a map expression in Java.
 *
 * @author Ben
 */
public class MapExpression extends Expression<MapExpression> {

  private Sequence<MapEntryExpression> mapEntryExpression_;

  public MapExpression() {
    this(Sequence.sequenceList());
  }

  public MapExpression(Sequence<MapEntryExpression> mapEntryExpression) {
    this(null, mapEntryExpression);
  }

  public MapExpression(Token token, Sequence<MapEntryExpression> mapEntryExpression) {
    super(token);
    setMapEntry(mapEntryExpression);
  }

  public MapExpression setMapEntry(Sequence<MapEntryExpression> mapEntryExpression) {
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

  public Sequence<MapEntryExpression> getMapEntry() {
    return mapEntryExpression_;
  }

  @Override
  public boolean isMapExpression() {
    return true;
  }

  @Override
  public MapExpression asMapExpression() {
    return this;
  }

  @Override
  public MapExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < mapEntryExpression_.size(); ++i) {
      if (node == mapEntryExpression_.get(i)) {
        mapEntryExpression_.set(i, (MapEntryExpression) replaceWith);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < mapEntryExpression_.size(); ++i) {
      if (node == mapEntryExpression_.get(i)) {
        mapEntryExpression_.remove(i);
        return true;
      }
    }
    return false;
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
