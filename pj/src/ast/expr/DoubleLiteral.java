package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a double literal value.
 *
 * @author Ben
 */
public class DoubleLiteral extends ConstantExpr {

  public DoubleLiteral() {
    this("0");
  }

  public DoubleLiteral(final String value) {
    this(null, value);
  }

  public DoubleLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isDoubleLiteral() {
    return true;
  }

  @Override
  public DoubleLiteral asDoubleLiteral() {
    return this;
  }

  @Override
  public DoubleLiteral setType(Type type) {
    return (DoubleLiteral) super.setType(type);
  }
}
