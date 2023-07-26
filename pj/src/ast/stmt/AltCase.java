package ast.stmt;

import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a alt-statement in a alt block.
 *
 * @author Ben
 */
public class AltCase extends Statement {

  private Expression<?> expression_;
  private Guard guard_;
  private Statement statement_;

  public AltCase(Guard guard) {
    this(guard, null);
  }

  public AltCase(Statement statement) {
    this(null, statement);
  }

  public AltCase(Guard guard, Statement statement) {
    this(null, guard, statement);
  }

  public AltCase(Expression<?> expression, Guard guard, Statement statement) {
    this(null, expression, guard, statement);
  }

  public AltCase(Token token, Expression<?> expression, Guard guard, Statement statement) {
    super(token, null);
    setExpression(expression);
    setGuard(guard);
    setStatement(statement);
  }

  public AltCase setExpression(Expression<?> expression) {
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

  public AltCase setGuard(Guard guard) {
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

  public AltCase setStatement(Statement statement) {
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

  public boolean isAltStmt() {
    return expression_ == null && guard_ == null;
  }

  @Override
  public boolean isAltCase() {
    return true;
  }

  @Override
  public AltCase asAltCase() {
    return this;
  }

  @Override
  public boolean remove(Node node) {
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
  public boolean replace(Node node, Node replaceWith) {
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
