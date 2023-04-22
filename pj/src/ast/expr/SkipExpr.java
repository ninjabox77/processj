package ast.expr;

import org.antlr.v4.runtime.Token;

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
}
