package ast.expr;

import ast.SourceAST;
import ast.Sequence;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a mutable list expression.
 *
 * @author Ben
 */
public class ListExpression<T extends ListExpression<?>> extends Expression<T> implements Iterable<Expression<?>> {

  protected Sequence<Expression<?>> values_;

  public ListExpression() {
    this(Sequence.sequenceList());
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

  public Sequence<Expression<?>> getValues() {
    return values_;
  }

  public Expression<?> getValue(int index) {
    Objects.checkIndex(index, values_.size());
    return values_.get(index);
  }

  public boolean isArrayInitializer() {
    return false;
  }

  public ArrayInitializer asArrayInitializer() {
    throw new IllegalStateException(String.format("%s is not a ArrayInitializer, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTupleExpression() {
    return false;
  }

  public TupleExpression asTupleExpression() {
    throw new IllegalStateException(String.format("%s is not a TupleExpression, it is a %s", this, getClass().getSimpleName()));
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
  public T setNodeType(NodeType type) {
    return (T) super.setNodeType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < values_.size(); ++i) {
      if (node == values_.get(i)) {
        values_.set(i, (Expression<?>) replaceWith);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < values_.size(); ++i) {
      if (node == values_.get(i)) {
        values_.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public <L, A> L accept(GenericVisitor<L, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <L> L accept(DefaultVisitor<L> v) {
    return v.visit(this);
  }

  @NotNull
  @Override
  public Iterator<Expression<?>> iterator() {
    return values_.iterator();
  }
}
