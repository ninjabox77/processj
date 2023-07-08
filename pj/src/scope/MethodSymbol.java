package scope;

/**
 * Represents a method symbol.
 *
 * @author Ben
 */
public class MethodSymbol extends FunctionSymbol implements MemberSymbol {

  public MethodSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }
}
