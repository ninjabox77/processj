package ast.stmt;

import ast.Node;
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
public class CaseStmt extends Statement {

  private Expression<?> expression_;
  private Statement statement_;

  public CaseStmt() {
    this(null);
  }

  public CaseStmt(Expression<?> expression) {
    this(expression, new EmptyStmt());
  }

  public CaseStmt(Expression<?> expression, Statement statement) {
    this(null, expression, statement);
  }

  public CaseStmt(Token token, Expression<?> expression, Statement statement) {
    super(token, null);
    setExpression(expression);
    setStatement(statement);
  }

  public CaseStmt setExpression(Expression<?> expression) {
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

  public CaseStmt setStatement(Statement statement) {
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
  public boolean isCaseStmt() {
    return true;
  }

  @Override
  public CaseStmt asCaseStmt() {
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
