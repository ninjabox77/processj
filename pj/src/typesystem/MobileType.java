package typesystem;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a method type.
 *
 * @author Ben
 */
public class MobileType extends MethodCallType<MobileType> {

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
}
