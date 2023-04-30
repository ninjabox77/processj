package ast;

import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a name that may consist of multiple identifiers, where
 * the right most identifier is an identifier, and the one to the
 * left of it is qualifier name.
 *
 * @author Ben
 */
public class Name extends AnnotatedNode {

  private String identifier_;
  private Name qualifier_;

  public Name() {
    this(null);
  }

  public Name(final String identifier) {
    this(null, null, identifier);
  }

  public Name(Name qualifier, final String identifier) {
    this(null, qualifier, identifier);
  }

  public Name(Token token, Name qualifier, final String identifier) {
    super(token);
    setQualifier(qualifier);
    setIdentifier(identifier);
  }

  public Name setQualifier(Name qualifier) {
    if (qualifier == qualifier_) {
      return this;
    }
    if (qualifier != null) {
      qualifier_.setParentNode(null);
    }
    qualifier_ = qualifier;
    setAsParentNodeOf(qualifier);
    return this;
  }

  public Name removeQualifier() {
    return setQualifier(null);
  }

  public Optional<Name> getQualifier() {
    return Optional.ofNullable(qualifier_);
  }

  public Name setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (node == qualifier_) {
      removeQualifier();
      return true;
    }
    return false;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == qualifier_) {
      setQualifier((Name) replaceWith);
      return true;
    }
    return false;
  }

  /**
   * Returns true if this name is not part of a larger Name.
   */
  public boolean isTopLevel() {
    return !isInternal();
  }

  /**
   * Returns true if this name is a part of a larger Name instance.
   */
  public boolean isInternal() {
    return getParentNode().filter(parent -> parent instanceof Name).isPresent();
  }
}
