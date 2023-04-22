package ast.expr;

import org.antlr.v4.runtime.Token;

/**
 * Represents a true or false literal value.
 *
 * @author Ben
 */
public class BooleanLiteral extends ConstantExpr {

  public BooleanLiteral() {
    this("false");
  }

  public BooleanLiteral(final String value) {
    this(null, value);
  }

  public BooleanLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isBooleanLiteral() {
    return true;
  }

  @Override
  public BooleanLiteral asBooleanLiteral() {
    return this;
  }
}
