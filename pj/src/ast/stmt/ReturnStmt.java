package ast.stmt;

import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a return statement.
 *
 * @author Ben
 */
public class ReturnStmt extends Statement {

  private Expression<?> expression_;

  public ReturnStmt() {
    this(null);
  }

  public ReturnStmt(Expression<?> expression) {
    this(null, expression);
  }

  public ReturnStmt(Token token, Expression<?> expression) {
    super(token, null);
    setExpression(expression);
  }

  public ReturnStmt setExpression(Expression<?> expression) {
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

  public Optional<Expression<?>> getExpression() {
    return Optional.ofNullable(expression_);
  }

  @Override
  public boolean isReturnStmt() {
    return true;
  }

  @Override
  public ReturnStmt asReturnStmt() {
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
    return false;
  }
}
