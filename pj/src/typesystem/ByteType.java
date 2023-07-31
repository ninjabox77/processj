package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
  public String asString() {
    return "byte";
  }

  @Override
  public boolean isByteType() {
    return true;
  }

  @Override
  public ByteType asByteType() {
    return this;
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
