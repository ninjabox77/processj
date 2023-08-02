package scope;

import ast.Variable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VariableScope {

  private Map<String, Variable<?>> declaredVariables_ = Collections.emptyMap();
  private Map<String, Variable<?>> referencedLocalVariables_ = Collections.emptyMap();
  private VariableScope parentScope_;

  public VariableScope() {
  }

  private VariableScope(VariableScope parentScope) {
    parentScope_ = parentScope;
  }

  public void setParentScope(VariableScope parentScope) {
    parentScope_ = parentScope;
  }

  public VariableScope getParentScope() {
    return parentScope_;
  }

  public boolean isRoot() {
    return parentScope_ == null;
  }

  public void putDeclaredVariable(Variable<?> variable) {
    if (declaredVariables_ == Collections.EMPTY_MAP) {
      declaredVariables_ = new HashMap<>();
    }
    declaredVariables_.put(variable.getIdentifier(), variable);
  }

  public Variable<?> getDeclaredVariable(final String name) {
    return declaredVariables_.get(name);
  }

  public boolean isDeclaredVariable(final String name) {
    if (declaredVariables_ == Collections.EMPTY_MAP) {
      return false;
    }
    return declaredVariables_.containsKey(name);
  }

  public Variable<?> removeDeclaredVariable(final String name) {
    if (declaredVariables_ == Collections.EMPTY_MAP) {
      return null;
    }
    return declaredVariables_.remove(name);
  }

  public void putReferencedLocalVariable(Variable<?> variable) {
    if (referencedLocalVariables_ == Collections.EMPTY_MAP) {
      referencedLocalVariables_ = new HashMap<>();
    }
    referencedLocalVariables_.put(variable.getIdentifier(), variable);
  }

  public Variable<?> getReferencedVariable(final String name) {
    return referencedLocalVariables_.get(name);
  }

  public boolean isReferencedLocalVariable(final String name) {
    if (referencedLocalVariables_ == Collections.EMPTY_MAP) {
      return false;
    }
    return referencedLocalVariables_.containsKey(name);
  }

  public Variable<?> removeReferencedLocalVariable(final String name) {
    if (referencedLocalVariables_ == Collections.EMPTY_MAP) {
      return null;
    }
    return referencedLocalVariables_.remove(name);
  }

  public Iterator<Variable<?>> getDeclaredVariableIterator() {
    return declaredVariables_.values().iterator();
  }

  public Iterator<Variable<?>> getReferencedVariableIterator() {
    return referencedLocalVariables_.values().iterator();
  }
}
