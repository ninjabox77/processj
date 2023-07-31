package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents stop.
 *
 * @author Ben
 */
public class StopExpression extends Expression<StopExpression> {

  public StopExpression() {
    this(null);
  }

  public StopExpression(Token token) {
    super(token);
  }

  @Override
  public boolean isStopExpression() {
    return true;
  }

  @Override
  public StopExpression asStopExpression() {
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
