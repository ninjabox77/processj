package ast.types;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.ChannelEndType;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a channel-end type in the AST.
 *
 * @author Ben
 */
public class ChannelEndNode extends ASTType {

  private ChannelEndType type_;

  public ChannelEndNode() {
    this(null, null);
  }

  public ChannelEndNode(Type type) {
    this(null, type);
  }

  public ChannelEndNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public ChannelEndNode setTSType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asConstructedType().asChannelEndType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public ChannelEndType getTSType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((ChannelEndType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isChannelEndNode() {
    return true;
  }

  @Override
  public ChannelEndNode asChannelEndNode() {
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
