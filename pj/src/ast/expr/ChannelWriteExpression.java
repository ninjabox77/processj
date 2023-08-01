package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the writing end of a channel.
 *
 * @author Ben
 */
public class ChannelWriteExpression extends CallabelExpression {

  private Expression<?> channel_;
  private Expression<?> expression_;

  public ChannelWriteExpression() {
    this(null, null);
  }

  public ChannelWriteExpression(Expression<?> channel, Expression<?> expression) {
    this(null, channel, expression);
  }

  public ChannelWriteExpression(Token token, Expression<?> channel, Expression<?> expression) {
    super(token, null, null, null);
    setChannel(channel);
    setExpression(expression);
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

  public ChannelWriteExpression setExpression(Expression<?> expression) {
    if (expression == expression_) {
      return this;
    }
    if (expression_ != null) {
      expression_.setParentNode(null);
    }
    expression_ = expression;
    setAsParentNodeOf(expression);
    return this;
  }

  public Expression<?> getExpression() {
    return expression_;
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
  public ChannelWriteExpression setNodeType(NodeType type) {
    return (ChannelWriteExpression) super.setNodeType(type);
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
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
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
