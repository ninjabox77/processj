package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents the byte type.
 *
 * @author Ben
 */
public class ByteType extends Primitive {

  public ByteType() {
    this(null);
  }

  public ByteType(Token token) {
    super(token);
  }

  @Override
  public boolean isByteType() {
    return true;
  }

  @Override
  public ByteType asByteType() {
    return this;
  }
}
