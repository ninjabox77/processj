package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the short type.
 *
 * @author Ben
 */
public class ShortType extends Primitive {

  public ShortType() {
    this(null);
  }

  public ShortType(Token token) {
    super(token);
  }

  @Override
  public boolean isShortType() {
    return true;
  }

  @Override
  public ShortType asShortType() {
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
