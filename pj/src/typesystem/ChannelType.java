package typesystem;

import ast.SourceAST;
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

  public ChannelType() {
    this(null);
  }

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
  public String asString() {
    return "chan<" + componentType_.asString() + ">";
  }

  @Override
  public boolean isChannelType() {
    return true;
  }

  @Override
  public ChannelType asChannelType() {
    return this;
  }

  // if α = Channel(t1, a1) ∧ β = Channel(t2, a2)
  // α =T β ⇔ Channel?(α) ∧ Channel?(β) ∧ (t1 =T t2) ∧ (a1 = a2)
  @Override
  public boolean typeEqual(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    // Channel?(β) -- is t a channel?
    if (!other.asConstructedType().isChannelType()) {
      return false;
    }
    ChannelType ct = other.asConstructedType().asChannelType();
    // (a1 = a2) -- are both channels' ends shared in the same way?
    if (shared_ != ct.shared_) {
      return false;
    }
    // (t1 =T t2) -- are the base types type equal?
    return ct.componentType_.typeEqual(componentType_);
  }

  // α ∼T β ⇔ α =T β
  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  // Channels cannot be assigned.
  @Override
  public boolean typeAssignmentCompatible(Type other) {
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
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
