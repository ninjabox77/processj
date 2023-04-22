package ast.java;

import ast.Parameter;
import ast.Sequence;
import ast.stmt.BlockStmt;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a declaration of a method.
 *
 * @author Ben
 */
public class MethodDeclaration<T extends MethodDeclaration<?>> extends BodyDeclaration<T> {

  private Type type_;
  private String name_;
  private BlockStmt body_;
  private Sequence<Parameter> parameters_;

  public MethodDeclaration() {
    this(ACC_PUBLIC, null, null);
  }

  public MethodDeclaration(final int modifiers, Type type, final String name) {
    this(modifiers, type, name, null, new BlockStmt());
  }

  public MethodDeclaration(final int modifiers, Type type, final String name, Sequence<Parameter> parameters, BlockStmt body) {
    this(null, modifiers, type, name, parameters, body);
  }

  public MethodDeclaration(Token token, final int modifiers, Type type, final String name, Sequence<Parameter> parameters, BlockStmt body) {
    super(token, modifiers);
    setModifiers(modifiers);
    setType(type);
    setName(name);
    setParameters(parameters);
    setBody(body);
  }

  @Override
  public T setType(Type type) {
    if (type == type_) {
      return (T) this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    setAsParentNodeOf(type);
    return (T) this;
  }

  @Override
  public T setModifiers(int modifiers) {
    return super.setModifiers(modifiers);
  }

  @Override
  public Type getType() {
    return type_;
  }

  public T setName(final String name) {
    if (Objects.equals(name, name_)) {
      return (T) this;
    }
    name_ = name;
    return (T) this;
  }

  public String getName() {
    return name_;
  }

  public T setBody(BlockStmt body) {
    if (body == body_) {
      return (T) this;
    }
    if (body_ != null) {
      body_.setParentNode(null);
    }
    body_ = body;
    setAsParentNodeOf(body);
    return (T) this;
  }

  public T setParameters(Sequence<Parameter> parameters) {
    if (parameters == parameters_) {
      return (T) this;
    }
    if (parameters_ != null) {
      parameters_.setParentNode(null);
    }
    parameters_ = parameters;
    setAsParentNodeOf(parameters);
    return (T) this;
  }

  public Optional<Sequence<Parameter>> getParameters() {
    return Optional.ofNullable(parameters_);
  }

  public BlockStmt getBody() {
    return body_;
  }

  public boolean isConstructorDeclaration() {
    return false;
  }

  public ConstructorDeclaration asConstructorDeclaration() {
    throw new IllegalStateException(String.format("%s is not a ConstructorDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isMethodDeclaration() {
    return true;
  }

  @Override
  public MethodDeclaration<?> asMethodDeclaration() {
    return this;
  }
}
