package buildin;

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
  //  * semantic analysis
  //  * ast construction
  public static final int PARSING = 2;
  // Symbol table and scope.
  public static final int SYMBOL_SCOPE = 3;
  // Semantic analysis.
  //  * reachability check
  //  * parallel usage check
  //  * alt return check
  //  * label for break and continue check
  //  * literal check
  //  * replicated alt check
  public static final int SEMANTICS = 4;
  // Name checker and resolution
  //  * array type construction
  //  * resolve native imports
  //  * resolve imports and packages
  public static final int NAME_CHECKER = 5;
  // Type checker.
  public static final int TYPE_CHECKER = 6;
  // AST node rewriting.
  //  * alt rewrite
  //  * array rewrite
  //  * cast rewrite
  //  * channel read rewrite
  //  * par block rewrite
  //  * par for rewrite
  //  * record and protocol rewrite
  //  * statement rewrite
  //  * switch statement rewrite
  //  * loop rewrite
  //  * yield rewrite
  public static final int REWRITES = 7;
  // Class generation.
  //  * generate java file
  public static final int CLASS_GENERATION = 8;
  // Byte code rewrite.
  //  * asm instrumentation
  public static final int INSTRUMENTATION = 9;
  // Output of class.
  public static final int OUTPUT = 10;

  public static final String[] info = {
      "startup",
      "initialization",
      "parsing",
      "table and scope construction",
      "semantic analysis",
      "name checker analysis",
      "type checker analysis",
      "rewrites",
      "class generation",
      "instrumentation",
      "output"
  };

  public static String getInformation(final int phase) {
    return info[phase];
  }
}
