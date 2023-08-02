package ast.java;

import ast.AnnotatedNode;
import ast.Modifier;
import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Base class for (some) type declarations in Java. This should only
 * be used during the code generation phase of the compiler.
 *
 * @author Ben
 */
public abstract class TypeDeclaration<T extends TypeDeclaration<?>> extends AnnotatedNode implements Modifier<T> {

  private int modifiers_;
  private String name_;
  private Sequence<BodyDeclaration<?>> declarations_;

  public TypeDeclaration() {
    this(ACC_PUBLIC, null, null);
  }

  public TypeDeclaration(final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations) {
    this(null, modifiers, name, declarations);
  }

  public TypeDeclaration(Token token, final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations) {
    super(token);
    setModifiers(modifiers);
    setIdentifier(name);
    setDeclarations(declarations);
  }

  public T setIdentifier(final String name) {
    if (Objects.equals(name, name_)) {
      return (T) this;
    }
    name_ = name;
    return (T) this;
  }

  public String getIdentifier() {
    return name_;
  }

  public T setDeclarations(Sequence<BodyDeclaration<?>> declarations) {
    if (declarations == declarations_) {
      return (T) this;
    }
    if (declarations_ != null) {
      declarations_.setParentNode(null);
    }
    declarations_ = declarations;
    setAsParentNodeOf(declarations);
    return (T) this;
  }

  public Optional<Sequence<BodyDeclaration<?>>> getDeclarations() {
    return Optional.ofNullable(declarations_);
  }

  public boolean isClassDeclaration() {
    return false;
  }

  public ClassDeclaration asClassDeclaration() {
    throw new IllegalStateException(String.format("%s is not a ClassDeclaration, it is a %s",
        this, getClass().getSimpleName()));
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  @Override
  public T setModifiers(int modifiers) {
    if (modifiers == modifiers_) {
      return (T) this;
    }
    modifiers_ = modifiers;
    return (T) this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (declarations_ != null) {
      for (int i = 0; i < declarations_.size(); ++i) {
        if (node == declarations_.get(i)) {
          declarations_.set(i, (BodyDeclaration<?>) replaceWith);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (declarations_ != null) {
      for (int i = 0; i < declarations_.size(); ++i) {
        if (node == declarations_.get(i)) {
          declarations_.remove(i);
          return true;
        }
      }
    }
    return false;
  }
}
