package ast;

import java.util.Optional;

/**
 * Represents a root of a subtree, or a leaf node.
 *
 * @author Ben
 */
public interface NodeWithParent<T> {

  default boolean isOrphan() {
    return getParentNode().isEmpty();
  }

  Optional<Node> getParentNode();

  T setParentNode(Node node);

  Node getParentNodeForChildren();
}
