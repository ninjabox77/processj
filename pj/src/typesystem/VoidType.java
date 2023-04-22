package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents a method's returned type.
 *
 * @author Ben
 */
public class VoidType extends Type {

  public VoidType() {
    this(null);
  }

  public VoidType(Token token) {
    super(token);
  }

  @Override
  public boolean isVoidType() {
    return true;
  }

  @Override
  public VoidType asVoidType() {
    return this;
  }
}
