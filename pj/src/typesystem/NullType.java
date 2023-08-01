package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the type for null literals.
 *
 * @author Ben
 */
public class NullType extends Type {

  public NullType() {
    this(null);
  }

  public NullType(Token token) {
    super(token);
  }

  @Override
  public boolean typeEqual(Type other) {
    return other.isNullType();
  }

  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  @Override
  public boolean typeAssignmentCompatible(Type other) {
    return typeEquivalent(other);
  }

  @Override
  public String asString() {
    return "null";
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
