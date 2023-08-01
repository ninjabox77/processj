package ast;

import java.util.Optional;

/**
 * Represents a root of a subtree, or a leaf node.
 *
 * @author Ben
 */
public interface HasParent<T> {

  default boolean isOrphan() {
    return getParentNode().isEmpty();
  }

  Optional<SourceAST> getParentNode();

  T setParentNode(SourceAST node);

  SourceAST getParentNodeForChildren();
}
