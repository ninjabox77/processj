package ast.expr;

import org.antlr.v4.runtime.Token;

/**
 * Represents a timeout invocation.
 *
 * @author Ben
 */
public class TimeoutReadExpression extends Invocation {

  public TimeoutReadExpression() {
    this(null, new TupleExpression());
  }

  public TimeoutReadExpression(Expression<?> scope, Expression<?> arguments) {
    this(null, scope, arguments);
  }

  public TimeoutReadExpression(final String identifier, Expression<?> scope, Expression<?> arguments) {
    this(null, identifier, scope, arguments);
  }

  public TimeoutReadExpression(Token token, final String identifier, Expression<?> scope, Expression<?> arguments) {
    super(token, scope, identifier, arguments);
  }

  @Override
  public boolean isTimeoutReadExpression() {
    return true;
  }

  @Override
  public TimeoutReadExpression asTimeoutReadExpression() {
    return this;
  }
}
