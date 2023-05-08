package ast.expr;

import ast.ArrayDimension;
import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

import java.util.Optional;

/**
 * Represents an array object construction. For example, "new int[][]{...}"
 * where "int" represents the element type, "[][]" represents the level of
 * brackets, and "{...}" represents an array literal..
 *
 * @author Ben
 */
public class NewArrayExpr extends Expression<NewArrayExpr> {

  private Sequence<ArrayDimension> levels_;
  private ArrayExpr arrayLiteral_;

  public NewArrayExpr() {
    this(null, null, null);
  }

  public NewArrayExpr(Type elementType, Sequence<ArrayDimension> levels, ArrayExpr arrayLiteral) {
    this(null, elementType, levels, arrayLiteral);
  }

  public NewArrayExpr(Token token, Type elementType, Sequence<ArrayDimension> levels, ArrayExpr arrayLiteral) {
    super(token);
    setType(elementType);
    setLevels(levels);
    setArrayLiteral(arrayLiteral);
  }

  @Override
  public NewArrayExpr setType(Type type) {
    return super.setType(type);
  }

  public NewArrayExpr setLevels(Sequence<ArrayDimension> levels) {
    if (levels == levels_) {
      return this;
    }
    if (levels_ != null) {
      levels_.setParentNode(null);
    }
    levels_ = levels;
    setAsParentNodeOf(levels);
    return this;
  }

  public Optional<Sequence<ArrayDimension>> getLevels() {
    return Optional.ofNullable(levels_);
  }

  public NewArrayExpr setArrayLiteral(ArrayExpr arrayLiteral) {
    if (arrayLiteral == arrayLiteral_) {
      return this;
    }
    if (arrayLiteral_ != null) {
      arrayLiteral_.setParentNode(null);
    }
    arrayLiteral_ = arrayLiteral;
    setAsParentNodeOf(arrayLiteral);
    return this;
  }

  public ArrayExpr getArrayLiteral() {
    return arrayLiteral_;
  }

  @Override
  public boolean isNewArrayExpr() {
    return true;
  }

  @Override
  public NewArrayExpr asNewArrayExpr() {
    return this;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (levels_ != null) {
      for (int i = 0; i < levels_.size(); ++i) {
        if (node == levels_.get(i)) {
          levels_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (levels_ != null) {
      for (int i = 0; i < levels_.size(); ++i) {
        if (node == levels_.get(i)) {
          levels_.set(i, (ArrayDimension) replaceWith);
          return true;
        }
      }
    }
    if (node == arrayLiteral_) {
      setArrayLiteral((ArrayExpr) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }
}
