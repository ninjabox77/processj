package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;

/**
 * Represents a sync invocation.
 *
 * @author Ben
 */
public class SyncReadExpression extends CallableExpression {

  public SyncReadExpression() {
    this(null, (Expression<?>) null);
  }

  public SyncReadExpression(Expression<?> methodExpression, Expression<?>... arguments) {
    this(methodExpression, Sequence.sequenceList(arguments));
  }

  public SyncReadExpression(Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, methodExpression, arguments);
  }

  public SyncReadExpression(final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(null, identifier, methodExpression, arguments);
  }

  public SyncReadExpression(Token token, final String identifier, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token, methodExpression, identifier, arguments);
  }

  @Override
  public boolean isSyncReadExpression() {
    return true;
  }

  @Override
  public SyncReadExpression asSyncReadExpression() {
    return this;
  }
}
