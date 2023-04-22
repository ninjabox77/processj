package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a character literal value.
 *
 * @author Ben
 */
public class CharLiteral extends ConstantExpr {

  public CharLiteral() {
    this("?");
  }

  public CharLiteral(final String value) {
    this(null, value);
  }

  public CharLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isCharLiteral() {
    return true;
  }

  @Override
  public CharLiteral asCharLiteral() {
    return this;
  }

  @Override
  public CharLiteral setType(Type type) {
    return (CharLiteral) super.setType(type);
  }
}
