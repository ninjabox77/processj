package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents skip.
 *
 * @author Ben
 */
public class SkipExpr extends Expression<SkipExpr> {

  public SkipExpr() {
    this(null);
  }

  public SkipExpr(Token token) {
    super(token);
  }

  @Override
  public boolean isSkipExpr() {
    return true;
  }

  @Override
  public SkipExpr asSkipExpr() {
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
