package ast.toplevel;

import ast.*;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Base class for all top level declarations.
 *
 * @author Ben
 */
public abstract class TopLevelDeclaration<T extends TopLevelDeclaration<?>> extends AnnotatedNode implements Modifier<T> {

  protected int modifiers_;
  protected ASTType type_;
  protected String name_;
  private Sequence<TopLevelDeclaration<?>> implementedNames_;

  public TopLevelDeclaration() {
    this(null, null);
  }

  public TopLevelDeclaration(ASTType type, final String name) {
    this(ACC_PRIVATE, type, name);
  }

  public TopLevelDeclaration(final int modifier, ASTType type, final String name) {
    this(modifier, type, name, null);
  }

  public TopLevelDeclaration(final int modifier, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames) {
    this(null, modifier, type, name, implementedNames);
  }

  public TopLevelDeclaration(Token token, final int modifier, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames) {
    super(token);
    setModifiers(modifier);
    setASTType(type);
    setName(name);
    setImplementedNames(implementedNames);
    customInitialization();
  }

  @Override
  public T setASTType(ASTType type) {
    if (type == type_) {
      return (T) this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    setAsParentNodeOf(type);
    return (T) this;
  }

  @Override
  public ASTType getASTType() {
    return type_;
  }

  @Override
  public T setModifiers(final int modifier) {
    if (modifier == modifiers_) {
      return (T) this;
    }
    modifiers_ = modifier;
    return (T) this;
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  public T setName(final String name) {
    if (Objects.equals(name, name_)) {
      return (T) this;
    }
    name_ = name;
    return (T) this;
  }

  public String getName() {
    return name_;
  }

  public T setImplementedNames(Sequence<TopLevelDeclaration<?>> implementNames) {
    if (implementNames == implementedNames_) {
      return (T) this;
    }
    if (implementedNames_ != null) {
      implementedNames_.setParentNode(null);
    }
    implementedNames_ = implementNames;
    setAsParentNodeOf(implementNames);
    return (T) this;
  }

  public Optional<Sequence<TopLevelDeclaration<?>>> getImplementedNames() {
    return Optional.ofNullable(implementedNames_);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (implementedNames_ != null) {
      for (int i = 0; i < implementedNames_.size(); ++i) {
        if (node == implementedNames_.get(i)) {
          implementedNames_.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setASTType((ASTType) replaceWith);
      return true;
    }
    if (implementedNames_ != null) {
      for (int i = 0; i < implementedNames_.size(); ++i) {
        if (node == implementedNames_.get(i)) {
          implementedNames_.set(i, (TopLevelDeclaration<?>) replaceWith);
          return true;
        }
      }
    }
    return false;
  }

  public boolean isConstantTopLevel() {
    return false;
  }

  public ConstantTopLevel asConstantTopLevel() {
    throw new IllegalStateException(String.format("%s is not a VariableDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordTopLevel() {
    return false;
  }

  public RecordTopLevel asRecordTopLevel() {
    throw new IllegalStateException(String.format("%s is not a RecordDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolTopLevel() {
    return false;
  }

  public ProtocolTopLevel asProtocolTopLevel() {
    throw new IllegalStateException(String.format("%s is not a ProtocolDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCallableTopLevel() {
    return false;
  }

  public CallableTopLevel<?> asCallableTopLevel() {
    throw new IllegalStateException(String.format("%s is not a MethodCallDeclaration, it is a %s", this, getClass().getSimpleName()));
  }
}
