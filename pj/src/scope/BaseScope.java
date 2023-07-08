package scope;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for all scopes in ProcessJ.
 *
 * @author Ben
 */
public abstract class BaseScope implements Scope {

  // If this scope is the root of the scope tree then it will be null.
  protected Scope enclosingScope_;
  // Contains all variable symbols defined in this scope.
  protected Map<String, Symbol> variables_ = new LinkedHashMap<>();
  // Contains local scopes that are not symbol scopes.
  protected List<Scope> nestedScopesNotSymbols_ = new ArrayList<>();

  public BaseScope() {
  }

  public BaseScope(Scope enclosingScope) {
    setEnclosingScope(enclosingScope);
  }

  @Override
  public Scope getEnclosingScope() {
    return enclosingScope_;
  }

  @Override
  public void setEnclosingScope(Scope scope) {
    enclosingScope_ = scope;
  }

  @Override
  public void addSymbolInScope(Symbol variable) {
    if (variables_.containsKey(variable.getName())) {
      throw new IllegalArgumentException("Duplicated symbol " + variable.getName());
    }
    variable.setScope(this);
    variables_.put(variable.getName(), variable);
  }

  @Override
  public Symbol lookupSymbolInScope(final String name) {
    Symbol variable = variables_.get(name);
    if (variable != null) {
      return variable;
    }
    Scope parentScope = getEnclosingScope();
    if (parentScope != null) {
      return parentScope.lookupSymbolInScope(name);
    }
    return null;
  }

  @Override
  public Symbol getSymbol(final String name) {
    return variables_.get(name);
  }

  @Override
  public void addNestedLocalScope(Scope scope) {
    if (scope instanceof SymbolWithScope) {
      throw new IllegalArgumentException(String.format("%s not a symbol with scope", scope.getClass().getSimpleName()));
    }
    nestedScopesNotSymbols_.add(scope);
  }

  @Override
  public List<? extends Symbol> getSymbols() {
    return new ArrayList<>(variables_.values());
  }

  @Override
  public List<? extends Symbol> getAllSymbols() {
    List<Symbol> variables = new ArrayList<>(getSymbols());
    for (Symbol s : variables_.values()) {
      if (s instanceof final Scope scope) {
        variables.addAll(scope.getAllSymbols());
      }
    }
    return variables;
  }

  @Override
  public List<Scope> getNestedScopes() {
    List<Scope> scopes = new ArrayList<>();
    scopes.addAll(getNestedScopedSymbols());
    scopes.addAll(nestedScopesNotSymbols_);
    return scopes;
  }

  @Override
  public List<Scope> getNestedScopedSymbols() {
    return null;
  }

  @Override
  public List<String> getSymbolNames() {
    return new ArrayList<>(variables_.keySet());
  }

  @Override
  public int getNumberOfSymbolsInScope() {
    return variables_.size();
  }

  @Override
  public List<Scope> getEnclosingScopesToRoot() {
    return null;
  }
}
