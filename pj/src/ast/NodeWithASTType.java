package ast;

import ast.types.ASTType;

/**
 * Represents a node that has an optional ASTType.
 *
 * @author Ben
 */
public interface NodeWithASTType<N extends SourceAST, T extends ASTType> {

  default T getASTType() {
    throw new IllegalStateException(String.format("%s cannot have an optional type, it is a %s", this, getClass().getSimpleName()));
  }

  default N setASTType(T type) {
    throw new IllegalStateException(String.format("%s cannot have an optional type, it is a %s", this, getClass().getSimpleName()));
  }
}
