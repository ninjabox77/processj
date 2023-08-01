package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the integer type.
 *
 * @author Ben
 */
public class IntegerType extends Primitive {

  public IntegerType() {
    this(null, INT_KIND);
  }

  public IntegerType(Token token, final int kind) {
    super(token, kind);
  }

  @Override
  public String asString() {
    return "int";
  }

  @Override
  public boolean isIntegerType() {
    return true;
  }

  @Override
  public IntegerType asIntegerType() {
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
