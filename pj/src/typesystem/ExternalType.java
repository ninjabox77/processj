package typesystem;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represent an external Java type (typically a class).
 *
 * @author Ben
 */
public class ExternalType extends Type {

  private String name_;
  private Sequence<Type> typeArguments_;

  public ExternalType() {
    this(null, null);
  }

  public ExternalType(final String name) {
    this(name, null);
  }

  public ExternalType(final String name, Sequence<Type> typeArguments) {
    this(null, name, typeArguments);
  }

  public ExternalType(Token token, final String name, Sequence<Type> typeArguments) {
    super(token);
    setName(name);
    setTypeArguments(typeArguments);
  }

  public ExternalType setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public ExternalType setTypeArguments(Sequence<Type> typeArguments) {
    if (typeArguments == typeArguments_) {
      return this;
    }
    if (typeArguments_ != null) {
      typeArguments_.setParentNode(null);
    }
    typeArguments_ = typeArguments;
    setAsParentNodeOf(typeArguments);
    return this;
  }

  public Optional<Sequence<Type>> getTypeArguments() {
    return Optional.ofNullable(typeArguments_);
  }

  @Override
  public boolean isExternalType() {
    return true;
  }

  @Override
  public ExternalType asExternalType() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (typeArguments_ != null) {
      for (int i = 0; i < typeArguments_.size(); ++i) {
        if (node == typeArguments_.get(i)) {
          typeArguments_.set(i, (Type) replaceWith);
          return true;
        }
      }
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
