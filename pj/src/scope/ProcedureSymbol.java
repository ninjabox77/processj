package scope;

/**
 * Represents a procedure.
 */
public class ProcedureSymbol extends FunctionSymbol {

  public ProcedureSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isProcedureSymbol() {
    return true;
  }

  @Override
  public ProcedureSymbol asProcedureSymbol() {
    return this;
  }
}
