package scope;

/**
 * Base class for all symbols with scope.
 *
 * @author Ben
 */
public abstract class SymbolWithScope extends BaseScope implements Symbol, Scope {

  protected String name_;

  public SymbolWithScope(String name) {
    name_ = name;
  }

  @Override
  public Scope getScope() {
    return enclosingScope_;
  }

  @Override
  public void setScope(Scope scope) {
    setEnclosingScope(scope);
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public int getNumberOfSymbolsInScope() {
    return variables_.size();
  }
}
