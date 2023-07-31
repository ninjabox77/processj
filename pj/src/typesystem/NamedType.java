package typesystem;

import ast.Name;
import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the name of a constructed type whose actual type needs
 * to be resolved during compilation.
 *
 * @author Ben
 */
public class NamedType extends Constructed {

  private Name name_;

  public NamedType() {
    this(null);
  }

  public NamedType(Name name) {
    this(null, name);
  }

  public NamedType(Token token, Name name) {
    super(token);
  }

  public NamedType setName(Name name) {
    if (name == name_) {
      return this;
    }
    if (name_ != null) {
      name_.setParentNode(null);
    }
    name_ = name;
    setAsParentNodeOf(name);
    return this;
  }

  public Name getName() {
    return name_;
  }

  @Override
  public String asString() {
    return name_.asString();
  }

  @Override
  public boolean isNamedType() {
    return true;
  }

  @Override
  public NamedType asNamedType() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == name_) {
      setName((Name) replaceWith);
      return true;
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
