package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the short type.
 *
 * @author Ben
 */
public class ShortType extends Primitive {

  public ShortType() {
    this(null);
  }

  public ShortType(Token token) {
    super(token);
  }

  @Override
  public boolean isShortType() {
    return true;
  }

  @Override
  public ShortType asShortType() {
    return this;
  }
}
