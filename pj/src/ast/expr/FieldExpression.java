package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a field access which contains a reference to a variable
 * object that points to the field's declaration. The fieldExpression
 * represents the instance of the class that was used to access the field.
 * If it is null, then this indicates that it was implicitly accessed
 * using "this".
 *
 * @author Ben
 */
public class FieldExpression extends Expression<FieldExpression> {

  private Expression<?> fieldExpression_;
  private boolean implicitThis_;
  private String identifier_;

  public FieldExpression() {
    this(null, null);
  }

  public FieldExpression(Expression<?> fieldExpression, final String identifier) {
    this(null, fieldExpression, identifier);
  }

  public FieldExpression(Token token, Expression<?> fieldExpression, final String identifier) {
    super(token);
    setFieldExpression(fieldExpression);
    setIdentifier(identifier);
  }

  public FieldExpression setFieldExpression(Expression<?> fieldExpression) {
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

  public Optional<Expression<?>> getFieldExpression() {
    return Optional.ofNullable(fieldExpression_);
  }

  public FieldExpression setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
  }

  public FieldExpression setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean isImplicitThis() {
    return implicitThis_ || getFieldExpression().isPresent();
  }

  @Override
  public boolean isFieldExpression() {
    return true;
  }

  @Override
  public FieldExpression asFieldExpression() {
    return this;
  }

  @Override
  public FieldExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == fieldExpression_) {
      setFieldExpression((Expression<?>) replaceWith);
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
