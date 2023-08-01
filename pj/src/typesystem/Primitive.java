package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents all primitive (or atomic) types.
 *
 * @author Ben
 */
public abstract class Primitive extends Type {

  public final static int BOOLEAN_KIND = 0;    // either true or false
  public final static int BYTE_KIND = 1;       // 8 bit signed:                  -128 -> 127
  public final static int SHORT_KIND = 2;      // 16 bit signed:               -32768 -> 32767
  public final static int CHAR_KIND = 3;       // 16 bit unicode
  public final static int INT_KIND = 4;        // 32 bit signed:          -2147483648 -> 2147483647
  public final static int LONG_KIND = 5;       // 64 bit signed: -9223372036854775808 -> 9223372036854775807

  public final static int FLOAT_KIND = 6;      // 32 bit IEEE 754-1985. min: 1.4e-45f max: 3.4028235e+38f
  public final static int DOUBLE_KIND = 7;     // 64 bit IEEE 754-1985. min: 5e-324   max: 1.7976931348623157e+308

  public final static int STRING_KIND = 8;     // 1 bit signed * length of string
  public final static int NULL_KIND = 9;       // null

  public final static int BARRIER_KIND = 10;   // No literal exists of this type;
  public final static int TIMER_KIND = 11;     // No literal exists of this type;

  protected int kind_;

  public Primitive() {
    this(null, INT_KIND);
  }

  public Primitive(Token token, int kind) {
    super(token);
    setKind(kind);
  }

  public Primitive setKind(final int kind) {
    if (kind == kind_) {
      return this;
    }
    kind_ = kind;
    return this;
  }

  public int getKind() {
    return kind_;
  }

  public boolean isNumeric() {
    return isFloatType() || isDoubleType() || isIntegralType();
  }

  public boolean isIntegralType() {
    return isIntegerType() || isShortType() || isByteType() || isCharType() || isLongType();
  }

  // α =T β ⇔ Primitive?(α) ∧ Primitive?(β) ∧ α = β
  @Override
  public boolean typeEqual(Type other) {
    if (!other.isPrimitiveType()) {
      return false;
    }
    Primitive pt = other.asPrimitiveType();
    return kind_ == pt.kind_;
  }

  // α ∼T β ⇔ Primitive?(α) ∧ Primitive?(β) ∧ α =T β
  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  // α :=T β ⇔ Primitive?(α) ∧ Primitive?(β) ∧ β ≤ α
  @Override
  public boolean typeAssignmentCompatible(Type other) {
    if (!other.isPrimitiveType()) {
      return false;
    }
    Primitive pt = other.asPrimitiveType();
    return other.typeLessThanEqual(this);
  }

  // α <T β ⇔ Numeric?(α) ∧ Numeric?(β)
  // Definition:
  // byte <T short <T char <T int <T long <T float <T double
  @Override
  public boolean typeLessThan(Type other) {
    if (!other.isPrimitiveType()) {
      return false;
    }
    Primitive pt = other.asPrimitiveType();
    return kind_ < pt.kind_;
  }

  // α <=T β ⇔ Primitive?(α) ∧ Primitive?(β) ∧
  // (α =T β || (Numeric?(α) ∧ Numeric?(β) ∧ α <T β))
  @Override
  public boolean typeLessThanEqual(Type other) {
    if (!other.isPrimitiveType()) {
      return false;
    }
    if (other.typeEqual(this)) {
      return true;
    }
    return typeLessThan(other);
  }

  @Override
  public Type typeCeiling(Type other) {
    if (!other.isPrimitiveType()) {
      throw new IllegalStateException(String.format("\"%s\" must be a primitive type.",
          other.getClass().getSimpleName()));
    }
    Primitive pt = other.asPrimitiveType();
    if (!isNumeric() || !pt.isNumeric()) {
      throw new IllegalStateException(String.format("\"%s\" or \"%s\" are not numeric types.",
          getClass().getSimpleName(), other.getClass().getSimpleName()));
    }
    if (kind_ < INT_KIND && pt.kind_ < INT_KIND) {
      return new IntegerType();
    }
    if (kind_ < pt.kind_) {
      return other;
    }
    return this;
  }

  @Override
  public boolean isPrimitiveType() {
    return true;
  }

  @Override
  public Primitive asPrimitiveType() {
    return this;
  }

  public boolean isIntegerType() {
    return false;
  }

  public IntegerType asIntegerType() {
    throw new IllegalStateException(String.format("%s is not a IntegerType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isShortType() {
    return false;
  }

  public ShortType asShortType() {
    throw new IllegalStateException(String.format("%s is not a ShortType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBooleanType() {
    return false;
  }

  public BooleanType asBooleanType() {
    throw new IllegalStateException(String.format("%s is not a BooleanType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isLongType() {
    return false;
  }

  public LongType asLongType() {
    throw new IllegalStateException(String.format("%s is not a LongType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isFloatType() {
    return false;
  }

  public FloatType asFloatType() {
    throw new IllegalStateException(String.format("%s is not a FloatType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDoubleType() {
    return false;
  }

  public DoubleType asDoubleType() {
    throw new IllegalStateException(String.format("%s is not a DoubleType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCharType() {
    return false;
  }

  public CharType asCharType() {
    throw new IllegalStateException(String.format("%s is not a CharType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isByteType() {
    return false;
  }

  public ByteType asByteType() {
    throw new IllegalStateException(String.format("%s is not a ByteType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTimerType() {
    return false;
  }

  public TimerType asTimerType() {
    throw new IllegalStateException(String.format("%s is not a TimerType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBarrierType() {
    return false;
  }

  public BarrierType asBarrierType() {
    throw new IllegalStateException(String.format("%s is not a BarrierType, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isStringType() {
    return false;
  }

  public StringType asStringType() {
    throw new IllegalStateException(String.format("%s is not a StringType, it is a %s", this, getClass().getSimpleName()));
  }
}
