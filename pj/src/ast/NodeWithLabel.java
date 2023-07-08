package ast;

import java.util.Optional;

/**
 * Represents a node that has an optional label.
 *
 * @author Ben
 */
public interface NodeWithLabel<N extends Node> {

  Optional<String> getLabel();

  N setLabel(final String label);

  N removeLabel();
}
