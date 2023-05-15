package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the unary expression !... .
 *
 * @author Ben
 */
public class NotExpression extends BooleanExpr {

  public NotExpression() {
    this(null);
  }

  public NotExpression(Expression<?> expression) {
    this(null, expression);
  }

  public NotExpression(Token token, Expression<?> expression) {
    super(token, expression);
  }

  @Override
  public boolean isNotExpression() {
    return true;
  }

  @Override
  public NotExpression asNotExpression() {
    return this;
  }

  @Override
  public NotExpression setType(Type type) {
    return (NotExpression) super.setType(type);
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
