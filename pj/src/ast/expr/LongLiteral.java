package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
