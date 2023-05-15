package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents two expressions and an operation.
 *
 * @author Ben
 */
public class BinaryExpr extends Expression<BinaryExpr> {

  private Expression<?> leftExpression_;
  private Expression<?> rightExpression_;
  private Token operation_;

  public BinaryExpr(Expression<?> leftExpression, Token operation, Expression<?> rightExpression) {
    this(null, leftExpression, operation, rightExpression);
  }

  public BinaryExpr(Token token, Expression<?> leftExpression, Token operation, Expression<?> rightExpression) {
    super(token);
    setLeftExpression(leftExpression);
    setRightExpression(rightExpression);
    setOperation(operation);
  }

  public BinaryExpr setLeftExpression(Expression<?> leftExpression) {
    if (leftExpression == leftExpression_) {
      return this;
    }
    if (leftExpression_ != null) {
      leftExpression_.setParentNode(null);
    }
    leftExpression_ = leftExpression;
    setAsParentNodeOf(leftExpression);
    return this;
  }

  public Expression<?> getLeftExpression() {
    return leftExpression_;
  }

  public BinaryExpr setRightExpression(Expression<?> rightExpression) {
    if (rightExpression == rightExpression_) {
      return this;
    }
    if (rightExpression_ != null) {
      rightExpression_.setParentNode(null);
    }
    rightExpression_ = rightExpression;
    setAsParentNodeOf(rightExpression);
    return this;
  }

  public Expression<?> getRightExpression() {
    return rightExpression_;
  }

  public BinaryExpr setOperation(Token operation) {
    if (operation == operation_) {
      return this;
    }
    operation_ = operation;
    return this;
  }

  @Override
  public BinaryExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public boolean isBinaryExpr() {
    return true;
  }

  @Override
  public BinaryExpr asBinaryExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == leftExpression_) {
      setLeftExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == rightExpression_) {
      setRightExpression((Expression<?>) replaceWith);
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
