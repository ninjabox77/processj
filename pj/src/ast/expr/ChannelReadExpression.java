package ast.expr;

import ast.SourceAST;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the reading end of a channel.
 *
 * @author Ben
 */
public class ChannelReadExpression extends Invocation {

  private Expression<?> channel_;

  public ChannelReadExpression() {
    this(null);
  }

  public ChannelReadExpression(Expression<?> channel) {
    this(channel, new BlockExpression());
  }

  public ChannelReadExpression(Expression<?> channel, BlockExpression externalRV) {
    this(null, channel, externalRV);
  }

  public ChannelReadExpression(Token token, Expression<?> channel, BlockExpression externalRV) {
    super(token, null, null, externalRV);
    setChannel(channel);
  }

  public ChannelReadExpression setChannel(Expression<?> channel) {
    if (channel == channel_) {
      return this;
    }
    if (channel_ != null) {
      channel_.setParentNode(null);
    }
    channel_ = channel;
    setAsParentNodeOf(channel);
    return this;
  }

  public Expression<?> getChannel() {
    return channel_;
  }

  @Override
  public boolean isChannelReadExpression() {
    return true;
  }

  @Override
  public ChannelReadExpression asChannelReadExpression() {
    return this;
  }

  @Override
  public ChannelReadExpression setASTType(ASTType type) {
    return (ChannelReadExpression) super.setASTType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == channel_) {
      setChannel((Expression<?>) replaceWith);
      return true;
    }
    if (node == arguments_) {
      setArguments((Expression<?>) replaceWith);
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
