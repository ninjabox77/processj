package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the boolean type.
 *
 * @author Ben
 */
public class BooleanType extends Primitive {

  public BooleanType() {
    this(null);
  }

  public BooleanType(Token token) {
    super(token);
  }

  @Override
  public String asString() {
    return "boolean";
  }

  @Override
  public boolean isBooleanType() {
    return true;
  }

  @Override
  public BooleanType asBooleanType() {
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
