package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a timeout invocation.
 *
 * @author Ben
 */
public class TimeOutCallable extends CallableExpr {

  public TimeOutCallable() {
    this(null, (Expression<?>) null);
  }

  public TimeOutCallable(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public TimeOutCallable(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public TimeOutCallable(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public TimeOutCallable(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isTimeOutCallable() {
    return true;
  }

  @Override
  public TimeOutCallable asTimeOutCallable() {
    return this;
  }
}
