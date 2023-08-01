package typesystem;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a procedure or a mobile type. Note that the default type
 * for a callable object is its returned type, and it does not yield.
 *
 * @author Ben
 */
public abstract class CallableType<T extends CallableType<?>> extends Constructed {

  protected boolean yields_;
  protected Type returnType_;
  protected Sequence<Type> parameterTypes_;

  public CallableType() {
    this(null, new VoidType(), null, false);
  }

  public CallableType(Type type, Sequence<Type> parameterTypes, final boolean yields) {
    this(null, type, parameterTypes, yields);
  }

  public CallableType(Token token, Type returnType, Sequence<Type> parameterTypes, final boolean yields) {
    super(token);
    setReturnType(returnType);
    setParameterTypes(parameterTypes);
    setYield(yields);

  }

  @SuppressWarnings("unchecked")
  public T setReturnType(Type retuntType) {
    if (retuntType == returnType_) {
      return (T) this;
    }
    if (returnType_ != null) {
      returnType_.setParentNode(null);
    }
    returnType_ = retuntType;
    setAsParentNodeOf(retuntType);
    return (T) this;
  }

  public Type getReturnType() {
    return returnType_;
  }

  @SuppressWarnings("unchecked")
  public T setParameterTypes(Sequence<Type> typeArguments) {
    if (typeArguments == parameterTypes_) {
      return (T) this;
    }
    if (parameterTypes_ != null) {
      parameterTypes_.setParentNode(null);
    }
    parameterTypes_ = typeArguments;
    setAsParentNodeOf(typeArguments);
    return (T) this;
  }

  public Optional<Sequence<Type>> getParameterTypes() {
    return Optional.ofNullable(parameterTypes_);
  }

  @SuppressWarnings("unchecked")
  public T setYield(final boolean yields) {
    if (yields == yields_) {
      return (T) this;
    }
    yields_ = yields;
    return (T) this;
  }

  public boolean doesYield() {
    return yields_;
  }

  public boolean isProcedureType() {
    return false;
  }

  public ProcedureType asProcedureType() {
    throw new IllegalStateException(String.format("%s is not a ProcedureType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMobileType() {
    return false;
  }

  public MobileType asMobileType() {
    throw new IllegalStateException(String.format("%s is not a MobileType, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == returnType_) {
      setReturnType((Type) replaceWith);
      return true;
    }
    if (parameterTypes_ != null) {
      for (int i = 0; i < parameterTypes_.size(); ++i) {
        if (node == parameterTypes_.get(i)) {
          parameterTypes_.set(i, (Type) replaceWith);
          return true;
        }
      }
    }
    if (implementedTypes_ != null) {
      for (int i = 0; i < implementedTypes_.size(); ++i) {
        if (node == implementedTypes_.get(i)) {
          implementedTypes_.set(i, (Type) replaceWith);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (parameterTypes_ != null) {
      for (int i = 0; i < parameterTypes_.size(); ++i) {
        if (node == parameterTypes_.get(i)) {
          parameterTypes_.remove(i);
          return true;
        }
      }
    }
    if (implementedTypes_ != null) {
      for (int i = 0; i < implementedTypes_.size(); ++i) {
        if (node == implementedTypes_.get(i)) {
          implementedTypes_.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isCallableType() {
    return true;
  }

  @Override
  public CallableType<?> asCallableType() {
    return this;
  }
}
