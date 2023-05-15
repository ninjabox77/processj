package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a protocol expression that contains a tag and a list
 * of declarations.
 *
 * @author Ben
 */
public class ProtocolExpr extends Expression<ProtocolExpr> {

  private Expression<?> tagExpression_;
  private Sequence<DeclarationExpr> values_;

  public ProtocolExpr() {
    this(null, null);
  }

  public ProtocolExpr(Expression<?> tagExpression, Sequence<DeclarationExpr> values) {
    this(null, tagExpression, values);
  }

  public ProtocolExpr(Token token, Expression<?> tagExpression, Sequence<DeclarationExpr> values) {
    super(token);
    setTagExpression(tagExpression);
    setValues(values);
  }

  public ProtocolExpr setTagExpression(Expression<?> tagExpression) {
    if (tagExpression == tagExpression_) {
      return this;
    }
    if (tagExpression_ != null) {
      tagExpression_.setParentNode(null);
    }
    tagExpression_ = tagExpression;
    setAsParentNodeOf(tagExpression);
    return this;
  }

  public Expression<?> getTagExpression() {
    return tagExpression_;
  }

  public ProtocolExpr setValues(Sequence<DeclarationExpr> values) {
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
  public boolean isProtocolExpr() {
    return true;
  }

  @Override
  public ProtocolExpr asProtocolExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == tagExpression_) {
      setTagExpression((Expression<?>) replaceWith);
      return true;
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
    if (node == tagExpression_) {
      setTagExpression(null);
      return true;
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
