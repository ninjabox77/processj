package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a method call, or a method call on an object or class.
 *
 * @author Ben
 */
public class MethodCallExpr extends Expression<MethodCallExpr> {

  private Expression<?> objectExpression_;
  private Expression<?> methodExpression_;
  private Sequence<Expression<?>> arguments_;
  private Sequence<Type> typeArguments_;
  private boolean implicitThis_;

  public MethodCallExpr() {
    this(null, null, null);
  }

  public MethodCallExpr(Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments) {
    this(objectExpression, methodExpression, arguments, null);
  }

  public MethodCallExpr(Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    this(null, objectExpression, methodExpression, arguments, typeArguments);
  }

  public MethodCallExpr(Token token, Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    super(token);
    setObjectExpression(objectExpression);
    setMethodExpression(methodExpression);
    setArguments(arguments);
    setTypeArguments(typeArguments);
    setImplicitThis(false);
  }

  public MethodCallExpr setObjectExpression(Expression<?> objectExpression) {
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

  public MethodCallExpr setMethodExpression(Expression<?> methodExpression) {
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

  public MethodCallExpr setArguments(Sequence<Expression<?>> arguments) {
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

  public Optional<Sequence<Expression<?>>> getArguments() {
    return Optional.ofNullable(arguments_);
  }

  public MethodCallExpr setTypeArguments(Sequence<Type> typeArguments) {
    if (typeArguments == typeArguments_) {
      return this;
    }
    if (typeArguments_ != null) {
      typeArguments_.setParentNode(null);
    }
    typeArguments_ = typeArguments;
    setAsParentNodeOf(typeArguments);
    return this;
  }

  public Optional<Sequence<Type>> getTypeArguments() {
    return Optional.ofNullable(typeArguments_);
  }

  public MethodCallExpr setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean getImplicitThis() {
    return implicitThis_;
  }

  public boolean isConstructorCallExpr() {
    return false;
  }

  public ConstructorCallExpr asConstructorCallExpr() {
    throw new IllegalStateException(String.format("%s is not a ConstructorCallExpr, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isMethodCallExpr() {
    return true;
  }

  @Override
  public MethodCallExpr asMethodCallExpr() {
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
    if (typeArguments_ != null) {
      for (int i = 0; i < typeArguments_.size(); ++i) {
        if (node == typeArguments_.get(i)) {
          typeArguments_.set(i, (Type) replaceWith);
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
    if (typeArguments_ != null) {
      for (int i = 0; i < typeArguments_.size(); ++i) {
        if (node == typeArguments_.get(i)) {
          typeArguments_.remove(i);
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
