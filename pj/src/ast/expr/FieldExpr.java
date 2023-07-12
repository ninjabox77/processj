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
public class FieldExpr extends Expression<FieldExpr> {

  private Expression<?> objectExpression_;
  private Expression<?> fieldExpression_;
  private boolean implicitThis_;

  public FieldExpr() {
    this(null, null);
  }

  public FieldExpr(Expression<?> objectExpression, Expression<?> fieldExpression) {
    this(null, objectExpression, fieldExpression);
  }

  public FieldExpr(Token token, Expression<?> objectExpression, Expression<?> fieldExpression) {
    super(token);
    setObjectExpression(objectExpression);
    setFieldExpression(fieldExpression);
  }

  public FieldExpr setObjectExpression(Expression<?> objectExpression) {
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

  public FieldExpr setFieldExpression(Expression<?> fieldExpression) {
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

  public FieldExpr setImplicitThis(final boolean implicitThis) {
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
  public boolean isFieldExpr() {
    return true;
  }

  @Override
  public FieldExpr asFieldExpr() {
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
