package scope;

/**
 * Represents a record symbol.
 */
public class RecordSymbol extends AggregatedSymbol implements MemberSymbol {

  public RecordSymbol(String name) {
    super(name);
  }

  @Override
  public boolean isRecordSymbol() {
    return true;
  }

  @Override
  public RecordSymbol asRecordSymbol() {
    return this;
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }
}
