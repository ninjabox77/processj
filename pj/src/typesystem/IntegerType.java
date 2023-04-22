package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the integer type.
 *
 * @author Ben
 */
public class IntegerType extends Primitive {

  public IntegerType() {
    this(null);
  }

  public IntegerType(Token token) {
    super(token);
  }

  @Override
  public boolean isIntegerType() {
    return true;
  }

  @Override
  public IntegerType asIntegerType() {
    return this;
  }
}
