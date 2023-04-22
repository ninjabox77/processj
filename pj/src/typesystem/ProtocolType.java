package typesystem;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a protocol type.
 *
 * @author Ben
 */
public class ProtocolType extends Constructed {

  private String name_;
  private Sequence<ProtocolTagType> typeFields_;

  public ProtocolType() {
    this(null, null, null);
  }

  public ProtocolType(final String name) {
    this(null, name, null);
  }

  public ProtocolType(final String name, Sequence<ProtocolTagType> typeFields) {
    this(null, name, typeFields);
  }

  public ProtocolType(Token token, final String name, Sequence<ProtocolTagType> typeFields) {
    super(token);
    setName(name);
    setTypeFields(typeFields);
  }

  public ProtocolType setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public ProtocolType setTypeFields(Sequence<ProtocolTagType> typeFields) {
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

  public Optional<Sequence<ProtocolTagType>> getTypeFields() {
    return Optional.ofNullable(typeFields_);
  }

  @Override
  public boolean isProtocolType() {
    return true;
  }

  @Override
  public ProtocolType asProtocolType() {
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
        typeFields_.set(i, (ProtocolTagType) replaceWith);
        return true;
      }
    }
    return false;
  }
}
