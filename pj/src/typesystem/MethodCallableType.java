package typesystem;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a procedure or a mobile type. Note that the default type
 * for a callable object is its returned type, and it does not yield.
 *
 * @author Ben
 */
public abstract class MethodCallableType<T extends MethodCallableType<?>> extends Constructed {

  private boolean yields_;
  private Type returnType_;
  private Sequence<Type> typeArguments_;
  private Sequence<Type> implementedTypes_;

  public MethodCallableType() {
    this(null, new VoidType(), null, null, false);
  }

  public MethodCallableType(Type type, Sequence<Type> typeArguments, final boolean yields) {
    this(null, type, typeArguments, null, yields);
  }

  public MethodCallableType(Token token, Type returnType, Sequence<Type> typeArguments, Sequence<Type> implementedTypes, final boolean yields) {
    super(token);
    setType(returnType);
    setTypeArguments(typeArguments);
    setImplementedTypes(implementedTypes);
    setYield(yields);

  }

  @Override
  public T setType(Type retuntType) {
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

  @Override
  public Type getType() {
    return returnType_;
  }

  public T setTypeArguments(Sequence<Type> typeArguments) {
    if (typeArguments == typeArguments_) {
      return (T) this;
    }
    if (typeArguments_ != null) {
      typeArguments_.setParentNode(null);
    }
    typeArguments_ = typeArguments;
    setAsParentNodeOf(typeArguments);
    return (T) this;
  }

  public Optional<Sequence<Type>> getTypeArguments() {
    return Optional.ofNullable(typeArguments_);
  }

  public T setImplementedTypes(Sequence<Type> implementedTypes) {
    if (implementedTypes == implementedTypes_) {
      return (T) this;
    }
    if (implementedTypes_ != null) {
      implementedTypes_.setParentNode(null);
    }
    implementedTypes_ = implementedTypes;
    setAsParentNodeOf(implementedTypes);
    return (T) this;
  }

  public Optional<Sequence<Type>> getImplementedTypes() {
    return Optional.ofNullable(implementedTypes_);
  }

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
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == returnType_) {
      setType((Type) replaceWith);
      return true;
    }
    if (typeArguments_ == null) {
      return false;
    }
    for (int i = 0; i < typeArguments_.size(); ++i) {
      if (node == typeArguments_.get(i)) {
        typeArguments_.set(i, (Type) replaceWith);
        return true;
      }
    }
    if (implementedTypes_ == null) {
      return false;
    }
    for (int i = 0; i < implementedTypes_.size(); ++i) {
      if (node == implementedTypes_.get(i)) {
        implementedTypes_.set(i, (Type) replaceWith);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (typeArguments_ != null) {
      for (int i = 0; i < typeArguments_.size(); ++i) {
        if (node == typeArguments_.get(i)) {
          typeArguments_.remove(i);
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
  public boolean isMethodCallableType() {
    return true;
  }

  @Override
  public MethodCallableType<?> asMethodCallableType() {
    return this;
  }

//  @Override
//  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
//    return v.visit(this, arg);
//  }
//
//  @Override
//  public <A> void accept(VoidVisitor<A> v, A arg) {
//    v.visit(this, arg);
//  }
//
//  @Override
//  public <T> T accept(DefaultVisitor<T> v) {
//    return v.visit(this);
//  }
}
