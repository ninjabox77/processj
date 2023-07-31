package ast.stmt;

import ast.SourceAST;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a return statement.
 *
 * @author Ben
 */
public class ReturnStatement extends Statement {

  private Expression<?> expression_;

  public ReturnStatement() {
    this(null);
  }

  public ReturnStatement(Expression<?> expression) {
    this(null, expression);
  }

  public ReturnStatement(Token token, Expression<?> expression) {
    super(token, null);
    setExpression(expression);
  }

  public ReturnStatement setExpression(Expression<?> expression) {
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
  public boolean isReturnStatement() {
    return true;
  }

  @Override
  public ReturnStatement asReturnStatement() {
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
