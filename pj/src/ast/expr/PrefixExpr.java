package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a prefix expression such as ++expression or --expression.
 *
 * @author Ben
 */
public class PrefixExpr extends Expression<PrefixExpr> {

  private Expression<?> expression_;
  private Token operation_;

  public PrefixExpr() {
    this(null, null);
  }

  public PrefixExpr(Token operation, Expression<?> expression) {
    this(null, operation, expression);
  }

  public PrefixExpr(Token token, Token operation, Expression<?> expression) {
    super(token);
    setOperation(operation);
    setExpression(expression);
  }

  public PrefixExpr setOperation(Token operation) {
    if (operation == operation_) {
      return this;
    }
    operation_ = operation;
    return this;
  }

  public Token getOperation() {
    return operation_;
  }

  public PrefixExpr setExpression(Expression<?> expression) {
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
  public boolean isPrefixExpr() {
    return true;
  }

  @Override
  public PrefixExpr asPrefixExpr() {
    return this;
  }

  @Override
  public PrefixExpr setType(Type type) {
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
