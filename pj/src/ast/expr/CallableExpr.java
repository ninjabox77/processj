package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a mobile, a procedure, or a method call on an object or
 * a class (the latter is only used during code transformation). The
 * methodExpression represent the instance of the class that was used
 * to access the Java method. If it is null, then this indicates that
 * it was implicitly accessed using "this".
 *
 * @author Ben
 */
public class CallableExpr extends Expression<CallableExpr> {

  private Expression<?> methodExpression_;
  private Sequence<Expression<?>> arguments_;
  private boolean implicitThis_;
  private String identifier_;

  public CallableExpr() {
    this(null, null);
  }

  public CallableExpr(Expression<?> methodExpression, String identifier) {
    this(methodExpression, identifier, (Expression<?>) null);
  }

  public CallableExpr(Expression<?> methodExpression, String identifier, Expression<?>... arguments) {
    this(null, methodExpression, identifier, Sequence.sequenceList(arguments));
  }

  public CallableExpr(Token token, Expression<?> methodExpression, String identifier, Sequence<Expression<?>> arguments) {
    super(token);
    setMethodExpression(methodExpression);
    setIdentifier(identifier);
    setArguments(arguments);
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

  public Optional<Expression<?>> getMethodExpression() {
    return Optional.ofNullable(methodExpression_);
  }

  public CallableExpr setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
  }

  public CallableExpr setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean isImplicitThis() {
    return implicitThis_ || getMethodExpression().isPresent();
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
    if (node == methodExpression_) {
      setMethodExpression(null);
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

  public boolean isReadCallable() {
    return false;
  }

  public ReadCallable asReadCallable() {
    throw new IllegalStateException(String.format("%s is not a ReadCallable, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isWriteCallable() {
    return false;
  }

  public WriteCallable asWriteCallable() {
    throw new IllegalStateException(String.format("%s is not a WriteCallable, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isSyncCallable() {
    return false;
  }

  public SyncCallable asSyncCallable() {
    throw new IllegalStateException(String.format("%s is not a SyncCallable, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTimeOutCallable() {
    return false;
  }

  public TimeOutCallable asTimeOutCallable() {
    throw new IllegalStateException(String.format("%s is not a TimeOutCallable, it is a %s", this, getClass().getSimpleName()));
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
