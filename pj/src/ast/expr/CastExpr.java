package ast.expr;

import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a typecast expression.
 *
 * @author Ben
 */
public class CastExpr extends Expression<CastExpr> {

  private Expression<?> expression_;
  private boolean coerce_;
  private boolean ignoreAutoboxing_;

  public CastExpr() {
    this(null, null, false);
  }

  public CastExpr(Type type, Expression<?> expression, final boolean coerce) {
    this(null, type, expression, coerce, false);
  }

  public CastExpr(Type type, Expression<?> expression, final boolean coerce, final boolean ignoreAutoboxing) {
    this(null, type, expression, coerce, ignoreAutoboxing);
  }

  public CastExpr(Token token, Type type, Expression<?> expression, final boolean coerce, final boolean ignoreAutoboxing) {
    super(token);
    setType(type);
    setExpression(expression);
    setCoerce(coerce);
    setIgnoreAutoboxing(ignoreAutoboxing);
  }

  public CastExpr setExpression(Expression<?> expression) {
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

  public CastExpr setCoerce(final boolean coerce) {
    if (coerce == coerce_) {
      return this;
    }
    coerce_ = coerce;
    return this;
  }

  public boolean isCoerce() {
    return coerce_;
  }

  public CastExpr setIgnoreAutoboxing(final boolean ignoreAutoboxing) {
    if (ignoreAutoboxing == ignoreAutoboxing_) {
      return this;
    }
    ignoreAutoboxing_ = ignoreAutoboxing;
    return this;
  }

  public boolean isIgnoringAutoboxing() {
    return ignoreAutoboxing_;
  }

  @Override
  public boolean isCastExpr() {
    return true;
  }

  @Override
  public CastExpr asCastExpr() {
    return this;
  }

  @Override
  public CastExpr setType(Type type) {
    return super.setType(type);
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
    return super.replace(node, replaceWith);
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
