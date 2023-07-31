package ast.stmt;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents the empty statement or ";".
 *
 * @author Ben
 */
public class EmptyStatement extends Statement {

  public EmptyStatement() {
    this(null);
  }

  public EmptyStatement(Token token) {
    super(token, null);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean isEmptyStatement() {
    return true;
  }

  @Override
  public EmptyStatement asEmptyStatement() {
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
