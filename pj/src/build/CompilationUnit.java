package buildin;

import ast.CompileUnit;

import java.util.Map;
import java.util.Queue;

/**
 * The compilation unit collects all compilation data as it is generated
 * by the compiler system. We use an instance of this class to add additional
 * resources to the compile unit, and force the compilation to be run
 * again if needed.
 *
 * @author Ben
 */
public class CompilationUnit extends ProcessingUnit {

  private CompileUnit ast;
  private Map<String, CompileUnit> otherSources_;
  private Queue<CompileUnit> queueSources_;
}
