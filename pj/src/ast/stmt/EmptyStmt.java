package ast.stmt;

import org.antlr.v4.runtime.Token;

/**
 * Represents the empty statement or ";".
 *
 * @author Ben
 */
public class EmptyStmt extends Statement {

  public EmptyStmt() {
    this(null);
  }

  public EmptyStmt(Token token) {
    super(token, null);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean isEmptyStmt() {
    return true;
  }

  @Override
  public EmptyStmt asEmptyStmt() {
    return this;
  }
}
