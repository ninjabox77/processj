package typesystem;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure type.
 *
 * @author Ben
 */
public class ProcedureType extends CallableType<ProcedureType> {

  public ProcedureType() {
    this(Sequence.sequenceList());
  }

  public ProcedureType(Sequence<Type> typeArguments) {
    this(null, typeArguments, false);
  }

  public ProcedureType(Token token, Sequence<Type> typeArguments, final boolean yields) {
    super(token, new VoidType(), typeArguments, yields);
  }

  public ProcedureType setReturnType(Type retuntType) {
    throw new UnsupportedOperationException("Cannot change the return type of a ProcedureType");
  }

  public ProcedureType setParameterTypes(Sequence<Type> argumentsType) {
    return super.setParameterTypes(argumentsType);
  }

  @Override
  public String asString() {
    StringBuilder sb = new StringBuilder();
    var it = parameterTypes_.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(",");
      }
    }
    return "(" + sb + ") -> " + returnType_.asString();
  }

  @Override
  public boolean isProcedureType() {
    return true;
  }

  @Override
  public ProcedureType asProcedureType() {
    return this;
  }

  // α = procedure(name1, {t1,1, . . . , t1,m1 }, t1) ∧ β = procedure(name2,
  // {t2,1, . . . , t2,m2 }, t2)
  // α =T β ⇔ procedure?(α) ∧ procedure?(β) ∧ (m1 = m2) ∧ (t1 =T t2) ∧ (name1 =
  // name2) ∧ ∧^m1_i=1 (t1,i =T t2,i)
  @Override
  public boolean typeEqual(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    if (!other.asConstructedType().isCallableType() &&
        !other.asConstructedType().asCallableType().isProcedureType()) {
      return false;
    }
    ProcedureType pt = other.asConstructedType().asCallableType().asProcedureType();
    if (parameterTypes_.size() != pt.parameterTypes_.size()) {
      return false;
    }
    if (!returnType_.typeEqual(pt.returnType_)) {
      return false;
    }
    boolean b = true;
    for (int i = 0; i < parameterTypes_.size(); ++i) {
      b = b && parameterTypes_.get(i).typeEqual(pt.parameterTypes_.get(i));
    }
    return false;
  }

  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  @Override
  public boolean typeAssignmentCompatible(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    if (!other.asConstructedType().isCallableType() &&
        !other.asConstructedType().asCallableType().isProcedureType()) {
      return false;
    }
    ProcedureType pt = other.asConstructedType().asCallableType().asProcedureType();
    if (parameterTypes_.size() != pt.parameterTypes_.size()) {
      return false;
    }
    if (!returnType_.typeEqual(pt.returnType_)) {
      return false;
    }
    boolean b = true;
    for (int i = 0; i < parameterTypes_.size(); ++i) {
      b = b && parameterTypes_.get(i).typeAssignmentCompatible(pt.parameterTypes_.get(i));
    }
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
