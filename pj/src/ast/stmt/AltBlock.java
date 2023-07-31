package ast.stmt;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a pri or fair alt.
 *
 * @author Ben
 */
public class AltBlock extends AltStatement<AltBlock> {

  private boolean fairAlt_;

  public AltBlock() {
    this(null, false);
  }

  public AltBlock(Statement loopBlock, final boolean isFairALt) {
    this(null, loopBlock, isFairALt);
  }

  public AltBlock(Token token, Statement loopBlock, final boolean isFairAlt) {
    super(token, loopBlock);
    setFairAlt(isFairAlt);
  }

  public AltBlock setFairAlt(final boolean isFairAlt) {
    if (isFairAlt == fairAlt_) {
      return this;
    }
    fairAlt_ = isFairAlt;
    return this;
  }

  public boolean isFairAlt() {
    return fairAlt_;
  }

  @Override
  public boolean isAltBlock() {
    return true;
  }

  @Override
  public AltBlock asAltBlock() {
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
