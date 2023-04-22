package ast.stmt;

import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a guard statement.
 *
 * @author Ben
 */
public class GuardStmt extends Statement {

  private Expression<?> expression_;
  private Statement statement_;

  public GuardStmt(Expression<?> expression) {
    this(expression, null);
  }

  public GuardStmt(Expression<?> expression, Statement statement) {
    this(null, expression, statement);
  }

  private GuardStmt(Token token, Expression<?> expression, Statement statement) {
    super(token, null);
    setExpression(expression);
    setStatement(statement);
  }

  public GuardStmt setExpression(Expression<?> expression) {
    if (expression == expression_) {
      return this;
    }
    if (expression_ != null) {
      expression_.setParentNode(null);
    }
    expression_ = expression;
    setAsParentNodeOf(expression);
    return this;
  }

  public Expression<?> getExpression() {
    return expression_;
  }

  public GuardStmt setStatement(Statement statement) {
    if (statement == statement_) {
      return this;
    }
    if (statement_ != null) {
      statement_.setParentNode(null);
    }
    statement_ = statement;
    setAsParentNodeOf(statement);
    return this;
  }

  public Optional<Statement> getStatement() {
    return Optional.ofNullable(statement_);
  }

  @Override
  public boolean isGuardStmt() {
    return true;
  }

  @Override
  public GuardStmt asGuardStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == statement_) {
      setStatement((Statement) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (node == statement_) {
      setStatement(null);
      return true;
    }
    return super.remove(node);
  }
}
