package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the character type.
 *
 * @author Ben
 */
public class CharType extends Primitive {

  public CharType() {
    this(null);
  }

  public CharType(Token token) {
    super(token);
  }

  @Override
  public boolean isCharType() {
    return true;
  }

  @Override
  public CharType asCharType() {
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
