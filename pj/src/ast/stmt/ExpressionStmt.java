package ast.stmt;

import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a statement where the return value is ignore.
 *
 * @author Ben
 */
public class ExpressionStmt extends Statement {

  private Expression<?> expression_;

  public ExpressionStmt() {
    this(null);
  }

  public ExpressionStmt(Expression<?> expression) {
    this(null, expression);
  }

  public ExpressionStmt(Token token, Expression<?> expression) {
    super(token, null);
    setExpression(expression);
  }

  public ExpressionStmt setExpression(Expression<?> expression) {
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
  public boolean isExpressionStmt() {
    return true;
  }

  @Override
  public ExpressionStmt asExpressionStmt() {
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
