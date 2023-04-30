package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a record literal value that contains a list of declarations.
 *
 * @author Ben
 */
public class RecordExpr extends Expression<RecordExpr> {

  private Sequence<DeclarationExpr> values_;

  public RecordExpr() {
    this(null);
  }

  public RecordExpr(Sequence<DeclarationExpr> values) {
    this(null, values);
  }

  public RecordExpr(Token token, Sequence<DeclarationExpr> valueExpressions) {
    super(token);
    setValues(valueExpressions);
  }

  public RecordExpr setValues(Sequence<DeclarationExpr> values) {
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

  public Optional<Sequence<DeclarationExpr>> getValues() {
    return Optional.ofNullable(values_);
  }

  @Override
  public boolean isRecordExpr() {
    return true;
  }

  @Override
  public RecordExpr asRecordExpr() {
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
          values_.set(i, (DeclarationExpr) replaceWith);
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
