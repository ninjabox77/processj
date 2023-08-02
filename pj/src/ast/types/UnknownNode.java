package ast.types;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an error node.
 *
 * @author Ben
 */
public class UnknownNode extends ASTType {

  private Type type_;

  public UnknownNode() {
    this(null);
  }

  public UnknownNode(Type type) {
    this(null, type);
  }

  public UnknownNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public UnknownNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    return this;
  }

  @Override
  public Type getType() {
    return type_;
  }

  @Override
  public boolean isUnknownNode() {
    return true;
  }

  @Override
  public UnknownNode asUnknownNode() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setType((Type) replaceWith);
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
