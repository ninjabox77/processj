package scope;

/**
 * Represents a symbol that has a name and a scope in which in it lives.
 */
public interface Symbol {

  // The name of the variable.
  String getName();

  // Return the scope in which the variable lives.
  Scope getScope();

  // The scope in which the variable lives.
  void setScope(Scope scope);

  // Return true if this symbol represents an aggregated symbol.
  default boolean isType() {
    return false;
  }

  default boolean isMemberSymbol() {
    return false;
  }

  default MemberSymbol asMemberSymbol() {
    throw new IllegalStateException(String.format("%s is not a MemberSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  default boolean isVariableSymbol() {
    return false;
  }

  default VariableSymbol asVariableSymbol() {
    throw new IllegalStateException(String.format("%s is not a VariableSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  default boolean isAggregatedSymbol() {
    return false;
  }

  default AggregatedSymbol asAggregatedSymbol() {
    throw new IllegalStateException(String.format("%s is not a AggregatedSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  default boolean isFunctionSymbol() {
    return false;
  }

  default FunctionSymbol asFunctionSymbol() {
    throw new IllegalStateException(String.format("%s is not a FunctionSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  // TODO: override equals and hash
}
