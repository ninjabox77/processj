package ast.stmt;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents one of the following atl statements:
 * - alt { ... }
 * - alt (int i = 0; i < n; ++i) { ... }
 *
 * @author Ben
 */
public abstract class AltStmt<A extends AltStmt<?>> extends Statement {

  private Sequence<AltCase> altCases_;

  public AltStmt() {
    this(null);
  }

  public AltStmt(Sequence<AltCase> altCases) {
    this(null, altCases);
  }

  public AltStmt(Token token, Sequence<AltCase> altCases) {
    super(token, null);
    setAltCases(altCases);
  }

  public A setAltCases(Sequence<AltCase> altCases) {
    if (altCases == altCases_) {
      return (A) this;
    }
    if (altCases_ != null) {
      altCases_.setParentNode(null);
    }
    altCases_ = altCases;
    setAsParentNodeOf(altCases);
    return (A) this;
  }

  public Optional<Sequence<AltCase>> getAltCases() {
    return Optional.ofNullable(altCases_);
  }

  public boolean isRegularAltStmt() {
    return false;
  }

  public RegularAltStmt asRegularAltStmt() {
    throw new IllegalStateException(String.format("%s is not a PriAltStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isReplicatedAltStmt() {
    return false;
  }

  public ReplicatedAltStmt asReplicatedAltStmt() {
    throw new IllegalStateException(String.format("%s is not a ReplicatedAltStmt, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isAltStmt() {
    return true;
  }

  @Override
  public A asAltStmt() {
    return (A) this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (altCases_ != null) {
      for (int i = 0; i < altCases_.size(); ++i) {
        if (node == altCases_.get(i)) {
          altCases_.set(i, (AltCase) replaceWith);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (altCases_ != null) {
      for (int i = 0; i < altCases_.size(); ++i) {
        if (node == altCases_.get(i)) {
          altCases_.remove(i);
          return true;
        }
      }
    }
    return false;
  }
}
