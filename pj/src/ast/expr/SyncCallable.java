package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a sync invocation.
 *
 * @author Ben
 */
public class SyncCallable extends CallableExpr {

  public SyncCallable() {
    this(null, (Expression<?>) null);
  }

  public SyncCallable(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public SyncCallable(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public SyncCallable(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public SyncCallable(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isSyncCallable() {
    return true;
  }

  @Override
  public SyncCallable asSyncCallable() {
    return this;
  }
}
