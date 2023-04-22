package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;

/**
 * Represents an entry inside a map expression in Java.
 *
 * @author Ben
 */
public class MapEntryExpr extends Expression<MapEntryExpr> {

  private Expression<?> keyExpression_;
  private Expression<?> valueExpression_;

  public MapEntryExpr() {
    this(null, null);
  }

  public MapEntryExpr(Expression<?> keyExpression, Expression<?> valueExpression) {
    this(null, keyExpression, valueExpression);
  }

  public MapEntryExpr(Token token, Expression<?> keyExpression, Expression<?> valueExpression) {
    super(token);
    setKeyExpression(keyExpression);
    setValueExpression(valueExpression);
  }

  public MapEntryExpr setKeyExpression(Expression<?> keyExpression) {
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

  public MapEntryExpr setValueExpression(Expression<?> valueExpression) {
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
  public boolean isMapEntryExpr() {
    return true;
  }

  @Override
  public MapEntryExpr asMapEntryExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
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
}
