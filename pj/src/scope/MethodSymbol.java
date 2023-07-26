package scope;

/**
 * Represents a method symbol.
 */
public class MethodSymbol extends FunctionSymbol implements MemberSymbol {

  public MethodSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }

  @Override
  public boolean isMethodSymbol() {
    return true;
  }

  @Override
  public MethodSymbol asMethodSymbol() {
    return this;
  }
}
