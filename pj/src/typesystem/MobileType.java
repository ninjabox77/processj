package typesystem;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a method type.
 *
 * @author Ben
 */
public class MobileType extends CallableType<MobileType> {

  public MobileType() {
    this(null, null);
  }

  public MobileType(Type type, Sequence<Type> typeArguments) {
    this(null, type, typeArguments, false);
  }

  public MobileType(Token token, Type type, Sequence<Type> typeArguments, final boolean yields) {
    super(token, type, typeArguments, yields);
  }

  public MobileType setReturnType(Type retuntType) {
    return super.setReturnType(retuntType);
  }

  public MobileType setParameterTypes(Sequence<Type> argumentsType) {
    return super.setParameterTypes(argumentsType);
  }

  @Override
  public String asString() {
    return null;
  }

  @Override
  public boolean isMobileType() {
    return true;
  }

  @Override
  public MobileType asMobileType() {
    return this;
  }

  @Override
  public boolean typeEqual(Type other) {
    return false;
  }

  @Override
  public boolean typeEquivalent(Type other) {
    return false;
  }

  @Override
  public boolean typeAssignmentCompatible(Type other) {
    return false;
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
