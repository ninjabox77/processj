package ast.stmt;

import ast.NodeWithLabel;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a break statement in a switch or loop statement.
 *
 * @author Ben
 */
public class BreakStatement extends Statement implements NodeWithLabel<BreakStatement> {

  private String label_;

  public BreakStatement() {
    this(null);
  }

  public BreakStatement(final String label) {
    this(null, label);
  }

  public BreakStatement(Token token, final String label) {
    super(token, null);
    setLabel(label);
  }

  @Override
  public BreakStatement setLabel(final String label) {
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
  public BreakStatement removeLabel() {
    return setLabel(null);
  }

  @Override
  public boolean isBreakStatement() {
    return true;
  }

  @Override
  public BreakStatement asBreakStatement() {
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
