package typesystem;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a protocol type.
 *
 * @author Ben
 */
public class ProtocolType extends Constructed {

  private String name_;
  private Sequence<ProtocolTagType> fieldTypes_;

  public ProtocolType() {
    this(null, null, null);
  }

  public ProtocolType(final String name) {
    this(null, name, null);
  }

  public ProtocolType(final String name, Sequence<ProtocolTagType> fieldTypes) {
    this(null, name, fieldTypes);
  }

  public ProtocolType(Token token, final String name, Sequence<ProtocolTagType> fieldTypes) {
    super(token);
    setName(name);
    setFieldTypes(fieldTypes);
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

  public ProtocolType setFieldTypes(Sequence<ProtocolTagType> typeFields) {
    if (typeFields == fieldTypes_) {
      return this;
    }
    if (fieldTypes_ != null) {
      fieldTypes_.setParentNode(null);
    }
    fieldTypes_ = typeFields;
    setAsParentNodeOf(typeFields);
    return this;
  }

  public Optional<Sequence<ProtocolTagType>> getFieldTypes() {
    return Optional.ofNullable(fieldTypes_);
  }

  @Override
  public String asString() {
    return "<P" + name_ + ";";
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
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (fieldTypes_ == null) {
      return false;
    }
    for (int i = 0; i < fieldTypes_.size(); ++i) {
      if (node == fieldTypes_.get(i)) {
        fieldTypes_.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (fieldTypes_ == null) {
      return false;
    }
    for (int i = 0; i < fieldTypes_.size(); ++i) {
      if (node == fieldTypes_.get(i)) {
        fieldTypes_.set(i, (ProtocolTagType) replaceWith);
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
