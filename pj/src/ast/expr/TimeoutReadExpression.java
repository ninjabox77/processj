package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a timeout invocation.
 *
 * @author Ben
 */
public class TimeoutReadExpression extends CallableExpression {

  public TimeoutReadExpression() {
    this(null, (Expression<?>) null);
  }

  public TimeoutReadExpression(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public TimeoutReadExpression(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public TimeoutReadExpression(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public TimeoutReadExpression(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
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
