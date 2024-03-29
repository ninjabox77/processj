package ast.expr;

import ast.SourceAST;
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
public class MethodCallExpression extends Expression<MethodCallExpression> {

  private Expression<?> scope_;
  private Expression<?> arguments_;
  private Sequence<Type> typeArguments_;
  private String identifier_;
  private boolean implicitThis_;

  public MethodCallExpression() {
    this(null);
  }

  public MethodCallExpression(final String identifier) {
    this(identifier, new TupleExpression());
  }

  public MethodCallExpression(final String identifier, Expression<?> arguments) {
    this(null, identifier, arguments);
  }

  public MethodCallExpression(Expression<?> scope, final String identifier, Expression<?> arguments) {
    this(scope, identifier, arguments, Sequence.sequenceList());
  }

  public MethodCallExpression(Expression<?> scope, final String identifier, Expression<?> arguments, Sequence<Type> typeArguments) {
    this(null, scope, identifier, arguments, typeArguments);
  }

  public MethodCallExpression(Token token, Expression<?> scope, final String identifier, Expression<?> arguments, Sequence<Type> typeArguments) {
    super(token);
    setScope(scope);
    setIdentifier(identifier);
    setArguments(arguments);
    setTypeArguments(typeArguments);
    setImplicitThis(false);
  }

  public MethodCallExpression setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
  }

  public MethodCallExpression setScope(Expression<?> scope) {
    if (scope == scope_) {
      return this;
    }
    if (scope_ != null) {
      scope_.setParentNode(null);
    }
    scope_ = scope;
    setAsParentNodeOf(scope);
    return this;
  }

  public Optional<Expression<?>> getScope() {
    return Optional.ofNullable(scope_);
  }

  public MethodCallExpression setArguments(Expression<?> arguments) {
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

  public Expression<?> getArguments() {
    return arguments_;
  }

  public MethodCallExpression setTypeArguments(Sequence<Type> typeArguments) {
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

  public MethodCallExpression setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean isImplicitThis() {
    return implicitThis_;
  }

  public boolean isObjectCreationExpr() {
    return false;
  }

  public ObjectCreationExpr asObjectCreationExpr() {
    throw new IllegalStateException(String.format("%s is not an ObjectCreationExpr, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isMethodCallExpression() {
    return true;
  }

  @Override
  public MethodCallExpression asMethodCallExpression() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == scope_) {
      setScope((Expression<?>) replaceWith);
      return true;
    }
    if (arguments_.isListExpression()) {
      return arguments_.asListExpression().replace(node, replaceWith);
    }
    for (int i = 0; i < typeArguments_.size(); ++i) {
      if (node == typeArguments_.get(i)) {
        typeArguments_.set(i, (Type) replaceWith);
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
    if (node == scope_) {
      setScope(null);
      return true;
    }
    if (arguments_.isListExpression()) {
      return arguments_.asListExpression().remove(node);
    }
    for (int i = 0; i < typeArguments_.size(); ++i) {
      if (node == typeArguments_.get(i)) {
        typeArguments_.remove(i);
        return true;
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
