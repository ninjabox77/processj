package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
  public UnaryPlusExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean isUnaryPlusExpr() {
    return true;
  }

  @Override
  public UnaryPlusExpr asUnaryPlusExpr() {
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
