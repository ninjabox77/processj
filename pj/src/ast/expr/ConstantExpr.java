package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents literal values.
 *
 * @author Ben
 */
public abstract class ConstantExpr extends Expression<ConstantExpr> {

  protected String value_;

  public ConstantExpr() {
    this(null);
  }

  public ConstantExpr(final String value) {
    this(null, value);
  }

  public ConstantExpr(Token token, final String value) {
    super(token);
    setValue(value);
  }

  public ConstantExpr setValue(final String value) {
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

  public boolean isCharLiteral() {
    return false;
  }

  public CharLiteral asCharLiteral() {
    throw new IllegalStateException(String.format("%s is not an CharLiteral, it is a %s", this, getClass().getSimpleName()));
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
    throw new IllegalStateException(String.format("%s is not an LongLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDoubleLiteral() {
    return false;
  }

  public DoubleLiteral asDoubleLiteral() {
    throw new IllegalStateException(String.format("%s is not an DoubleLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNullLiteral() {
    return false;
  }

  public NullLiteral asNullLiteral() {
    throw new IllegalStateException(String.format("%s is not an NullLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isConstantExpr() {
    return true;
  }

  @Override
  public ConstantExpr asConstantExpr() {
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
