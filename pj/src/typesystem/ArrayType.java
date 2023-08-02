package typesystem;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an array type.
 *
 * @author Ben
 */
public class ArrayType extends Constructed {

  private Type componentType_;

  public ArrayType() {
    this(null, null);
  }

  public ArrayType(Type componentType) {
    this(null, componentType);
  }

  public ArrayType(Token token, Type componentType) {
    super(token);
    setComponentType(componentType);
  }

  public ArrayType setComponentType(Type componentType) {
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

  @Override
  public String asString() {
    return "[" + componentType_.asString() + ";";
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
  public boolean isArrayType() {
    return true;
  }

  @Override
  public ArrayType asArrayType() {
    return this;
  }

  // if α=Array(t1,I1) ∧ β=Array(t2,I2)
  // α =T β ⇔ Array?(α) ∧ Array?(β) ∧ (t1 =T t2) ∧ ((I1 =I2) ∨ (I1 =⊥) ∨ (I2 =⊥))
  @Override
  public boolean typeEqual(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    if (!other.asConstructedType().isArrayType()) {
      return false;
    }
    ArrayType at = other.asConstructedType().asArrayType();
    if (getArrayLevel() != at.getArrayLevel()) {
      return false;
    }
    return componentType_.typeEqual(at.componentType_);
  }

  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  @Override
  public boolean typeAssignmentCompatible(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    return typeEqual(other);
  }

  @Override
  public Type getElementType() {
    return getComponentType().getElementType();
  }

  @Override
  public int getArrayLevel() {
    return 1 + getComponentType().getArrayLevel();
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
