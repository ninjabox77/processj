package ast.java;

import ast.Parameter;
import ast.Sequence;
import ast.stmt.BlockStatement;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a declaration of a method.
 *
 * @author Ben
 */
public class MethodDeclaration<T extends MethodDeclaration<?>> extends BodyDeclaration<T> {

  private NodeType type_;
  private String name_;
  private BlockStatement body_;
  private Sequence<Parameter> parameters_;

  public MethodDeclaration() {
    this(ACC_PUBLIC, null, null);
  }

  public MethodDeclaration(final int modifiers, NodeType type, final String name) {
    this(modifiers, type, name, null, new BlockStatement());
  }

  public MethodDeclaration(final int modifiers, NodeType type, final String name, Sequence<Parameter> parameters, BlockStatement body) {
    this(null, modifiers, type, name, parameters, body);
  }

  public MethodDeclaration(Token token, final int modifiers, NodeType type, final String name, Sequence<Parameter> parameters, BlockStatement body) {
    super(token, modifiers);
    setModifiers(modifiers);
    setNodeType(type);
    setName(name);
    setParameters(parameters);
    setBody(body);
  }

  public T setNodeType(NodeType type) {
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

  public NodeType getNodeType() {
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

  public T setBody(BlockStatement body) {
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

  public BlockStatement getBody() {
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
