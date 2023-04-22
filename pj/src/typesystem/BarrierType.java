package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the timer type.
 *
 * @author Ben
 */
public class BarrierType extends Primitive {

  public BarrierType(Token token) {
    super(token);
  }

  @Override
  public boolean isBarrierType() {
    return true;
  }

  @Override
  public BarrierType asBarrierType() {
    return this;
  }
}
