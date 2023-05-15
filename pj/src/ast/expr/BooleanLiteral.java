package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
