import control.CompilationUnit;
import control.CompilerConfiguration;

import java.io.File;

/**
 * A convenience front end for getting standard compilations done.
 *
 * @author Ben
 */
public final class Processor {
  private CompilerConfiguration configuration_;

  public Processor() {
    configuration_ = null;
  }

  public Processor(CompilerConfiguration configuration) {
    configuration_ = configuration;
  }

  public void compile(File file) {
    CompilationUnit unit = new CompilationUnit();
    unit.addSource(file);
    unit.compile();
  }

  public void compile(File[] files) {
    CompilationUnit unit = new CompilationUnit();
    unit.addSources(files);
    unit.compile();
  }

  public void compile(String[] files) {
    CompilationUnit unit = new CompilationUnit();
    unit.addSources(files);
    unit.compile();
  }
}
