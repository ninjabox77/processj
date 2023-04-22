package typesystem;

import ast.Node;
import org.antlr.v4.runtime.Token;

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
  public boolean isArrayType() {
    return true;
  }

  @Override
  public ArrayType asArrayType() {
    return this;
  }

  @Override
  public Type getElementType() {
    return getComponentType().getElementType();
  }

  @Override
  public int getArrayLevel() {
    return 1 + getComponentType().getArrayLevel();
  }
}
