package ast.types;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Constructed;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the type of a constructed type in the AST.
 *
 * @author Ben
 */
public class ConstructedNode extends ASTType {

  Constructed type_;

  public ConstructedNode() {
    this(null);
  }

  public ConstructedNode(Type type) {
    this(null, type);
  }

  public ConstructedNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public ConstructedNode setTSType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    assert type_ != null;
    type_ = type_.asConstructedType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public Constructed getTSType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((Constructed) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isConstructedNode() {
    return true;
  }

  @Override
  public ConstructedNode asConstructedNode() {
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
