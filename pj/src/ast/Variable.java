package ast;

import ast.expr.Expression;

/**
 * A node marked as a variable which are typically VariableExpression,
 * FieldDeclaration, Parameter, and DeclarationExpression.
 */
public interface Variable<N extends SourceAST> {

  /**
   * Sets the name of the variable.
   */
  N setIdentifier(final String name);

  /**
   * Returns the name of the variable.
   */
  String getIdentifier();

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
