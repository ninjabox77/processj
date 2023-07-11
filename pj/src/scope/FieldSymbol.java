package scope;

/**
 * Represents a field.
 */
public class FieldSymbol extends VariableSymbol implements MemberSymbol {

  public FieldSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isFieldSymbol() {
    return true;
  }

  @Override
  public FieldSymbol asFieldSymbol() {
    return this;
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }
}
