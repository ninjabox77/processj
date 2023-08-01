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
public class ErrorNode extends NodeType {

  private Type type_;

  public ErrorNode() {
    this(null);
  }

  public ErrorNode(Type type) {
    this(null, type);
  }

  public ErrorNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public ErrorNode setTSType(Type type) {
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
  public Type getTSType() {
    return type_;
  }

  @Override
  public boolean isErrorNode() {
    return true;
  }

  @Override
  public ErrorNode asErrorNode() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((Type) replaceWith);
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
