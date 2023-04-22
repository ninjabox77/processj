package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents an expression where - is applied to a single expression.
 *
 * @author Ben
 */
public class UnaryMinusExpr extends Expression<UnaryMinusExpr> {

  private Expression<?> expression_;

  public UnaryMinusExpr() {
    this(null);
  }

  public UnaryMinusExpr(Expression<?> expression) {
    this(null, expression);
  }

  public UnaryMinusExpr(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public UnaryMinusExpr setExpression(Expression<?> expression) {
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

  @Override
  public UnaryMinusExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public boolean isUnaryMinusExpr() {
    return true;
  }

  @Override
  public UnaryMinusExpr asUnaryMinusExpr() {
    return this;
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
