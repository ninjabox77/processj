package scope;

/**
 * Represents a tag of a protocol variant.
 */
public class ProtocolTagSymbol extends AggregatedSymbol {

  public ProtocolTagSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isProtocolTagSymbol() {
    return true;
  }

  @Override
  public ProtocolTagSymbol asProtocolTagSymbol() {
    return this;
  }

  @Override
  public boolean isMethodSymbol() {
    return true;
  }
}
