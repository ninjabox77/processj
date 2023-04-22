package ast.expr;

import org.antlr.v4.runtime.Token;

/**
 * Represents stop.
 *
 * @author Ben
 */
public class StopExpr extends Expression<StopExpr> {

  public StopExpr() {
    this(null);
  }

  public StopExpr(Token token) {
    super(token);
  }

  @Override
  public boolean isStopExpr() {
    return true;
  }

  @Override
  public StopExpr asStopExpr() {
    return this;
  }
}
