package ast.expr;

import ast.SourceAST;
import ast.Sequence;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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

  public boolean isArrayInitializer() {
    return false;
  }

  public ArrayInitializer asArrayInitializer() {
    throw new IllegalStateException(String.format("%s is not an ArrayInitializer, it is a %s", this, getClass().getSimpleName()));
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
  public T setASTType(ASTType type) {
    return (T) super.setASTType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
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
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
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
