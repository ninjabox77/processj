package ast.expr;

import ast.Node;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
  public UnaryMinusExpr setASTType(ASTType type) {
    return super.setASTType(type);
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
