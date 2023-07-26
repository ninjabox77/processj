package ast.stmt;

import ast.Node;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a par-block.
 *
 * @author Ben
 */
public abstract class ParStmt<P extends ParStmt<P>> extends Statement {

  private Sequence<Expression<?>> barriers_;
  private Sequence<Statement> statements_;

  public ParStmt() {
    this(null);
  }

  public ParStmt(Sequence<Statement> statements) {
    this((Sequence<Expression<?>>) null, statements);
  }

  public ParStmt(Sequence<Expression<?>> barriers, Sequence<Statement> statements) {
    this(null, barriers, statements);
  }

  public ParStmt(Token token, Sequence<Statement> statements) {
    this(token, null, statements);
  }

  public ParStmt(Token token, Sequence<Expression<?>> barriers, Sequence<Statement> statements) {
    super(token, null);
    setBarriers(barriers);
    setStatemetns(statements);
  }

  public P setBarriers(Sequence<Expression<?>> barriers) {
    if (barriers == barriers_) {
      return (P) this;
    }
    if (barriers_ != null) {
      barriers_.setParentNode(null);
    }
    barriers_ = barriers;
    setAsParentNodeOf(barriers);
    return (P) this;
  }

  public Optional<Sequence<Expression<?>>> getBarriers() {
    return Optional.ofNullable(barriers_);
  }

  public P setStatemetns(Sequence<Statement> statements) {
    if (statements == statements_) {
      return (P) this;
    }
    if (statements_ != null) {
      statements_.setParentNode(null);
    }
    statements_ = statements;
    setAsParentNodeOf(statements);
    return (P) this;
  }

  public Sequence<Statement> getStatements() {
    return statements_;
  }

  public boolean isRegularParBlock() {
    return false;
  }

  public RegularParBlock asRegularParBlock() {
    throw new IllegalStateException(String.format("%s is not a RegularParBlock, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isParForBlock() {
    return false;
  }

  public ParForBlock asParForBlock() {
    throw new IllegalStateException(String.format("%s is not a ParForBlock, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (barriers_ != null) {
      for (int i = 0; i < barriers_.size(); ++i) {
        if (node == barriers_.get(i)) {
          barriers_.remove(i);
          return true;
        }
      }
    }
    if (statements_ != null) {
      for (int i = 0; i < statements_.size(); ++i) {
        if (node == statements_.get(i)) {
          statements_.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (barriers_ != null) {
      for (int i = 0; i < barriers_.size(); ++i) {
        if (node == barriers_.get(i)) {
          barriers_.set(i, (Expression<?>) replaceWith);
          return true;
        }
      }
    }
    if (statements_ != null) {
      for (int i = 0; i < statements_.size(); ++i) {
        if (node == statements_.get(i)) {
          statements_.set(i, (Statement) replaceWith);
          return true;
        }
      }
    }
    return false;
  }
}
