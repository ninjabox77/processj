package ast.expr;

import ast.Node;
import ast.Variable;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a variable name. The type of this variable must be determined
 * if this expression is part of a declaration expression, in which case
 * it may or may not have an initial value. For example, if this expression
 * is part of a declaration expression, it will point to that type of
 * expression. Since a declaration may be initialized, we can grab its
 * initial default value from there. However, if the variable is a Field
 * or a Parameter, then the type of this expression should be resolved
 * during name resolution, and its initial default value should be obtained
 * from one of these two nodes. In short, the default value needs to be
 * looked up since we are not evaluating the values of variables during
 * run-time.
 *
 * @author Ben
 */
public class VariableExpr extends Expression<VariableExpr> implements Variable<VariableExpr> {

  private int modifiers_;
  private String name_;
  private Variable<?> accessVariable_;

  public VariableExpr() {
    this(null, null);
  }

  public VariableExpr(Type type, final String name) {
    this(null, type, name);
  }

  public VariableExpr(Token token, Type type, final String name) {
    super(token);
    setType(type);
    setName(name);
  }

  public VariableExpr setModifiers(final int modifiers) {
    if (modifiers == modifiers_) {
      return this;
    }
    modifiers_ = modifiers;
    return this;
  }

  public int getModifiers() {
    return modifiers_;
  }

  public VariableExpr setAccessVariable(Variable<?> accessVariable) {
    if (accessVariable == accessVariable_) {
      return this;
    }
    accessVariable_ = accessVariable;
    return this;
  }

  public Optional<Variable<?>> getAccessVariable() {
    return Optional.ofNullable(accessVariable_);
  }

  @Override
  public VariableExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public VariableExpr setName(String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public boolean hasInitialExpression() {
    return false;
  }

  @Override
  public Expression<?> getInitialExpression() {
    return null;
  }

  @Override
  public boolean isVariableExpr() {
    return true;
  }

  @Override
  public VariableExpr asVariableExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == accessVariable_) {
      setAccessVariable((Variable<?>) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }
}
