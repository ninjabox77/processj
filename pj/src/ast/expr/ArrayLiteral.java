package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents an array literal such as new int[]{{1,2},{3,4}}, where
 * the outer { } is an ArrayLiteral that has two expressions inside,
 * which are also two ArrayLiterals.
 *
 * @author Ben
 */
public class ArrayLiteral extends ConstantExpr {

  private Sequence<Expression<?>> values_;

  public ArrayLiteral() {
    this(null, null);
  }

  public ArrayLiteral(Expression<?>... values) {
    this(null, Sequence.sequenceList(values));
  }

  public ArrayLiteral(Token token, Sequence<Expression<?>> values) {
    super(token, token.getText());
    setValues(values);
  }

  public ArrayLiteral setValues(Sequence<Expression<?>> values) {
    if (values == values_) {
      return this;
    }
    if (values_ != null) {
      values_.setParentNode(null);
    }
    values_ = values;
    setAsParentNodeOf(values);
    return this;
  }

  public Optional<Sequence<Expression<?>>> getValues() {
    return Optional.ofNullable(values_);
  }

  @Override
  public boolean isArrayLiteral() {
    return true;
  }

  @Override
  public ArrayLiteral asArrayLiteral() {
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
