package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a boolean expression.
 *
 * @author Ben
 */
public class BooleanExpression extends Expression<BooleanExpression> {

  private Expression<?> expression_;

  public BooleanExpression() {
    this(null);
  }

  public BooleanExpression(Expression<?> expression) {
    this(null, expression);
  }

  public BooleanExpression(Token token, Expression<?> expression) {
    super(token);
    setExpression(expression);
  }

  public BooleanExpression setExpression(Expression<?> expression) {
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
  public boolean isBooleanExpression() {
    return true;
  }

  @Override
  public BooleanExpression asBooleanExpression() {
    return this;
  }

  @Override
  public BooleanExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  public boolean isNotExpression() {
    return false;
  }

  public NotExpression asNotExpression() {
    throw new IllegalStateException(String.format("%s is not a NotExpression, it is a %s", this, getClass().getSimpleName()));
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
