package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

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
}
