package typesystem;

import org.antlr.v4.runtime.Token;

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
  public boolean isTimerType() {
    return true;
  }

  @Override
  public TimerType asTimerType() {
    return this;
  }
}
