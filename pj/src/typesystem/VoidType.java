package typesystem;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a method's returned type.
 *
 * @author Ben
 */
public class VoidType extends Type {

  public VoidType() {
    this(null);
  }

  public VoidType(Token token) {
    super(token);
  }

  // α =T β ⇔ Void?(α) ∧ Void?(β)
  @Override
  public boolean typeEqual(Type other) {
    return other.isVoidType();
  }

  // α ∼T β ⇔ α =T β
  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  // α :=T β ⇔ α =T β
  @Override
  public boolean typeAssignmentCompatible(Type other) {
    return typeEqual(other);
  }

  @Override
  public String asString() {
    return "void";
  }

  @Override
  public boolean isVoidType() {
    return true;
  }

  @Override
  public VoidType asVoidType() {
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
