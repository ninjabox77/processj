package ast.stmt;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a plain par-block.
 *
 * @author Ben
 */
public class ParBlock extends ParStatement<ParBlock> {

  public ParBlock() {
    this(null);
  }

  public ParBlock(Sequence<Statement> statements) {
    this(null, statements);
  }

  public ParBlock(Token token, Sequence<Statement> statements) {
    super(token, statements);
  }

  @Override
  public boolean isParBlock() {
    return true;
  }

  @Override
  public ParBlock asParBlock() {
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
