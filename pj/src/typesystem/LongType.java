package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
  public String asString() {
    return "long";
  }

  @Override
  public boolean isLongType() {
    return true;
  }

  @Override
  public LongType asLongType() {
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
