package typesystem;

import ast.Node;
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
public class MobileType extends MethodCallableType<MobileType> {

  public MobileType() {
    this(null, null);
  }

  public MobileType(Type type, Sequence<Type> typeArguments) {
    this(null, type, typeArguments, false);
  }

  public MobileType(Token token, Type type, Sequence<Type> typeArguments, final boolean yields) {
    super(token, type, typeArguments, null, yields);
  }

  public MobileType setType(Type retuntType) {
    return super.setType(retuntType);
  }

  public MobileType setTypeArguments(Sequence<Type> argumentsType) {
    return super.setTypeArguments(argumentsType);
  }

  @Override
  public Optional<Sequence<Type>> getTypeArguments() {
    return super.getTypeArguments();
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
  public boolean remove(Node node) {
    return super.remove(node);
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
