package typesystem;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a record type.
 *
 * @author Ben
 */
public class RecordType extends Constructed {

  private String name_;
  private Sequence<Type> typeFields_;

  public RecordType() {
    this(null, null, null);
  }

  public RecordType(final String name) {
    this(null, name, null);
  }

  public RecordType(final String name, Sequence<Type> typeFields) {
    this(null, name, typeFields);
  }

  public RecordType(Token token, final String name, Sequence<Type> typeFields) {
    super(token);
    setName(name);
    setTypeFields(typeFields);
  }

  public RecordType setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public RecordType setTypeFields(Sequence<Type> typeFields) {
    if (typeFields == typeFields_) {
      return this;
    }
    if (typeFields_ != null) {
      typeFields_.setParentNode(null);
    }
    typeFields_ = typeFields;
    return this;
  }

  public Optional<Sequence<Type>> getTypeFields() {
    return Optional.ofNullable(typeFields_);
  }

  @Override
  public boolean isRecordType() {
    return true;
  }

  @Override
  public RecordType asRecordType() {
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
}
