package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the double type.
 *
 * @author Ben
 */
public class DoubleType extends Primitive {

  public DoubleType() {
    this(null);
  }

  public DoubleType(Token token) {
    super(token, DOUBLE_KIND);
  }

  @Override
  public String asString() {
    return "double";
  }

  @Override
  public boolean isDoubleType() {
    return true;
  }

  @Override
  public DoubleType asDoubleType() {
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
