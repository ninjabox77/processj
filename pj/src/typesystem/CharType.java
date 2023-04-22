package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the character type.
 *
 * @author Ben
 */
public class CharType extends Primitive {

  public CharType() {
    this(null);
  }

  public CharType(Token token) {
    super(token);
  }

  @Override
  public boolean isCharType() {
    return true;
  }

  @Override
  public CharType asCharType() {
    return this;
  }
}
