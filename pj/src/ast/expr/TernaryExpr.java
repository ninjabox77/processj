package ast.expr;

import ast.Node;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a (booleanExpr)? expression : expression.
 */
public class TernaryExpr extends Expression<TernaryExpr> {

  private BooleanExpr condition_;
  private Expression<?> trueExpression_;
  private Expression<?> falseExpression_;

  public TernaryExpr() {
    this(null, null, null);
  }

  public TernaryExpr(BooleanExpr condition, Expression<?> trueExpression, Expression<?> falseExpression) {
    this(null, condition, trueExpression, falseExpression);
  }

  public TernaryExpr(Token token, BooleanExpr condition, Expression<?> trueExpression, Expression<?> falseExpression) {
    super(token);
    setCondition(condition);
    setTrueExpression(trueExpression);
    setFalseExpression(falseExpression);
  }

  public TernaryExpr setCondition(BooleanExpr condition) {
    if (condition == condition_) {
      return this;
    }
    if (condition_ != null) {
      condition_.setParentNode(null);
    }
    condition_ = condition;
    setAsParentNodeOf(condition);
    return this;
  }

  public BooleanExpr getCondition() {
    return condition_;
  }

  public TernaryExpr setTrueExpression(Expression<?> trueExpression) {
    if (trueExpression == trueExpression_) {
      return this;
    }
    if (trueExpression_ != null) {
      trueExpression_.setParentNode(null);
    }
    trueExpression_ = trueExpression;
    setAsParentNodeOf(trueExpression);
    return this;
  }

  public Expression<?> getTrueExpression() {
    return trueExpression_;
  }

  public TernaryExpr setFalseExpression(Expression<?> falseExpression) {
    if (falseExpression == falseExpression_) {
      return this;
    }
    if (falseExpression_ != null) {
      falseExpression_.setParentNode(null);
    }
    falseExpression_ = falseExpression;
    setAsParentNodeOf(falseExpression);
    return this;
  }

  public Expression<?> getFalseExpression() {
    return falseExpression_;
  }

  @Override
  public boolean isTernaryExpr() {
    return true;
  }

  @Override
  public TernaryExpr asTernaryExpr() {
    return this;
  }

  @Override
  public TernaryExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == condition_) {
      setCondition((BooleanExpr) replaceWith);
      return true;
    }
    if (node == trueExpression_) {
      setTrueExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == falseExpression_) {
      setFalseExpression((Expression<?>) replaceWith);
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
