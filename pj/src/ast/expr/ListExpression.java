package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a mutable list expression.
 *
 * @author Ben
 */
public class ListExpression<T extends ListExpression<?>> extends Expression<T> {

  private Sequence<Expression<?>> values_;

  public ListExpression() {
    this(null);
  }

  public ListExpression(Sequence<Expression<?>> values) {
    this(null, values);
  }

  public ListExpression(Token token, Sequence<Expression<?>> values) {
    super(token);
    setValues(values);
  }

  public T setValues(Sequence<Expression<?>> values) {
    if (values == values_) {
      return (T) this;
    }
    if (values_ != null) {
      values_.setParentNode(null);
    }
    values_ = values;
    setAsParentNodeOf(values);
    return (T) this;
  }

  public Optional<Sequence<Expression<?>>> getValues() {
    return Optional.ofNullable(values_);
  }

  public boolean isArrayExpr() {
    return false;
  }

  public ArrayExpr asArrayExpr() {
    throw new IllegalStateException(String.format("%s is not an ArrayExpr, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isListExpression() {
    return true;
  }

  @Override
  public ListExpression<?> asListExpression() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (values_ != null) {
      for (int i = 0; i < values_.size(); ++i) {
        if (node == values_.get(i)) {
          values_.set(i, (Expression<?>) replaceWith);
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
    if (values_ != null) {
      for (int i = 0; i < values_.size(); ++i) {
        if (node == values_.get(i)) {
          values_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
  }
}
