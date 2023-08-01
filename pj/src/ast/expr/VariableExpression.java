package ast.expr;

import ast.SourceAST;
import ast.Variable;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
public class VariableExpression extends Expression<VariableExpression> implements Variable<VariableExpression> {

  private String name_;
  private Variable<?> accessVariable_;

  public VariableExpression() {
    this(null, null);
  }

  public VariableExpression(NodeType type, final String name) {
    this(null, type, name);
  }

  public VariableExpression(Token token, NodeType type, final String name) {
    super(token);
    setNodeType(type);
    setName(name);
  }

  public VariableExpression setAccessVariable(Variable<?> accessVariable) {
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
  public VariableExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public VariableExpression setName(String name) {
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
  public boolean isVariableExpression() {
    return true;
  }

  @Override
  public VariableExpression asVariableExpression() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == accessVariable_) {
      setAccessVariable((Variable<?>) replaceWith);
      return true;
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
