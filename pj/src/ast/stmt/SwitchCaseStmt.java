package ast.stmt;

import ast.Node;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a case statement in a switch statement.
 *
 * @author Ben
 */
public class SwitchCaseStmt extends Statement {

  private Sequence<Expression<?>> expressions_;
  private Sequence<Statement> statements_;

  public SwitchCaseStmt() {
    this(null);
  }

  public SwitchCaseStmt(Sequence<Expression<?>> expressions) {
    this(expressions, null);
  }

  public SwitchCaseStmt(Sequence<Expression<?>> expressions, Sequence<Statement> statements) {
    this(null, expressions, statements);
  }

  public SwitchCaseStmt(Token token, Sequence<Expression<?>> expression, Sequence<Statement> statement) {
    super(token, null);
    setExpressions(expression);
    setStatements(statement);
  }

  public SwitchCaseStmt setExpressions(Sequence<Expression<?>> expressions) {
    if (expressions == expressions_) {
      return this;
    }
    if (expressions_ != null) {
      expressions_.setParentNode(null);
    }
    expressions_ = expressions;
    setAsParentNodeOf(expressions);
    return this;
  }

  public Sequence<Expression<?>> getExpressions() {
    return expressions_;
  }

  public SwitchCaseStmt setStatements(Sequence<Statement> statements) {
    if (statements == statements_) {
      return this;
    }
    if (statements_ != null) {
      statements_.setParentNode(null);
    }
    statements_ = statements;
    setAsParentNodeOf(statements);
    return this;
  }

  public Optional<Sequence<Statement>> getStatements() {
    return Optional.ofNullable(statements_);
  }

  @Override
  public boolean isSwitchCaseStmt() {
    return true;
  }

  @Override
  public SwitchCaseStmt asSwitchCaseStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (expressions_ != null) {
      for (int i = 0; i < expressions_.size(); ++i) {
        if (node == expressions_.get(i)) {
          expressions_.set(i, (Expression<?>) replaceWith);
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

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (expressions_ != null) {
      for (int i = 0; i < expressions_.size(); ++i) {
        if (node == expressions_.get(i)) {
          expressions_.remove(i);
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
