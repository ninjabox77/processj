package ast.toplevel;

import ast.*;
import ast.types.ASTType;
import ast.types.ConstructedNode;
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
  protected Sequence<ConstructedNode> implementedNames_;

  public TopLevelDeclaration() {
    this(null, null);
  }

  public TopLevelDeclaration(ASTType type, final String name) {
    this(ACC_PRIVATE, type, name);
  }

  public TopLevelDeclaration(final int modifier, ASTType type, final String name) {
    this(modifier, type, name, null);
  }

  public TopLevelDeclaration(final int modifier, ASTType type, final String name, Sequence<ConstructedNode> implementedNames) {
    this(null, modifier, type, name, implementedNames);
  }

  public TopLevelDeclaration(Token token, final int modifier, ASTType type, final String name, Sequence<ConstructedNode> implementedNames) {
    super(token);
    setModifiers(modifier);
    setASTType(type);
    setIdentifier(name);
    setImplementedNames(implementedNames);
    customInitialization();
  }

  @SuppressWarnings("unchecked")
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

  public ASTType getASTType() {
    return type_;
  }

  @SuppressWarnings("unchecked")
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

  @SuppressWarnings("unchecked")
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

  @SuppressWarnings("unchecked")
  public T setImplementedNames(Sequence<ConstructedNode> implementNames) {
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

  public Optional<Sequence<ConstructedNode>> getImplementedNames() {
    return Optional.ofNullable(implementedNames_);
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (implementedNames_ == null) {
      return false;
    }
    for (int i = 0; i < implementedNames_.size(); ++i) {
      if (node == implementedNames_.get(i)) {
        implementedNames_.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setASTType((ASTType) replaceWith);
      return true;
    }
    if (implementedNames_ == null) {
      return false;
    }
    for (int i = 0; i < implementedNames_.size(); ++i) {
      if (node == implementedNames_.get(i)) {
        implementedNames_.set(i, (ConstructedNode) replaceWith);
        return true;
      }
    }
    return false;
  }

  public boolean isConstantDeclaration() {
    return false;
  }

  public ConstantDeclaration asConstantDeclaration() {
    throw new IllegalStateException(String.format("%s is not a ConstantDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordDeclaration() {
    return false;
  }

  public RecordDeclaration asRecordDeclaration() {
    throw new IllegalStateException(String.format("%s is not a RecordDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolDeclaration() {
    return false;
  }

  public ProtocolDeclaration asProtocolDeclaration() {
    throw new IllegalStateException(String.format("%s is not a ProtocolDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProcedureDeclaration() {
    return false;
  }

  public ProcedureDeclaration asProcedureDeclaration() {
    throw new IllegalStateException(String.format("%s is not a ProcedureDeclaration, it is a %s", this, getClass().getSimpleName()));
  }
}
