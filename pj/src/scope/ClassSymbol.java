package scope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent a class for the re-writers to use.
 *
 * @author Ben
 */
public class ClassSymbol extends AggregatedSymbol {

  protected String superClassName_;

  public ClassSymbol(String name) {
    super(name);
  }

  // Returns the class symbol associated with the super-class name.
  public ClassSymbol getSuerClassScope() {
    if (superClassName_ != null && getEnclosingScope() != null) {
      Symbol superClass = getEnclosingScope().lookupSymbolInScope(superClassName_);
      if (superClass instanceof ClassSymbol) {
        return (ClassSymbol) superClass;
      }
    }
    return null;
  }

  public List<ClassSymbol> getSuperClasScopes() {
    final ClassSymbol superClassScope = getSuerClassScope();
    if (superClassScope != null) {
      return Collections.singletonList(superClassScope);
    }
    return null;
  }

  public String getSuperClassName() {
    return superClassName_;
  }

  public MethodSymbol lookUpMethod(String name) {
    Symbol variable = lookUpMember(name);
    if (variable instanceof MethodSymbol) {
      return (MethodSymbol) variable;
    }
    return null;
  }

  @Override
  public Symbol lookupSymbolInScope(String name) {
    Symbol variable = lookUpMember(name);
    if (variable != null) {
      return variable;
    }
    final Scope parentScope = getEnclosingScope();
    if (parentScope != null) {
      return parentScope.lookupSymbolInScope(name);
    }
    return null;
  }

  @Override
  public Symbol lookUpMember(String name) {
    Symbol variable = variables_.get(name);
    if (variable.isMemberSymbol()) {
      return variable;
    }
    List<ClassSymbol> superClassScopes = getSuperClasScopes();
    if (superClassScopes != null) {
      for (ClassSymbol s : superClassScopes) {
        variable = s.lookUpMember(name);
        if (variable.isMemberSymbol()) {
          return variable;
        }
      }
    }
    return null;
  }

  @Override
  public Symbol lookUpField(String name) {
    Symbol variable = lookUpMember(name);
    if (variable instanceof FieldSymbol) {
      return variable;
    }
    return null;
  }

  public void setSuperClassName(final String superClassName) {
    superClassName_ = superClassName;
  }

  public List<MethodSymbol> getDefinedMethods() {
    List<MethodSymbol> methods = new ArrayList<>();
    for (MemberSymbol m : getSymbols()) {
      if (m.isMethodSymbol()) {
        methods.add(m.asMethodSymbol());
      }
    }
    return methods;
  }

  public List<MethodSymbol> getMethods() {
    List<MethodSymbol> methods = new ArrayList<>();
    final ClassSymbol superClassScope = getSuerClassScope();
    if (superClassScope != null) {
      methods.addAll(superClassScope.getMethods());
    }
    methods.removeAll(getDefinedMethods());
    methods.addAll(getDefinedMethods());
    return methods;
  }

  @Override
  public List<? extends FieldSymbol> getFields() {
    List<FieldSymbol> fields = new ArrayList<>();
    final ClassSymbol superClassScope = getSuerClassScope();
    if (superClassScope != null) {
      fields.addAll(superClassScope.getFields());
    }
    fields.addAll(getDefinedFields());
    return fields;
  }

  public int getNumberOfDefinedMethods() {
    int count = 0;
    for (MemberSymbol m : getSymbols()) {
      if (m.isMethodSymbol()) {
        ++count;
      }
    }
    return count;
  }

  public int getNumberOfMethods() {
    int count = 0;
    final ClassSymbol superClassScope = getSuerClassScope();
    if (superClassScope != null) {
      count += superClassScope.getNumberOfMethods();
    }
    count += getNumberOfDefinedMethods();
    return count;
  }

  @Override
  public int getNumberOfFields() {
    int count = 0;
    final ClassSymbol superClassScope = getSuerClassScope();
    if (superClassScope != null) {
      count += superClassScope.getNumberOfFields();
    }
    count += getNumberOfDefinedFields();
    return count;
  }

  @Override
  public boolean isMemberSymbol() {
    return true;
  }
}
