package control;

import ast.CompileUnit;

/**
 * An interface used to execute different types of operations during
 * compilation.
 *
 * @author Ben
 */
public interface PhaseOperation {

  void doPhaseOperation(CompileUnit unit);
}
