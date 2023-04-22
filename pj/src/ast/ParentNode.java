package ast;

import java.util.Optional;

/**
 * Represents a root of a subtree, or a leaf node.
 *
 * @author Ben
 */
public interface ParentNode<T> {

  default boolean isOrphan() {
    return getParentNode().isPresent();
  }

  Optional<Node> getParentNode();

  T setParentNode(Node node);

  Node getParentNodeForChildren();
}
