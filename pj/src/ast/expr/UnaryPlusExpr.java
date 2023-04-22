package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents an expression where + is applied to a single expression.
 *
 * @author Ben
 */
public class UnaryPlusExpr extends Expression<UnaryPlusExpr> {

  private Expression<?> expression_;

  public UnaryPlusExpr() {
    this(null);
  }

  public UnaryPlusExpr(Expression<?> expression) {
    this(null, expression);
  }

  public UnaryPlusExpr(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public UnaryPlusExpr setExpression(Expression<?> expression) {
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
  public UnaryPlusExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public boolean isUnaryPlusExpr() {
    return true;
  }

  @Override
  public UnaryPlusExpr asUnaryPlusExpr() {
    return this;
  }
}
