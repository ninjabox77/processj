package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the string type.
 *
 * @author Ben
 */
public class StringType extends Primitive {

  public StringType() {
    this(null);
  }

  public StringType(Token token) {
    super(token);
  }

  @Override
  public boolean isStringType() {
    return true;
  }

  @Override
  public StringType asStringType() {
    return this;
  }
}
