package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a long literal value.
 *
 * @author Ben
 */
public class LongLiteral extends ConstantExpr {

  public LongLiteral() {
    this("0");
  }

  public LongLiteral(final String value) {
    this(null, value);
  }

  public LongLiteral(Token token, final String value) {
    super(token, value);
  }

  // TODO: asLong?

  @Override
  public boolean isLongLiteral() {
    return true;
  }

  @Override
  public LongLiteral asLongLiteral() {
    return this;
  }

  @Override
  public LongLiteral setType(Type type) {
    return (LongLiteral) super.setType(type);
  }
}
