package control;

import ast.Compilation;

/**
 * An interface used to execute different types of operations during
 * compilation.
 *
 * @author Ben
 */
public interface PhaseOperation {

  void doPhaseOperation(Compilation unit);
}
