package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the float type.
 *
 * @author Ben
 */
public class FloatType extends Primitive {

  public FloatType() {
    this(null);
  }

  public FloatType(Token token) {
    super(token);
  }

  @Override
  public boolean isFloatType() {
    return true;
  }

  @Override
  public FloatType asFloatType() {
    return this;
  }
}
