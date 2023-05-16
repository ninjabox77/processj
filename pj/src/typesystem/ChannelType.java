package typesystem;

import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a channel type.
 *
 * @author Ben
 */
public class ChannelType extends Constructed {

  public static final int SHARED_READ = 0x0001;
  public static final int SHARED_WRITE = 0x0002;
  public static final int SHARED = 0x0004;
  public static final int NON_SHARED = 0x0008;

  private int shared_;
  private Type componentType_;

  public ChannelType(Type componentType) {
    this(null, componentType, NON_SHARED);
  }

  public ChannelType(Type componentType, int shared) {
    this(null, componentType, shared);
  }

  public ChannelType(Token token, Type componentType, int shared) {
    super(token);
    setComponentType(componentType);
    setChannelShared(shared);
  }

  public ChannelType setComponentType(Type componentType) {
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

  public ChannelType setChannelShared(int shared) {
    if (shared == shared_) {
      return this;
    }
    shared_ = shared;
    return this;
  }

  public int getChannelEnd() {
    return shared_;
  }

  public boolean isSharedRead() {
    return (shared_ & SHARED_READ) != 0;
  }

  public boolean isSharedWrite() {
    return (shared_ & SHARED_WRITE) != 0;
  }

  public boolean isShared() {
    return (shared_ & SHARED) != 0;
  }

  public boolean isNonShared() {
    return (shared_ & NON_SHARED) != 0;
  }

  @Override
  public boolean isChannelType() {
    return true;
  }

  @Override
  public ChannelType asChannelType() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == componentType_) {
      setComponentType((Type) replaceWith);
      return true;
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
