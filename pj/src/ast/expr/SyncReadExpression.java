package ast.expr;

import org.antlr.v4.runtime.Token;

/**
 * Represents a sync invocation.
 *
 * @author Ben
 */
public class SyncReadExpression extends Invocation {

  public SyncReadExpression() {
    this(null, new TupleExpression());
  }

  public SyncReadExpression(Expression<?> scope, Expression<?> arguments) {
    this(null, scope, arguments);
  }

  public SyncReadExpression(final String identifier, Expression<?> scope, Expression<?> arguments) {
    this(null, identifier, scope, arguments);
  }

  public SyncReadExpression(Token token, final String identifier, Expression<?> scope, Expression<?> arguments) {
    super(token, scope, identifier, arguments);
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
