package typesystem;

import ast.AnnotatedNode;
import ast.NodeWithTSType;
import org.antlr.v4.runtime.Token;

/**
 * Base class for all types in the language.
 *
 * @author Ben
 */
public abstract class Type extends AnnotatedNode implements NodeWithTSType<Type, Type> {

  public Type(Token token) {
    super(token);
    customInitialization();
  }

  /**
   * Finds the element type without ArrayTypes.
   */
  public Type getElementType() {
    return this;
  }

  /**
   * Returns the array level or 0 if the type is a non array type.
   */
  public int getArrayLevel() {
    return 0;
  }

  public boolean isPrimitiveType() {
    return false;
  }

  public Primitive asPrimitiveType() {
    throw new IllegalStateException(String.format("%s is not a Primitive type, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isConstructedType() {
    return false;
  }

  public Constructed asConstructedType() {
    throw new IllegalStateException(String.format("%s is not a Constructed type, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isVoidType() {
    return false;
  }

  public VoidType asVoidType() {
    throw new IllegalStateException(String.format("%s is not a VoidType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNullType() {
    return false;
  }

  public NullType asNullType() {
    throw new IllegalStateException(String.format("%s is not a NullType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isUnknownType() {
    return false;
  }

  public UnknownType asUnknownType() {
    throw new IllegalStateException(String.format("%s is not an UnknownType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isExternalType() {
    return false;
  }

  public ExternalType asExternalType() {
    throw new IllegalStateException(String.format("%s is not an ExternalType, it is a %s", this, getClass().getSimpleName()));
  }
}
