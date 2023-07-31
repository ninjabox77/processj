package typesystem;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents all constructed types.
 *
 * @author Ben
 */
public abstract class Constructed extends Type {

  protected Sequence<Type> implementedTypes_;

  public Constructed() {
    this(null);
  }

  public Constructed(Token token) {
    super(token);
  }

  public Constructed setImplementedTypes(Sequence<Type> implementedTypes) {
    if (implementedTypes == implementedTypes_) {
      return this;
    }
    if (implementedTypes_ != null) {
      implementedTypes_.setParentNode(null);
    }
    implementedTypes_ = implementedTypes;
    setAsParentNodeOf(implementedTypes);
    return this;
  }

  public Optional<Sequence<Type>> getImplementedTypes() {
    return Optional.ofNullable(implementedTypes_);
  }

  @Override
  public boolean isConstructedType() {
    return true;
  }

  @Override
  public Constructed asConstructedType() {
    return this;
  }

  public boolean isArrayType() {
    return false;
  }

  public ArrayType asArrayType() {
    throw new IllegalStateException(String.format("%s is not an ArrayType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelType() {
    return false;
  }

  public ChannelType asChannelType() {
    throw new IllegalStateException(String.format("%s is not a ChannelType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelEndType() {
    return false;
  }

  public ChannelEndType asChannelEndType() {
    throw new IllegalStateException(String.format("%s is not a ChannelEndType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordType() {
    return false;
  }

  public RecordType asRecordType() {
    throw new IllegalStateException(String.format("%s is not a RecordType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolType() {
    return false;
  }

  public ProtocolType asProtocolType() {
    throw new IllegalStateException(String.format("%s is not a ProtocolType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolTagType() {
    return false;
  }

  public ProtocolTagType asProtocolTagType() {
    throw new IllegalStateException(String.format("%s is not a ProtocolCaseType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCallableType() {
    return false;
  }

  public CallableType<?> asCallableType() {
    throw new IllegalStateException(String.format("%s is not a CallableType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNamedType() {
    return false;
  }

  public NamedType asNamedType() {
    throw new IllegalStateException(String.format("%s is not a NamedType, it is a %s", this, getClass().getSimpleName()));
  }
}
