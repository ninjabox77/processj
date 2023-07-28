package ast.stmt;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a STOP statement.
 *
 * @author Ben
 */
public class StopStmt extends Statement {

  public StopStmt() {
    this(null);
  }

  public StopStmt(Token token) {
    super(token, null);
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return null;
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {

  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return null;
  }
}
