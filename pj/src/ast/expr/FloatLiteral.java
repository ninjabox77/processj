package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a floating point value.
 *
 * @author Ben
 */
public class FloatLiteral extends ConstantExpression {

  public FloatLiteral() {
    this("0.0f");
  }

  public FloatLiteral(final String value) {
    this(null, value);
  }

  public FloatLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isFloatLiteral() {
    return true;
  }

  @Override
  public FloatLiteral asFloatLiteral() {
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
