package ast.stmt;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a plain par-block.
 *
 * @author Ben
 */
public class ParBlock extends ParStatement<ParBlock> {

  public ParBlock() {
    this(null);
  }

  public ParBlock(Statement loopBlock) {
    this(null, loopBlock);
  }

  public ParBlock(Token token, Statement loopBlock) {
    super(token, loopBlock);
  }

  @Override
  public boolean isParBlock() {
    return true;
  }

  @Override
  public ParBlock asParBlock() {
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
