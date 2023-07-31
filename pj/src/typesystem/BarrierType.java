package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the timer type.
 *
 * @author Ben
 */
public class BarrierType extends Primitive {

  public BarrierType() {
    this(null);
  }

  public BarrierType(Token token) {
    super(token);
  }

  @Override
  public String asString() {
    return "barrier";
  }

  @Override
  public boolean isBarrierType() {
    return true;
  }

  @Override
  public BarrierType asBarrierType() {
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
