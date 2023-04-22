package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the double type.
 *
 * @author Ben
 */
public class DoubleType extends Primitive {

  public DoubleType() {
    this(null);
  }

  public DoubleType(Token token) {
    super(token);
  }

  @Override
  public boolean isDoubleType() {
    return true;
  }

  @Override
  public DoubleType asDoubleType() {
    return this;
  }
}
