package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents all constructed types.
 *
 * @author Ben
 */
public abstract class Constructed extends Type {

  public Constructed() {
    this(null);
  }

  public Constructed(Token token) {
    super(token);
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

  public boolean isMethodCallType() {
    return false;
  }

  public MethodCallType<?> asMethodCallType() {
    throw new IllegalStateException(String.format("%s is not a CallableType, it is a %s", this, getClass().getSimpleName()));
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
