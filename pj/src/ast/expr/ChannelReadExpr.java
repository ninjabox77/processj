package ast.expr;

import ast.Node;
import ast.stmt.BlockStmt;
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
public class ChannelReadExpr extends CallableExpr {

  private Expression<?> channel_;
  private BlockExpr externalRV_;

  public ChannelReadExpr() {
    this(null);
  }

  public ChannelReadExpr(Expression<?> channel) {
    this(channel, new BlockExpr());
  }

  public ChannelReadExpr(Expression<?> channel, BlockExpr externalRV) {
    this(null, channel, externalRV);
  }

  public ChannelReadExpr(Token token, Expression<?> channel, BlockExpr externalRV) {
    super(token, null, null, null);
    setChannel(channel);
  }

  public ChannelReadExpr setChannel(Expression<?> channel) {
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
  public boolean isChannelReadExpr() {
    return true;
  }

  @Override
  public ChannelReadExpr asChannelReadExpr() {
    return this;
  }

  public ChannelReadExpr setExternalRV(BlockExpr externalRV) {
    if (externalRV == externalRV_) {
      return this;
    }
    if (externalRV_ != null) {
      externalRV_.setParentNode(null);
    }
    externalRV_ = externalRV;
    setAsParentNodeOf(externalRV);
    return this;
  }

  public BlockExpr getExternalRV() {
    return externalRV_;
  }

  @Override
  public ChannelReadExpr setASTType(ASTType type) {
    return (ChannelReadExpr) super.setASTType(type);
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
    if (node == externalRV_) {
      setExternalRV((BlockExpr) replaceWith);
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
