package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an expression where - is applied to a single expression.
 *
 * @author Ben
 */
public class UnaryMinusExpression extends Expression<UnaryMinusExpression> {

  private Expression<?> expression_;

  public UnaryMinusExpression() {
    this(null);
  }

  public UnaryMinusExpression(Expression<?> expression) {
    this(null, expression);
  }

  public UnaryMinusExpression(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public UnaryMinusExpression setExpression(Expression<?> expression) {
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
  public UnaryMinusExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean isUnaryMinusExpression() {
    return true;
  }

  @Override
  public UnaryMinusExpression asUnaryMinusExpression() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
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
