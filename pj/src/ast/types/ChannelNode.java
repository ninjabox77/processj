package ast.types;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import typesystem.ChannelType;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a channel type in the AST.
 *
 * @author Ben
 */
public class ChannelNode extends ASTType {

  private ChannelType type_;

  public ChannelNode() {
    this(null, null);
  }

  public ChannelNode(Type type) {
    this(null, type);
  }

  public ChannelNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public ChannelNode setTSType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asConstructedType().asChannelType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public ChannelType getTSType() {
    return type_;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((ChannelType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isChannelNode() {
    return true;
  }

  @Override
  public ChannelNode asChannelNode() {
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
