package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the timer type.
 *
 * @author Ben
 */
public class TimerType extends Primitive {

  public TimerType() {
    this(null);
  }

  public TimerType(Token token) {
    super(token);
  }

  @Override
  public String asString() {
    return "timer";
  }

  @Override
  public boolean isTimerType() {
    return true;
  }

  @Override
  public TimerType asTimerType() {
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
