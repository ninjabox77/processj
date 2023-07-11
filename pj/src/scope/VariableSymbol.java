package scope;

import ast.types.ASTType;
import typesystem.Type;

/**
 * Represents a variable name.
 */
public class VariableSymbol extends BaseSymbol implements MemberSymbol {

  public VariableSymbol(String name) {
    super(name);
  }

  @Override
  public void setType(ASTType type) {
    super.setType(type);
  }

  @Override
  public boolean isVariableSymbol() {
    return true;
  }

  @Override
  public boolean isMemberSymbol() {
    return isFieldSymbol();
  }

  @Override
  public VariableSymbol asVariableSymbol() {
    return this;
  }

  public boolean isParameterSymbol() {
    return false;
  }

  public ParameterSymbol asParameterSymbol() {
    throw new IllegalStateException(String.format("%s is not a ParameterSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isFieldSymbol() {
    return false;
  }

  public FieldSymbol asFieldSymbol() {
    throw new IllegalStateException(String.format("%s is not a FieldSymbol, it is a %s", this, getClass().getSimpleName()));
  }
}
