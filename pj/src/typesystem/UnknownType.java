package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an unknownType.
 *
 * @author Ben
 */
public class UnknownType extends Type {

  public UnknownType() {
    this(null);
  }

  public UnknownType(Token token) {
    super(token);
  }

  @Override
  public boolean isUnknownType() {
    return true;
  }

  @Override
  public UnknownType asUnknownType() {
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
