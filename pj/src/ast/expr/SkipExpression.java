package ast.expr;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents skip.
 *
 * @author Ben
 */
public class SkipExpression extends Expression<SkipExpression> {

  public SkipExpression() {
    this(null);
  }

  public SkipExpression(Token token) {
    super(token);
  }

  @Override
  public boolean isSkipExpression() {
    return true;
  }

  @Override
  public SkipExpression asSkipExpression() {
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
