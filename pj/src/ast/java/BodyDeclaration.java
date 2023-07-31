package ast.java;

import ast.AnnotatedNode;
import ast.Modifier;
import org.antlr.v4.runtime.Token;
import scope.VariableScope;

/**
 * Represents any declaration that can appear in a class or interface.
 *
 * @author Ben
 */
public abstract class BodyDeclaration<T extends BodyDeclaration<?>> extends AnnotatedNode implements Modifier<T> {

  protected int modifiers_;

  public BodyDeclaration() {
    this(ACC_PUBLIC);
  }

  public BodyDeclaration(final int modifiers) {
    this(null, modifiers);
  }

  public BodyDeclaration(Token token, final int modifiers) {
    super(token);
    setModifiers(modifiers);
  }

  public BodyDeclaration(Token token) {
    super(token);
  }

  public boolean isFieldDeclaration() {
    return false;
  }

  public FieldDeclaration asFieldDeclaration() {
    throw new IllegalStateException(String.format("%s is not a FieldDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMethodDeclaration() {
    return false;
  }

  public MethodDeclaration<?> asMethodDeclaration() {
    throw new IllegalStateException(String.format("%s is not a MethodDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setModifiers(int modifiers) {
    if (modifiers == modifiers_) {
      return (T) this;
    }
    modifiers_ = modifiers;
    return (T) this;
  }
}
