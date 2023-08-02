package ast.expr;

import ast.SourceAST;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the writing end of a channel.
 *
 * @author Ben
 */
public class ChannelWriteExpression extends Invocation {

  private Expression<?> channel_;

  public ChannelWriteExpression() {
    this(null, null);
  }

  public ChannelWriteExpression(Expression<?> channel, Expression<?> expression) {
    this(null, channel, expression);
  }

  public ChannelWriteExpression(Token token, Expression<?> channel, Expression<?> expression) {
    super(token, null, null, expression);
    setChannel(channel);
  }

  public ChannelWriteExpression setChannel(Expression<?> channel) {
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
  public boolean isChannelWriteExpression() {
    return true;
  }

  @Override
  public ChannelWriteExpression asChannelWriteExpression() {
    return this;
  }

  @Override
  public ChannelWriteExpression setASTType(ASTType type) {
    return (ChannelWriteExpression) super.setASTType(type);
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
