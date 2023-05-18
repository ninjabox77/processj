package ast;

import ast.expr.Expression;
import ast.expr.IntegerLiteral;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents the dimension of an array. For example, in "new int[1][2]"
 * there are two ArrayDimension objects; the first one contains the
 * expression 1, and the second the expression 2. This is needed
 * because an array can be defined with or without a specific index;
 * that is, it can be defined as an array of arrays in which case the
 * dimension will always be 1 for each set of brackets. For example
 *
 *  - Array(int), represents a one dimensional array.
 *  - Array(Array(int)), represents a two dimensional array.
 *  - and so on
 *
 * @author Ben
 */
public class ArrayDimension extends Node {
  private Expression<?> dimension_;

  public ArrayDimension() {
    this(null, null);
  }

  public ArrayDimension(int dimension) {
    this(null, new IntegerLiteral(String.valueOf(dimension)));
  }

  public ArrayDimension(Expression<?> dimension) {
    this(null, dimension);
  }

  public ArrayDimension(Token token, Expression<?> dimension) {
    super(token);
    setDimension(dimension);
  }

  public Optional<Expression<?>> getDimension() {
    return Optional.ofNullable(dimension_);
  }

  public ArrayDimension setDimension(Expression<?> dimension) {
    if (dimension_ == dimension) {
      return this;
    }
    if (dimension_ != null) {
      dimension_.setParentNode(null);
    }
    dimension_ = dimension;
    setAsParentNodeOf(dimension);
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == dimension_) {
      setDimension((Expression<?>) replaceWith);
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
