package scope;

import java.util.List;

/**
 * A scope is a table of variables that are grouped together by some
 * lexical construct of ProcessJ; some examples are the body of procedures,
 * methods, records, and protocols, nameless blocks, parameters, etc. Note
 * that the parent scope is generally the enclosing scope, but when inheritance
 * is present, as in the case of records and protocols, the enclosing scope
 * is the parent of the record or protocol.
 */
public interface Scope {

  // The name of the scope that encloses symbols. For nameless blocks,
  // the name returned by the function is local.
  default String getName() {
    return "local";
  }

  // The scope in which this scope is defined. For example, if this
  // scope is a procedure, the enclosing scope is the file in which
  // the procedure is defined.
  Scope getEnclosingScope();

  // The scope that wraps this scope.
  void setEnclosingScope(Scope scope);

  // Adds symbols to this scope. Note that symbol names must be unique.
  void addSymbolInScope(Symbol variable);

  Symbol lookupSymbolInScope(final String name);

  Symbol getSymbol(final String name);

  // Adds a nested local scope to this scope.
  void addNestedLocalScope(Scope scope);

  // Return all symbols found in all nested scopes.
  List<? extends Symbol> getSymbols();

  // Returns all symbols found in all nested scopes.
  List<? extends Symbol> getAllSymbols();

  // Return a list of nested scopes within this scope.
  List<Scope> getNestedScopes();

  // Returns all enclosed scoped symbols.
  List<Scope> getNestedScopedSymbols();

  // Return a list of names associated with all symbols in
  // this scope.
  List<String> getSymbolNames();

  // Return the numbers of symbols in this scope.
  int getNumberOfSymbolsInScope();

  // Return a list of all scopes that are on the path to the root scope.
  List<Scope> getEnclosingScopesToRoot();
}
