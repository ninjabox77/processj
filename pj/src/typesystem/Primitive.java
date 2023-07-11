package typesystem;

import org.antlr.v4.runtime.Token;

/**
 * Represents all primitive (or atomic) types.
 *
 * @author Ben
 */
public abstract class Primitive extends Type {

  public Primitive() {
    this(null);
  }

  public Primitive(Token token) {
    super(token);
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
