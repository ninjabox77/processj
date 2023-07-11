package scope;

/**
 * Represents a parameter.
 */
public class ParameterSymbol extends VariableSymbol {

  public ParameterSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isParameterSymbol() {
    return true;
  }

  @Override
  public ParameterSymbol asParameterSymbol() {
    return this;
  }
}
