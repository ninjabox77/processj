package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an entry inside a map expression in Java.
 *
 * @author Ben
 */
public class MapEntryExpression extends Expression<MapEntryExpression> {

  private Expression<?> keyExpression_;
  private Expression<?> valueExpression_;

  public MapEntryExpression() {
    this(null, null);
  }

  public MapEntryExpression(Expression<?> keyExpression, Expression<?> valueExpression) {
    this(null, keyExpression, valueExpression);
  }

  public MapEntryExpression(Token token, Expression<?> keyExpression, Expression<?> valueExpression) {
    super(token);
    setKeyExpression(keyExpression);
    setValueExpression(valueExpression);
  }

  public MapEntryExpression setKeyExpression(Expression<?> keyExpression) {
    if (keyExpression == keyExpression_) {
      return this;
    }
    if (keyExpression_ != null) {
      keyExpression_.setParentNode(null);
    }
    keyExpression_ = keyExpression;
    setAsParentNodeOf(keyExpression);
    return this;
  }

  public Expression<?> getKeyExpression() {
    return keyExpression_;
  }

  public MapEntryExpression setValueExpression(Expression<?> valueExpression) {
    if (valueExpression == valueExpression_) {
      return this;
    }
    if (valueExpression_ != null) {
      valueExpression_.setParentNode(null);
    }
    valueExpression_ = valueExpression;
    setAsParentNodeOf(valueExpression);
    return this;
  }

  public Expression<?> getValueExpression() {
    return valueExpression_;
  }

  @Override
  public boolean isMapEntryExpression() {
    return true;
  }

  @Override
  public MapEntryExpression asMapEntryExpression() {
    return this;
  }

  @Override
  public MapEntryExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == keyExpression_) {
      setKeyExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == valueExpression_) {
      setValueExpression((Expression<?>) replaceWith);
      return true;
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
