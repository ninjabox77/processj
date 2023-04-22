package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a postfix expression such as expression++ or expression--.
 *
 * @author Ben
 */
public class PostfixExpr extends Expression<PostfixExpr> {

  private Expression<?> expression_;
  private Token operation_;

  public PostfixExpr() {
    this(null, null);
  }

  public PostfixExpr(Token operation, Expression<?> expression) {
    this(null, operation, expression);
  }

  public PostfixExpr(Token token, Token operation, Expression<?> expression) {
    super(token);
    setOperation(operation);
    setExpression(expression);
  }

  public PostfixExpr setOperation(Token operation) {
    if (operation == operation_) {
      return this;
    }
    operation_ = operation;
    return this;
  }

  public Token getOperation() {
    return operation_;
  }

  public PostfixExpr setExpression(Expression<?> expression) {
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
  public boolean isPostfixExpr() {
    return true;
  }

  @Override
  public PostfixExpr asPostfixExpr() {
    return this;
  }

  @Override
  public PostfixExpr setType(Type type) {
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
