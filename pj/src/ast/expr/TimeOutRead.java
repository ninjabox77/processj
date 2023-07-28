package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a timeout invocation.
 *
 * @author Ben
 */
public class TimeOutRead extends CallableExpr {

  public TimeOutRead() {
    this(null, (Expression<?>) null);
  }

  public TimeOutRead(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public TimeOutRead(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public TimeOutRead(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public TimeOutRead(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isTimeOutRead() {
    return true;
  }

  @Override
  public TimeOutRead asTimeOutRead() {
    return this;
  }
}
