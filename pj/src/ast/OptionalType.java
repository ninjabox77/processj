package ast;

import typesystem.Type;

/**
 * Represents a node with a type in the AST.
 *
 * @author Ben
 */
public interface OptionalType<N extends Node, T extends Type> {

  default T getType() {
    throw new IllegalStateException(String.format("%s cannot have an optional type, it is a %s", this, getClass().getSimpleName()));
  }

  default N setType(T type) {
    throw new IllegalStateException(String.format("%s cannot have an optional type, it is a %s", this, getClass().getSimpleName()));
  }
}
