package ast.types;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import typesystem.Primitive;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a primitive type in the AST.
 *
 * @author Ben
 */
public class PrimitiveNode extends NodeType {

  private Primitive type_;

  public PrimitiveNode() {
    this(null);
  }

  public PrimitiveNode(Type type) {
    this(null, type);
  }

  public PrimitiveNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public PrimitiveNode setTSType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asPrimitiveType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public Primitive getTSType() {
    return type_;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((Primitive) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isPrimitiveNode() {
    return true;
  }

  @Override
  public PrimitiveNode asPrimitiveNode() {
    return this;
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
