package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a boolean expression.
 *
 * @author Ben
 */
public class BooleanExpr extends Expression<BooleanExpr> {

  private Expression<?> expression_;

  public BooleanExpr() {
    this(null);
  }

  public BooleanExpr(Expression<?> expression) {
    this(null, expression);
  }

  public BooleanExpr(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public BooleanExpr setExpression(Expression<?> expression) {
    if (expression == expression_) {
      return this;
    }
    if (expression_ != null) {
      expression_.setParentNode(null);
    }
    expression_ = expression;
    setAsParentNodeOf(expression);
    return this;
  }

  public Expression<?> getExpression() {
    return expression_;
  }

  public boolean isNotExpression() {
    return false;
  }

  public NotExpression asNotExpression() {
    throw new IllegalStateException(String.format("%s is not a NotExpression, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isBooleanExpr() {
    return true;
  }

  @Override
  public BooleanExpr asBooleanExpr() {
    return this;
  }

  @Override
  public BooleanExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }
}
