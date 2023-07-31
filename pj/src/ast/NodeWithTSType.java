package ast;

import typesystem.Type;

/**
 * Represents a node with a type-system type in the AST.
 *
 * @author Ben
 */
public interface NodeWithTSType<N extends SourceAST, T extends Type> {

  @SuppressWarnings("unchecked")
  default T getTSType() {
    return (T) this;
  }

  default N setTSType(T type) {
    throw new IllegalStateException(String.format("%s is already a typesystem type", getClass().getSimpleName()));
  }
}
