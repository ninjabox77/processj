package ast.expr;

import org.antlr.v4.runtime.Token;

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
}
