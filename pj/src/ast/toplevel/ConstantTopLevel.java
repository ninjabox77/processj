package ast.toplevel;

import ast.Node;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

import java.util.Optional;

/**
 * Represents a variable declaration.
 *
 * @author Ben
 */
public class ConstantTopLevel extends TopLevelDeclaration<ConstantTopLevel> {

  private Expression<?> rightExpression_;

  public ConstantTopLevel() {
    this(null, null);
  }

  public ConstantTopLevel(Type type, String name) {
    this(ACC_PUBLIC, type, name);
  }

  public ConstantTopLevel(int modifier, Type type, String name) {
    this(modifier, type, name, null);
  }

  public ConstantTopLevel(int modifier, Type type, String name, Expression<?> rightExpression) {
    this(null, modifier, type, name, rightExpression);
  }

  public ConstantTopLevel(Token token, int modifier, Type type, String name, Expression<?> rightExpression) {
    super(token, modifier, type, name, null);
    setRightExpression(rightExpression);
  }

  public ConstantTopLevel setRightExpression(Expression<?> rightExpression) {
    if (rightExpression == rightExpression_) {
      return this;
    }
    if (rightExpression_ != null) {
      rightExpression_.setParentNode(null);
    }
    rightExpression_ = rightExpression;
    setAsParentNodeOf(rightExpression);
    return this;
  }

  public Optional<Expression<?>> getRightExpression() {
    return Optional.ofNullable(rightExpression_);
  }

  @Override
  public boolean isConstantTopLevel() {
    return true;
  }

  @Override
  public ConstantTopLevel asConstantTopLevel() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == rightExpression_) {
      setRightExpression((Expression<?>) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }
}