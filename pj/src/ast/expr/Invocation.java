package ast.expr;

import ast.SourceAST;
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
public class Invocation extends Expression<Invocation> {

  protected Expression<?> scope_;
  protected Expression<?> arguments_;
  protected boolean implicitThis_;
  protected String identifier_;

  public Invocation() {
    this(null, null);
  }

  public Invocation(Expression<?> scope, String identifier) {
    this(scope, identifier, new TupleExpression());
  }

  public Invocation(Expression<?> methodExpression, String identifier, Expression<?> arguments) {
    this(null, methodExpression, identifier, arguments);
  }

  public Invocation(Token token, Expression<?> scope, String identifier, Expression<?> arguments) {
    super(token);
    setScope(scope);
    setIdentifier(identifier);
    setArguments(arguments);
  }

  public Invocation setScope(Expression<?> scope) {
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

  public Invocation setIdentifier(final String identifier) {
    if (Objects.equals(identifier, identifier_)) {
      return this;
    }
    identifier_ = identifier;
    return this;
  }

  public String getIdentifier() {
    return identifier_;
  }

  public Invocation setImplicitThis(final boolean implicitThis) {
    if (implicitThis == implicitThis_) {
      return this;
    }
    implicitThis_ = implicitThis;
    return this;
  }

  public boolean isImplicitThis() {
    return implicitThis_ || getScope().isPresent();
  }

  public Invocation setArguments(Expression<?> arguments) {
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

  @Override
  public boolean isInvocation() {
    return true;
  }

  @Override
  public Invocation asInvocation() {
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
    if (arguments_ != null) {
      if (arguments_.isListExpression()) {
        ListExpression<?> tuple = arguments_.asListExpression();
        return tuple.replace(node, replaceWith);
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
    if (arguments_ != null) {
      if (arguments_.isListExpression()) {
        ListExpression<?> tuple = arguments_.asListExpression();
        return tuple.remove(node);
      }
    }
    return false;
  }

  public boolean isChannelReadExpression() {
    return false;
  }

  public ChannelReadExpression asChannelReadExpression() {
    throw new IllegalStateException(String.format("%s is not a ChannelReadExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelWriteExpression() {
    return false;
  }

  public ChannelWriteExpression asChannelWriteExpression() {
    throw new IllegalStateException(String.format("%s is not a ChannelWriteExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isSyncReadExpression() {
    return false;
  }

  public SyncReadExpression asSyncReadExpression() {
    throw new IllegalStateException(String.format("%s is not a SyncReadExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTimeoutReadExpression() {
    return false;
  }

  public TimeoutReadExpression asTimeoutReadExpression() {
    throw new IllegalStateException(String.format("%s is not a TimeoutReadExpression, it is a %s", this, getClass().getSimpleName()));
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
