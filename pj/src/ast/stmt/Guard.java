package ast.stmt;

import ast.AnnotatedNode;
import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a guard in an alt statement.
 *
 * @author Ben
 */
public class Guard extends AnnotatedNode {

  private Expression<?> expression_;
  private boolean isSkip_;

  public Guard() {
    this(null);
  }

  public Guard(Expression<?> expression) {
    this(null, expression);
  }

  private Guard(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public Guard setExpression(Expression<?> expression) {
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

  public Guard setSkip(boolean isSkip) {
    if (isSkip == isSkip_) {
      return this;
    }
    isSkip_ = isSkip;
    return this;
  }

  public boolean isSkip() {
    return isSkip_;
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
