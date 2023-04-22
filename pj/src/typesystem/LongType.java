package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents a long type.
 *
 * @author Ben
 */
public class LongType extends Primitive {

  public LongType() {
    this(null);
  }

  public LongType(Token token) {
    super(token);
  }

  @Override
  public boolean isLongType() {
    return true;
  }

  @Override
  public LongType asLongType() {
    return this;
  }
}
