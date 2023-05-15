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
 * Represents a tag as defined in a protocol's body.
 *
 * @author Ben
 */
public class ProtocolTagType extends Constructed {

  private String name_;
  private Sequence<Type> typeFields_;

  public ProtocolTagType() {
    this(null, null, null);
  }

  public ProtocolTagType(final String name) {
    this(null, name, null);
  }

  public ProtocolTagType(final String name, Sequence<Type> typeFields) {
    this(null, name, typeFields);
  }

  public ProtocolTagType(Token token, final String name, Sequence<Type> typeFields) {
    super(token);
    setName(name);
    setTypeFields(typeFields);
  }

  public ProtocolTagType setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public ProtocolTagType setTypeFields(Sequence<Type> typeFields) {
    if (typeFields == typeFields_) {
      return this;
    }
    if (typeFields_ != null) {
      typeFields_.setParentNode(null);
    }
    typeFields_ = typeFields;
    setAsParentNodeOf(typeFields);
    return this;
  }

  public Optional<Sequence<Type>> getTypeFields() {
    return Optional.ofNullable(typeFields_);
  }

  @Override
  public boolean isProtocolTagType() {
    return true;
  }

  @Override
  public ProtocolTagType asProtocolTagType() {
    return this;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (typeFields_ == null) {
      return false;
    }
    for (int i = 0; i < typeFields_.size(); ++i) {
      if (node == typeFields_.get(i)) {
        typeFields_.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (typeFields_ == null) {
      return false;
    }
    for (int i = 0; i < typeFields_.size(); ++i) {
      if (node == typeFields_.get(i)) {
        typeFields_.set(i, (Type) replaceWith);
        return true;
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
