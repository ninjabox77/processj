package ast.expr;

import org.antlr.v4.runtime.Token;

import java.util.Objects;

/**
 * Represents literal values.
 *
 * @author Ben
 */
public abstract class ConstantExpression extends Expression<ConstantExpression> {

  protected String value_;

  public ConstantExpression() {
    this(null);
  }

  public ConstantExpression(final String value) {
    this(null, value);
  }

  public ConstantExpression(Token token, final String value) {
    super(token);
    setValue(value);
  }

  public ConstantExpression setValue(final String value) {
    if (Objects.equals(value, value_)) {
      return this;
    }
    value_ = value;
    return this;
  }

  public String getValue() {
    return value_;
  }

  public boolean isBooleanLiteral() {
    return false;
  }

  public BooleanLiteral asBooleanLiteral() {
    throw new IllegalStateException(String.format("%s is not a BooleanLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCharacterLiteral() {
    return false;
  }

  public CharacterLiteral asCharacterLiteral() {
    throw new IllegalStateException(String.format("%s is not a CharacterLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isIntegerLiteral() {
    return false;
  }

  public IntegerLiteral asIntegerLiteral() {
    throw new IllegalStateException(String.format("%s is not an IntegerLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isLongLiteral() {
    return false;
  }

  public LongLiteral asLongLiteral() {
    throw new IllegalStateException(String.format("%s is not a LongLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isFloatLiteral() {
    return false;
  }

  public FloatLiteral asFloatLiteral() {
    throw new IllegalStateException(String.format("%s is not a FloatLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDoubleLiteral() {
    return false;
  }

  public DoubleLiteral asDoubleLiteral() {
    throw new IllegalStateException(String.format("%s is not a DoubleLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isStringLiteral() {
    return false;
  }

  public StringLiteral asStringLiteral() {
    throw new IllegalStateException(String.format("%s is not a StringLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNullLiteral() {
    return false;
  }

  public NullLiteral asNullLiteral() {
    throw new IllegalStateException(String.format("%s is not a NullLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isConstantExpression() {
    return true;
  }

  @Override
  public ConstantExpression asConstantExpression() {
    return this;
  }
}
