package scope;

/**
 * Represents a protocol variant.
 */
public class ProtocolSymbol extends AggregatedSymbol implements MemberSymbol {

  public ProtocolSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isProtocolSymbol() {
    return true;
  }

  @Override
  public ProtocolSymbol asProtocolSymbol() {
    return this;
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }
}
