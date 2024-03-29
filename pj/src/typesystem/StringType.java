package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the string type.
 *
 * @author Ben
 */
public class StringType extends Primitive {

  public StringType() {
    this(null);
  }

  public StringType(Token token) {
    super(token, STRING_KIND);
  }

  @Override
  public String asString() {
    return "string";
  }

  @Override
  public boolean isStringType() {
    return true;
  }

  @Override
  public StringType asStringType() {
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
