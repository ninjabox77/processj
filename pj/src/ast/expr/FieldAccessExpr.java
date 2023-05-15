package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a field access which contains a reference to a variable
 * expression that points to the field's declaration. The object expression
 * represents the instance of the class that was used to access the field.
 * If it is null, then this indicates that it was implicitly accessed
 * using "this".
 *
 * @author Ben
 */
public class FieldAccessExpr extends Expression<FieldAccessExpr> {

  private Expression<?> objectExpression_;
  private Expression<?> fieldExpression_;
  private boolean implicitThis_;

  public FieldAccessExpr() {
    this(null, null);
  }

  public FieldAccessExpr(Expression<?> objectExpression, Expression<?> fieldExpression) {
    this(null, objectExpression, fieldExpression);
  }

  public FieldAccessExpr(Token token, Expression<?> objectExpression, Expression<?> fieldExpression) {
    super(token);
    setObjectExpression(objectExpression);
    setFieldExpression(fieldExpression);
  }

  public FieldAccessExpr setObjectExpression(Expression<?> objectExpression) {
    if (objectExpression == objectExpression_) {
      return this;
    }
    if (objectExpression_ != null) {
      objectExpression_.setParentNode(null);
    }
    objectExpression_ = objectExpression;
    setAsParentNodeOf(objectExpression);
    return this;
  }

  public Optional<Expression<?>> getObjectExpression() {
    return Optional.ofNullable(objectExpression_);
  }

  public FieldAccessExpr setFieldExpression(Expression<?> fieldExpression) {
    if (fieldExpression == fieldExpression_) {
      return this;
    }
    if (fieldExpression_ != null) {
      fieldExpression_.setParentNode(null);
    }
    fieldExpression_ = fieldExpression;
    setAsParentNodeOf(fieldExpression);
    return this;
  }

  public Expression<?> getFieldExpression() {
    return fieldExpression_;
  }

  public FieldAccessExpr setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean isImplicitThis() {
    return implicitThis_ || objectExpression_ == null;
  }

  @Override
  public boolean isFieldAccessExpr() {
    return true;
  }

  @Override
  public FieldAccessExpr asFieldAccessExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == objectExpression_) {
      setObjectExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == fieldExpression_) {
      setFieldExpression((Expression<?>) replaceWith);
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
