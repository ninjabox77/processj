package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents an unknownType.
 *
 * @author Ben
 */
public class UnknownType extends Type {

  public UnknownType() {
    this(null);
  }

  public UnknownType(Token token) {
    super(token);
  }

  @Override
  public boolean isUnknownType() {
    return true;
  }

  @Override
  public UnknownType asUnknownType() {
    return this;
  }
}
