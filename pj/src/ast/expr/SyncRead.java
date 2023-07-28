package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a sync invocation.
 *
 * @author Ben
 */
public class SyncRead extends CallableExpr {

  public SyncRead() {
    this(null, (Expression<?>) null);
  }

  public SyncRead(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public SyncRead(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public SyncRead(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public SyncRead(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isSyncRead() {
    return true;
  }

  @Override
  public SyncRead asSyncCallable() {
    return this;
  }
}
