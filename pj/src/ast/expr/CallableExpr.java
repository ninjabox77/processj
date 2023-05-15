package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a mobile, a procedure, or a method call on an object
 * or a class (the latter is only used during code transformation).
 *
 * @author Ben
 */
public class CallableExpr extends Expression<CallableExpr> {

  private Expression<?> objectExpression_;
  private Expression<?> methodExpression_;
  private Sequence<Expression<?>> arguments_;

  public CallableExpr() {
    this(null, new VariableExpr(), null);
  }

  public CallableExpr(Expression<?> objectExpression, Expression<?> methodExpression, Expression<?>... arguments) {
    this(null, objectExpression, methodExpression, Sequence.sequenceList(arguments));
  }

  public CallableExpr(Token token, Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    super(token);
    setObjectExpression(objectExpression);
    setMethodExpression(methodExpression);
    setArguments(arguments);
  }

  public CallableExpr setObjectExpression(Expression<?> objectExpression) {
    if (objectExpression == objectExpression_) {
      return this;
    }
    if (objectExpression_ != null) {
      objectExpression_.setParentNode(null);
    }
    objectExpression_ = objectExpression;
    setAsParentNodeOf(objectExpression);
    return this;
  }

  public Optional<Expression<?>> getObjectExpression() {
    return Optional.ofNullable(objectExpression_);
  }

  public CallableExpr setMethodExpression(Expression<?> methodExpression) {
    if (methodExpression == methodExpression_) {
      return this;
    }
    if (methodExpression_ != null) {
      methodExpression_.setParentNode(null);
    }
    methodExpression_ = methodExpression;
    setAsParentNodeOf(methodExpression);
    return this;
  }

  public Expression<?> getMethodExpression() {
    return methodExpression_;
  }

  public CallableExpr setArguments(Sequence<Expression<?>> arguments) {
    if (arguments == arguments_) {
      return this;
    }
    if (arguments_ != null) {
      arguments_.setParentNode(null);
    }
    arguments_ = arguments;
    setAsParentNodeOf(arguments);
    return this;
  }

  public Sequence<Expression<?>> getArguments() {
    return arguments_;
  }

  @Override
  public boolean isCallableExpr() {
    return true;
  }

  @Override
  public CallableExpr asCallableExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == objectExpression_) {
      setObjectExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == methodExpression_) {
      setMethodExpression((Expression<?>) replaceWith);
      return true;
    }
    if (arguments_ != null) {
      for (int i = 0; i < arguments_.size(); ++i) {
        if (node == arguments_.get(i)) {
          arguments_.set(i, (Expression<?>) replaceWith);
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
    if (node == objectExpression_) {
      setObjectExpression(null);
      return true;
    }
    if (arguments_ != null) {
      for (int i = 0; i < arguments_.size(); ++i) {
        if (node == arguments_.get(i)) {
          arguments_.remove(i);
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
