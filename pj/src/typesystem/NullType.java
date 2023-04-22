package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the type for null literals.
 *
 * @author Ben
 */
public class NullType extends Type {

  public NullType() {
    this(null);
  }

  public NullType(Token token) {
    super(token);
  }
}
