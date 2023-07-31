package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a string literal value.
 *
 * @author Ben
 */
public class StringLiteral extends ConstantExpression {

  public StringLiteral() {
    this("");
  }

  public StringLiteral(final String value) {
    this(null, value);
  }

  public StringLiteral(Token token, final String value) {
    super(token, value);
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
