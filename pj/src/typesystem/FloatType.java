package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the float type.
 *
 * @author Ben
 */
public class FloatType extends Primitive {

  public FloatType() {
    this(null);
  }

  public FloatType(Token token) {
    super(token, FLOAT_KIND);
  }

  @Override
  public String asString() {
    return "float";
  }

  @Override
  public boolean isFloatType() {
    return true;
  }

  @Override
  public FloatType asFloatType() {
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
