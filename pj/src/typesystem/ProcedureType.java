package typesystem;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a procedure type.
 *
 * @author Ben
 */
public class ProcedureType extends MethodCallType<ProcedureType> {

  private boolean yields_;

  public ProcedureType() {
    this(null);
  }

  public ProcedureType(Sequence<Type> typeArguments) {
    this(null, typeArguments, false);
  }

  public ProcedureType(Token token, Sequence<Type> typeArguments, final boolean yields) {
    super(token, new VoidType(), typeArguments, null, yields);
  }

  public ProcedureType setType(Type retuntType) {
    throw new UnsupportedOperationException("Cannot change the return type of a ProcedureType");
  }

  public ProcedureType setTypeArguments(Sequence<Type> argumentsType) {
    return super.setTypeArguments(argumentsType);
  }

  @Override
  public Optional<Sequence<Type>> getTypeArguments() {
    return super.getTypeArguments();
  }

  @Override
  public boolean isProcedureType() {
    return true;
  }

  @Override
  public ProcedureType asProcedureType() {
    return this;
  }
}
