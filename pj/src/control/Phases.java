package control;

/**
 * Phases of the ProcessJ compiler.
 *
 * @author Ben
 */
public final class Phases {

  private Phases() { }

  // Opening of files.
  public static final int INITIALIZATION = 1;
  // AST building.
  //  * lexical analysis
  //  * syntax analysis
  //  * ast construction
  public static final int PARSING = 2;
  // Symbol table and scope.
  //  * resolve imports and packages
  //  * resolve scopes
  public static final int SYMBOL_SCOPE = 3;
  // Control flow graph.
  public static final int FLOW_GRAPH = 4;
  // Semantic analysis.
  //  * reachability check
  //  * parallel usage check
  //  * alt return check
  //  * label for break and continue check
  //  * literal check
  //  * replicated alt check
  public static final int SEMANTICS = 5;
  // Name checker and resolution
  //  * resolve native imports
  public static final int NAME_CHECKER = 6;
  // AST node rewriting.
  //  * alt rewrite
  //  * array rewrite
  //  * cast rewrite
  //  * channel read rewrite
  //  * par-block rewrite
  //  * par-for rewrite
  //  * record and protocol rewrite
  //  * statement rewrite
  //  * switch statement rewrite
  //  * loop rewrite
  //  * yield rewrite
  public static final int REWRITES = 7;
  // Type checker.
  public static final int TYPE_CHECKER = 8;
  // Class generation.
  //  * generate java file
  public static final int CLASS_GENERATION = 9;
  // Byte code rewrite.
  //  * asm instrumentation
  public static final int INSTRUMENTATION = 10;
  // Output of class.
  public static final int OUTPUT = 11;

  public static final int ALL = 11;

  public static final String[] info = {
      "startup",
      "initialization",
      "parsing",
      "table and scope construction",
      "control flow graph",
      "semantic analysis",
      "name checker analysis",
      "rewrites",
      "type checker analysis",
      "class generation",
      "instrumentation",
      "output"
  };

  public static String getInformation(final int phase) {
    return info[phase];
  }
}
