package ast.stmt;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents one of the following atl statements:
 * - alt { ... }
 * - alt (int i = 0; i < n; ++i) { ... }
 *
 * @author Ben
 */
public abstract class AltStmt<A extends AltStmt<?>> extends Statement {

  private Sequence<GuardStmt> guards_;

  public AltStmt() {
    this(null);
  }

  public AltStmt(Sequence<GuardStmt> guards) {
    this(null, guards);
  }

  public AltStmt(Token token, Sequence<GuardStmt> guards) {
    super(token, null);
  }

  public A setGuards(Sequence<GuardStmt> guards) {
    if (guards == guards_) {
      return (A) this;
    }
    if (guards_ != null) {
      guards_.setParentNode(null);
    }
    guards_ = guards;
    setAsParentNodeOf(guards);
    return (A) this;
  }

  public Optional<Sequence<GuardStmt>> getGuards() {
    return Optional.ofNullable(guards_);
  }

  public boolean isRegularAltStmt() {
    return false;
  }

  public RegularAltStmt asRegularAltStmt() {
    throw new IllegalStateException(String.format("%s is not an PriAltStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isReplicatedAltStmt() {
    return false;
  }

  public ReplicatedAltStmt asReplicatedAltStmt() {
    throw new IllegalStateException(String.format("%s is not an ReplicatedAltStmt, it is a %s", this, getClass().getSimpleName()));
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
    if (guards_ != null) {
      for (int i = 0; i < guards_.size(); ++i) {
        if (node == guards_.get(i)) {
          guards_.set(i, (GuardStmt) replaceWith);
          return true;
        }
      }
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (guards_ != null) {
      for (int i = 0; i < guards_.size(); ++i) {
        if (node == guards_.get(i)) {
          guards_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
  }
}
