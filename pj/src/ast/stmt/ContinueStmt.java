package ast.stmt;

import ast.OptionalLabel;
import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a continue statement in a loop statement.
 *
 * @author Ben
 */
public class ContinueStmt extends Statement implements OptionalLabel<ContinueStmt> {

  private String label_;

  public ContinueStmt() {
    this(null);
  }

  public ContinueStmt(final String label) {
    this(null, label);
  }

  public ContinueStmt(Token token, final String label) {
    super(token, null);
    setLabel(label);
  }

  @Override
  public ContinueStmt setLabel(final String label) {
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
  public ContinueStmt removeLabel() {
    return setLabel(null);
  }

  @Override
  public boolean isContinueStmt() {
    return true;
  }

  @Override
  public ContinueStmt asContinueStmt() {
    return this;
  }
}
