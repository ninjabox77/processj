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
public class RegularAltStmt extends AltStmt {

  private boolean fairAlt_;

  public RegularAltStmt() {
    this(null, false);
  }

  public RegularAltStmt(Sequence<GuardStmt> guards, final boolean isFairALt) {
    this(null, guards, isFairALt);
  }

  public RegularAltStmt(Token token, Sequence<GuardStmt> guards, final boolean isFairAlt) {
    super(token, guards);
    setFairAlt(isFairAlt);
  }

  public RegularAltStmt setFairAlt(final boolean isFairAlt) {
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
  public boolean isRegularAltStmt() {
    return true;
  }

  @Override
  public RegularAltStmt asRegularAltStmt() {
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
