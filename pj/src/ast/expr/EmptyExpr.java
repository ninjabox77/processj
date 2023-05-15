package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Placeholder for any empty expression. An empty expression can be used
 * as part of an initializer.
 *
 * @author Ben
 */
public class EmptyExpr extends Expression<EmptyExpr> {

  public EmptyExpr() {
    this(null);
  }

  public EmptyExpr(Token token) {
    super(token);
  }

  @Override
  public boolean isEmptyExpr() {
    return true;
  }

  @Override
  public EmptyExpr asEmptyExpr() {
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
