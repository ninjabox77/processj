package typesystem;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a procedure type.
 *
 * @author Ben
 */
public class ProcedureType extends CallableType<ProcedureType> {

  public ProcedureType() {
    this(null);
  }

  public ProcedureType(Sequence<Type> typeArguments) {
    this(null, typeArguments, false);
  }

  public ProcedureType(Token token, Sequence<Type> typeArguments, final boolean yields) {
    super(token, new VoidType(), typeArguments, null, yields);
  }

  public ProcedureType setTSType(Type retuntType) {
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
