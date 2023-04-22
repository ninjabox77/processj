package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents an integer literal value.
 *
 * @author Ben
 */
public class IntegerLiteral extends ConstantExpr {

  public IntegerLiteral() {
    this("0");
  }

  public IntegerLiteral(final String value) {
    this(null, value);
  }

  public IntegerLiteral(Token token, final String value) {
    super(token, value);
  }

  // TODO: asInt?

  @Override
  public boolean isIntegerLiteral() {
    return true;
  }

  @Override
  public IntegerLiteral asIntegerLiteral() {
    return this;
  }

  @Override
  public IntegerLiteral setType(Type type) {
    return (IntegerLiteral) super.setType(type);
  }
}
