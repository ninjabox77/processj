package ast.toplevel;

import ast.*;
import ast.java.FieldDeclaration;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a protocol case declaration.
 *
 * @author Ben
 */
public class ProtocolCaseDeclaration extends AnnotatedNode {

  private String name_;
  private Sequence<FieldDeclaration> declaredFields_;

  public ProtocolCaseDeclaration() {
    this(null);
  }

  public ProtocolCaseDeclaration(final String name) {
    this(name, null);
  }

  public ProtocolCaseDeclaration(final String name, Sequence<FieldDeclaration> namedTags) {
    this(null, name, namedTags);
  }

  public ProtocolCaseDeclaration(Token token, final String name, Sequence<FieldDeclaration> declaredFields) {
    super(token);
    setIdentifier(name);
    setDeclaredFields(declaredFields);
  }

  public ProtocolCaseDeclaration setIdentifier(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getIdentifier() {
    return name_;
  }

  public ProtocolCaseDeclaration setDeclaredFields(Sequence<FieldDeclaration> declaredFields) {
    if (declaredFields == declaredFields_) {
      return this;
    }
    if (declaredFields_ != null) {
      declaredFields_.setParentNode(null);
    }
    declaredFields_ = declaredFields;
    setAsParentNodeOf(declaredFields);
    return this;
  }

  public Optional<Sequence<FieldDeclaration>> getDeclaredFields() {
    return Optional.ofNullable(declaredFields_);
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (declaredFields_ == null) {
      return false;
    }
    for (int i = 0; i < declaredFields_.size(); ++i) {
      if (node == declaredFields_.get(i)) {
        declaredFields_.remove(i);
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
    if (declaredFields_ == null) {
      return false;
    }
    for (int i = 0; i < declaredFields_.size(); ++i) {
      if (node == declaredFields_.get(i)) {
        declaredFields_.set(i, (FieldDeclaration) replaceWith);
        return true;
      }
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
