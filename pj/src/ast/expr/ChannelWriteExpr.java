package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the writing end of a channel.
 *
 * @author Ben
 */
public class ChannelWriteExpr extends Expression<ChannelWriteExpr> {

  private Expression<?> channel_;
  private Expression<?> expression_;

  public ChannelWriteExpr() {
    this(null, null);
  }

  public ChannelWriteExpr(Expression<?> channel, Expression<?> expression) {
    this(null, channel, expression);
  }

  public ChannelWriteExpr(Token token, Expression<?> channel, Expression<?> expression) {
    super(token);
    setChannel(channel);
    setExpression(expression);
  }

  public ChannelWriteExpr setChannel(Expression<?> channel) {
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

  public ChannelWriteExpr setExpression(Expression<?> expression) {
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
  public boolean isChannelWriteExpr() {
    return true;
  }

  @Override
  public ChannelWriteExpr asChannelWriteExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
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
    return super.replace(node, replaceWith);
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
