package ast.stmt;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a seq { ... } block
 *
 * @author Ben
 */
public class SequentialBlock extends BlockStatement {

  public SequentialBlock() {
    this(Sequence.sequenceList());
  }

  public SequentialBlock(Sequence<Statement> statements) {
    this(null, statements);
  }

  public SequentialBlock(Token token, Sequence<Statement> statements) {
    super(token, statements);
  }

  @Override
  public boolean isSequentialBlock() {
    return true;
  }

  @Override
  public SequentialBlock asSequentialBlock() {
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
