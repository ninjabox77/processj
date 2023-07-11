package ast.types;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import typesystem.VoidType;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a void type in the AST.
 *
 * @author Ben
 */
public class VoidNode extends ASTType {

  private VoidType type_;

  public VoidNode() {
    this(null);
  }

  public VoidNode(Type type) {
    this(null, type);
  }

  public VoidNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public VoidNode setTSType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asVoidType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public Type getTSType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((VoidType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isVoidNode() {
    return true;
  }

  @Override
  public VoidNode asVoidNode() {
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
