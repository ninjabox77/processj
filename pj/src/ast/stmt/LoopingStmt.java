package ast.stmt;

import java.util.Optional;

/**
 * Provides some sort of looping mechanism in the form of a block.
 *
 * @author Ben
 */
public interface LoopingStmt<T extends LoopingStmt<?>> {

  Optional<Statement> getLoopBlock();

  T setLoopBlock(Statement loopBlock);
}
