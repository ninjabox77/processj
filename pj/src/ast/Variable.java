package ast;

import ast.expr.Expression;

/**
 * A node marked as a variable which are typically VariableExpression,
 * Field, and Parameter.
 */
public interface Variable<N extends Node> {

  /**
   * Sets the name of the variable.
   */
  N setName(final String name);

  /**
   * Returns the name of the variable.
   */
  String getName();

  /**
   * Returns true if the variable has an initial value.
   */
  default boolean hasInitialExpression() {
    return false;
  }

  /**
   * Returns the expression used to initialize the variable or null if
   * there is no initialization.
   */
  default Expression<?> getInitialExpression() {
    throw new IllegalStateException(String.format("%s does not have an initial value, it is a %s", this, getClass().getSimpleName()));
  }
}
