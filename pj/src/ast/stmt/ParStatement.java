package ast.stmt;

import ast.SourceAST;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a par-block.
 *
 * @author Ben
 */
public abstract class ParStatement<P extends ParStatement<P>> extends Statement {

  private Sequence<Expression<?>> barriers_;
  private Statement loopBlock_;

  public ParStatement() {
    this(null);
  }

  public ParStatement(Statement loopBlock) {
    this((Sequence<Expression<?>>) null, loopBlock);
  }

  public ParStatement(Sequence<Expression<?>> barriers, Statement loopBlock) {
    this(null, barriers, loopBlock);
  }

  public ParStatement(Token token, Statement loopBlock) {
    this(token, null, loopBlock);
  }

  public ParStatement(Token token, Sequence<Expression<?>> barriers, Statement loopBlock) {
    super(token, null);
    setBarriers(barriers);
    setLoopBlock(loopBlock);
  }

  @SuppressWarnings("unchecked")
  public P setBarriers(Sequence<Expression<?>> barriers) {
    if (barriers == barriers_) {
      return (P) this;
    }
    if (barriers_ != null) {
      barriers_.setParentNode(null);
    }
    barriers_ = barriers;
    setAsParentNodeOf(barriers);
    return (P) this;
  }

  public Optional<Sequence<Expression<?>>> getBarriers() {
    return Optional.ofNullable(barriers_);
  }

  @SuppressWarnings("unchecked")
  public P setLoopBlock(Statement loopBlock) {
    if (loopBlock == loopBlock_) {
      return (P) this;
    }
    if (loopBlock_ != null) {
      loopBlock_.setParentNode(null);
    }
    loopBlock_ = loopBlock;
    setAsParentNodeOf(loopBlock);
    return (P) this;
  }

  public Statement getLoopBlock() {
    return loopBlock_;
  }

  public boolean isParBlock() {
    return false;
  }

  public ParBlock asParBlock() {
    throw new IllegalStateException(String.format("%s is not a ParBlock, it is a %s",
        this, getClass().getSimpleName()));
  }

  public boolean isParForBlock() {
    return false;
  }

  public ParForBlock asParForBlock() {
    throw new IllegalStateException(String.format("%s is not a ParForBlock, it is a %s",
        this, getClass().getSimpleName()));
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (barriers_ != null) {
      for (int i = 0; i < barriers_.size(); ++i) {
        if (node == barriers_.get(i)) {
          barriers_.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (barriers_ != null) {
      for (int i = 0; i < barriers_.size(); ++i) {
        if (node == barriers_.get(i)) {
          barriers_.set(i, (Expression<?>) replaceWith);
          return true;
        }
      }
    }
    if (node == loopBlock_) {
      setLoopBlock((Statement) replaceWith);
      return true;
    }
    return false;
  }
}
