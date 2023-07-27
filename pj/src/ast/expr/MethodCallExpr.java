package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a method call, or a method call on an object or class.
 * This class should only be when specifying generic arguments in a
 * method call.
 *
 * @author Ben
 */
public class MethodCallExpr extends Expression<MethodCallExpr> {

  private Expression<?> methodExpression_;
  private Sequence<Expression<?>> arguments_;
  private Sequence<Type> typeArguments_;
  private String identifier_;
  private boolean implicitThis_;

  public MethodCallExpr() {
    this(null);
  }

  public MethodCallExpr(final String identifier) {
    this(identifier, Sequence.sequenceList());
  }

  public MethodCallExpr(final String identifier, Sequence<Expression<?>> arguments) {
    this(null, identifier, arguments);
  }

  public MethodCallExpr(Expression<?> methodExpression, final String identifier, Sequence<Expression<?>> arguments) {
    this(methodExpression, identifier, arguments, Sequence.sequenceList());
  }

  public MethodCallExpr(Expression<?> methodExpression, final String identifier, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    this(null, methodExpression, identifier, arguments, typeArguments);
  }

  public MethodCallExpr(Token token, Expression<?> methodExpression, final String identifier, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    super(token);
    setMethodExpression(methodExpression);
    setIdentifier(identifier);
    setArguments(arguments);
    setTypeArguments(typeArguments);
    setImplicitThis(false);
  }

  public MethodCallExpr setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
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

  public boolean isObjectCreationExpr() {
    return false;
  }

  public ObjectCreationExpr asObjectCreationExpr() {
    throw new IllegalStateException(String.format("%s is not an ObjectCreationExpr, it is a %s", this, getClass().getSimpleName()));
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
    return false;
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
    if (typeArguments_ != null) {
      for (int i = 0; i < typeArguments_.size(); ++i) {
        if (node == typeArguments_.get(i)) {
          typeArguments_.remove(i);
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
