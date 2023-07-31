package ast.stmt;

import ast.SourceAST;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents an alt case statement in an alt-block.
 *
 * @author Ben
 */
public class AltCaseStatement extends Statement {

  private Expression<?> expression_;
  private Guard guard_;
  private Statement statement_;

  public AltCaseStatement(Guard guard) {
    this(guard, null);
  }

  public AltCaseStatement(Statement statement) {
    this(null, statement);
  }

  public AltCaseStatement(Guard guard, Statement statement) {
    this(null, guard, statement);
  }

  public AltCaseStatement(Expression<?> expression, Guard guard, Statement statement) {
    this(null, expression, guard, statement);
  }

  public AltCaseStatement(Token token, Expression<?> expression, Guard guard, Statement statement) {
    super(token, null);
    setExpression(expression);
    setGuard(guard);
    setStatement(statement);
  }

  public AltCaseStatement setExpression(Expression<?> expression) {
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

  public Optional<Expression<?>> getExpression() {
    return Optional.ofNullable(expression_);
  }

  public AltCaseStatement setGuard(Guard guard) {
    if (guard == guard_) {
      return this;
    }
    if (guard_ != null) {
      guard_.setParentNode(null);
    }
    guard_ = guard;
    setAsParentNodeOf(guard);
    return this;
  }

  public Guard getGuard() {
    return guard_;
  }

  public AltCaseStatement setStatement(Statement statement) {
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

  public Statement getStatement() {
    return statement_;
  }

  public boolean isAltStatement() {
    return expression_ == null && guard_ == null;
  }

  @Override
  public boolean isAltCaseStatement() {
    return true;
  }

  @Override
  public AltCaseStatement asAltCaseStatement() {
    return this;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (node == expression_) {
      setExpression(null);
      return true;
    }
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == guard_) {
      setGuard((Guard) replaceWith);
      return true;
    }
    if (node == statement_) {
      setStatement((Statement) replaceWith);
      return true;
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
