package ast.stmt;

import ast.OptionalLabel;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a break statement in a switch or loop statement.
 *
 * @author Ben
 */
public class BreakStmt extends Statement implements OptionalLabel<BreakStmt> {

  private String label_;

  public BreakStmt() {
    this(null);
  }

  public BreakStmt(final String label) {
    this(null, label);
  }

  public BreakStmt(Token token, final String label) {
    super(token, null);
    setLabel(label);
  }

  @Override
  public BreakStmt setLabel(final String label) {
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
  public BreakStmt removeLabel() {
    return setLabel(null);
  }

  @Override
  public boolean isBreakStmt() {
    return true;
  }

  @Override
  public BreakStmt asBreakStmt() {
    return this;
  }
}
