package ast.stmt;

import ast.LabelNode;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a continue statement in a loop statement.
 *
 * @author Ben
 */
public class ContinueStatement extends Statement implements LabelNode<ContinueStatement> {

  private String label_;

  public ContinueStatement() {
    this(null);
  }

  public ContinueStatement(final String label) {
    this(null, label);
  }

  public ContinueStatement(Token token, final String label) {
    super(token, null);
    setLabel(label);
  }

  @Override
  public ContinueStatement setLabel(final String label) {
    if (Objects.equals(label, label_)) {
      return this;
    }
    label_ = label;
    return this;
  }

  @Override
  public Optional<String> getLabel() {
    return Optional.ofNullable(label_);
  }

  @Override
  public ContinueStatement removeLabel() {
    return setLabel(null);
  }

  @Override
  public boolean isContinueStatement() {
    return true;
  }

  @Override
  public ContinueStatement asContinueStmt() {
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
