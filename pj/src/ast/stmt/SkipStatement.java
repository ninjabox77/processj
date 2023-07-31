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
public class SkipStatement extends Statement {

  public SkipStatement() {
    this(null);
  }

  public SkipStatement(Token token) {
    super(token, null);
  }

  @Override
  public boolean isSkipStatement() {
    return true;
  }

  @Override
  public SkipStatement asSkipStatement() {
    return this;
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
