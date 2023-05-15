package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the (shared) end of a channel type.
 *
 * @author Ben
 */
public class ChannelEndType extends Constructed {

  public static final int READ_END = 0x0001;
  public static final int WRITE_END = 0x0002;
  public static final int SHARED = 0x0004;

  private int channelEnd_;
  private Type componentType_;

  public ChannelEndType(Type componentType) {
    this(null, componentType, READ_END);
  }

  public ChannelEndType(Type componentType, int channelEnd) {
    this(null, componentType, channelEnd);
  }

  public ChannelEndType(Token token, Type componentType, int channelEnd) {
    super(token);
    setComponentType(componentType);
    setChannelEnd(channelEnd);
  }

  public boolean isRead() {
    return (channelEnd_ & READ_END) != 0;
  }

  public boolean isWrite() {
    return (channelEnd_ & WRITE_END) != 0;
  }

  public boolean isShared() {
    return (channelEnd_ & SHARED) != 0;
  }

  public ChannelEndType setComponentType(Type componentType) {
    if (componentType == componentType_) {
      return this;
    }
    if (componentType_ != null) {
      componentType_.setParentNode(null);
    }
    componentType_ = componentType;
    setAsParentNodeOf(componentType);
    return this;
  }

  public Type getComponentType() {
    return componentType_;
  }

  public ChannelEndType setChannelEnd(int channelEnd) {
    if (channelEnd == channelEnd_) {
      return this;
    }
    channelEnd_ = channelEnd;
    return this;
  }

  public int getChannelEnd() {
    return channelEnd_;
  }

  @Override
  public boolean isChannelEndType() {
    return true;
  }

  @Override
  public ChannelEndType asChannelEndType() {
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
