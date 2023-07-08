package scope;

/**
 * Represents a symbol in an aggregated symbol.
 *
 * @author Ben
 */
public interface MemberSymbol extends Symbol {

  default boolean isAggregatedSymbol() {
    return false;
  }

  default AggregatedSymbol asAggregatedSymbol() {
    throw new IllegalStateException(String.format("%s is not a AggregatedSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  default boolean isVariableSymbol() {
    return false;
  }

  default VariableSymbol asVariableSymbol() {
    throw new IllegalStateException(String.format("%s is not a AggregatedSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  default boolean isMethodSymbol() {
    return false;
  }

  default MethodSymbol asMethodSymbol() {
    throw new IllegalStateException(String.format("%s is not a MethodSymbol, it is a %s", this, getClass().getSimpleName()));
  }
}
