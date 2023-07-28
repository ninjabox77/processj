package ast.stmt;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a SKIP statement.
 *
 * @author Ben
 */
public class SkipStmt extends Statement {

  public SkipStmt() {
    this(null);
  }

  public SkipStmt(Token token) {
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
