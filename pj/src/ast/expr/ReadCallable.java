package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a channel read invocation.
 *
 * @author Ben
 */
public class ReadCallable extends CallableExpr {

  public ReadCallable() {
    this(null, (Expression<?>) null);
  }

  public ReadCallable(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public ReadCallable(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public ReadCallable(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public ReadCallable(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isReadCallable() {
    return true;
  }

  @Override
  public ReadCallable asReadCallable() {
    return this;
  }
}
