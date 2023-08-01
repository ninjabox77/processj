package ast.stmt;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents one of the following atl statements:
 * - alt { ... }
 * - alt (int i = 0; i < n; ++i) { ... }
 *
 * @author Ben
 */
public abstract class AltStatement<A extends AltStatement<?>> extends Statement {

  private Statement loopBlock_;

  public AltStatement() {
    this(null);
  }

  public AltStatement(Statement loopBlock) {
    this(null, loopBlock);
  }

  public AltStatement(Token token, Statement loopBlock) {
    super(token, null);
    setLoopBlock(loopBlock);
  }

  @SuppressWarnings("unchecked")
  public A setLoopBlock(Statement loopBlock) {
    if (loopBlock == loopBlock_) {
      return (A) this;
    }
    if (loopBlock_ != null) {
      loopBlock_.setParentNode(null);
    }
    loopBlock_ = loopBlock;
    setAsParentNodeOf(loopBlock);
    return (A) this;
  }

  public Statement getLoopBlock() {
    return loopBlock_;
  }

  public boolean isAltBlock() {
    return false;
  }

  public AltBlock asAltBlock() {
    throw new IllegalStateException(String.format("%s is not a AltBlock, it is a %s",
        this, getClass().getSimpleName()));
  }

  public boolean isReplicatedAltBlock() {
    return false;
  }

  public ReplicatedAltBlock asReplicatedAltBlock() {
    throw new IllegalStateException(String.format("%s is not a ReplicatedAltBlock, it is a %s",
        this, getClass().getSimpleName()));
  }

  @Override
  public boolean isAltStatement() {
    return true;
  }

  @Override
  public AltStatement<?> asAltStatement() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (loopBlock_ != null) {
      setLoopBlock((Statement) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (loopBlock_ != null) {
      setLoopBlock(null);
      return true;
    }
    return false;
  }
}
