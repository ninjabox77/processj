package ast.expr;

import ast.SourceAST;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a typecast expression.
 *
 * @author Ben
 */
public class CastExpression extends Expression<CastExpression> {

  private Expression<?> expression_;
  private boolean coerce_;
  private boolean ignoreAutoboxing_;

  public CastExpression() {
    this(null, null, false);
  }

  public CastExpression(ASTType type, Expression<?> expression, final boolean coerce) {
    this(null, type, expression, coerce, false);
  }

  public CastExpression(ASTType type, Expression<?> expression, final boolean coerce, final boolean ignoreAutoboxing) {
    this(null, type, expression, coerce, ignoreAutoboxing);
  }

  public CastExpression(Token token, ASTType type, Expression<?> expression, final boolean coerce, final boolean ignoreAutoboxing) {
    super(token);
    this.setASTType(type);
    setExpression(expression);
    setCoerce(coerce);
    setIgnoreAutoboxing(ignoreAutoboxing);
  }

  public CastExpression setExpression(Expression<?> expression) {
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

  public CastExpression setCoerce(final boolean coerce) {
    if (coerce == coerce_) {
      return this;
    }
    coerce_ = coerce;
    return this;
  }

  public boolean isCoerce() {
    return coerce_;
  }

  public CastExpression setIgnoreAutoboxing(final boolean ignoreAutoboxing) {
    if (ignoreAutoboxing == ignoreAutoboxing_) {
      return this;
    }
    ignoreAutoboxing_ = ignoreAutoboxing;
    return this;
  }

  public boolean isIgnoreAutoboxing() {
    return ignoreAutoboxing_;
  }

  @Override
  public boolean isCastExpression() {
    return true;
  }

  @Override
  public CastExpression asCastExpression() {
    return this;
  }

  @Override
  public CastExpression setASTType(ASTType type) {
    return super.setASTType(type);
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
