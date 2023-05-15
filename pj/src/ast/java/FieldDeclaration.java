package ast.java;

import ast.Node;
import ast.Variable;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a declaration of a field in a record, a protocol as
 * part of its tag, or a class (which is only used during code
 * transformation).
 *
 * @author Ben
 */
public class FieldDeclaration extends BodyDeclaration<FieldDeclaration> implements Variable<FieldDeclaration> {

  private Type type_;
  private String name_;
  private Expression<?> rightExpression_;

  public FieldDeclaration() {
    this(null, null);
  }

  public FieldDeclaration(Type type, final String name) {
    this(ACC_PRIVATE, type, name);
  }

  public FieldDeclaration(final int modifiers, Type type, final String name) {
    this(modifiers, type, name, null);
  }

  public FieldDeclaration(final int modifiers, Type type, final String name, Expression<?> rightExpression) {
    this(null, modifiers, type, name, rightExpression);
  }

  public FieldDeclaration(Token token, final int modifiers, Type type, final String name, Expression<?> rightExpression) {
    super(token);
    setModifiers(modifiers);
    setType(type);
    setName(name);
    setRightExpression(rightExpression);
  }

  public FieldDeclaration setRightExpression(Expression<?> rightExpression) {
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

  @Override
  public FieldDeclaration setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public Type getType() {
    return type_;
  }

  @Override
  public FieldDeclaration setName(String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public boolean hasInitialExpression() {
    return rightExpression_ != null;
  }

  @Override
  public Expression<?> getInitialExpression() {
    return rightExpression_;
  }

  @Override
  public FieldDeclaration setModifiers(final int modifiers) {
    return super.setModifiers(modifiers);
  }

  @Override
  public boolean isFieldDeclaration() {
    return true;
  }

  @Override
  public FieldDeclaration asFieldDeclaration() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
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
