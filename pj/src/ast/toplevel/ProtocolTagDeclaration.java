package ast.toplevel;

import ast.*;
import ast.java.FieldDeclaration;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a protocol case declaration.
 *
 * @author Ben
 */
public class ProtocolTagDeclaration extends AnnotatedNode {

  private String name_;
  private Sequence<FieldDeclaration> declaredFields_;

  public ProtocolTagDeclaration() {
    this(null);
  }

  public ProtocolTagDeclaration(final String name) {
    this(name, null);
  }

  public ProtocolTagDeclaration(final String name, Sequence<FieldDeclaration> namedTags) {
    this(null, name, namedTags);
  }

  public ProtocolTagDeclaration(Token token, final String name, Sequence<FieldDeclaration> declaredFields) {
    super(token);
    setName(name);
    setDeclaredFields(declaredFields);
  }

  public ProtocolTagDeclaration setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public ProtocolTagDeclaration setDeclaredFields(Sequence<FieldDeclaration> declaredFields) {
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
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (declaredFields_ != null) {
      for (int i = 0; i < declaredFields_.size(); ++i) {
        if (node == declaredFields_.get(i)) {
          declaredFields_.remove(i);
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
    if (declaredFields_ != null) {
      for (int i = 0; i < declaredFields_.size(); ++i) {
        if (node == declaredFields_.get(i)) {
          declaredFields_.set(i, (FieldDeclaration) replaceWith);
          return true;
        }
      }
    }
    return false;
  }
}
