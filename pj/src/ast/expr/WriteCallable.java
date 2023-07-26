package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a channel write invocation.
 *
 * @author Ben
 */
public class WriteCallable extends CallableExpr {

  public WriteCallable() {
    this(null, (Expression<?>) null);
  }

  public WriteCallable(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public WriteCallable(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public WriteCallable(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public WriteCallable(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isWriteCallable() {
    return true;
  }

  @Override
  public WriteCallable asWriteCallable() {
    return this;
  }
}
