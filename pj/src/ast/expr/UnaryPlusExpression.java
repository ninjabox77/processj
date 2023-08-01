package ast.expr;

import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an expression where + is applied to a single expression.
 *
 * @author Ben
 */
public class UnaryPlusExpression extends Expression<UnaryPlusExpression> {

  private Expression<?> expression_;

  public UnaryPlusExpression() {
    this(null);
  }

  public UnaryPlusExpression(Expression<?> expression) {
    this(null, expression);
  }

  public UnaryPlusExpression(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public UnaryPlusExpression setExpression(Expression<?> expression) {
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
  public UnaryPlusExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean isUnaryPlusExpression() {
    return true;
  }

  @Override
  public UnaryPlusExpression asUnaryPlusExpression() {
    return this;
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
