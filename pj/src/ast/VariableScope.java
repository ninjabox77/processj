package ast;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Records declared and reference variables for a given scope. Note
 * that this does not extend any AST node.
 *
 * @author Ben
 */
public class VariableScope extends Node {

  private VariableScope parent_;
  // This could be literally any node in the AST.
  private Node nodeScope_;
  // Local variables declared within the scope.
  private Map<String, Variable<?>> declaredVariables_ = Collections.emptyMap();
  // Local reference variables declared within the scope.
  private Map<String, Variable<?>> referencedLocalVariables_ = Collections.emptyMap();
  // TODO: don't think this will be needed for PJ
  private Map<String, Variable<?>> referencedClassVariables_ = Collections.emptyMap();
  // TODO: add scopes for methods, procedures, and classes

  public VariableScope() {
    this(null);
  }

  public VariableScope(final VariableScope parent) {
    this(null, parent);
  }

  public VariableScope(Token token, final VariableScope parent) {
    super(token);
    setParentScope(parent);
  }

  public VariableScope setParentScope(final VariableScope parent) {
    if (parent == parent_) {
      return this;
    }
    if (parent_ != null) {
      parent_.setParentNode(null);
    }
    parent_ = parent;
    setAsParentNodeOf(parent);
    return this;
  }

  public VariableScope getParentScope() {
    return parent_;
  }

  public boolean isRoot() {
    return Objects.isNull(parent_);
  }

  public VariableScope setNodeScope(Node nodeScope) {
    if (nodeScope == nodeScope_) {
      return this;
    }
    setAsParentNodeOf(nodeScope);
    return this;
  }

  public Node getNodeScope() {
    return nodeScope_;
  }

  public Variable<?> getDeclaredVariable(final String name) {
    return declaredVariables_.get(name);
  }

  public Variable<?> getReferencedLocalVariable(final String name) {
    return referencedLocalVariables_.get(name);
  }

  public Variable<?> getReferencedClassVariable(final String name) {
    return referencedClassVariables_.get(name);
  }

  public boolean isReferencedLocalVariable(final String name) {
    return referencedLocalVariables_.containsKey(name);
  }

  public boolean isReferencedClassVariable(final String name) {
    return referencedClassVariables_.containsKey(name);
  }

  public Map<String, Variable<?>> getDeclaredVariables() {
    if (declaredVariables_ == Collections.EMPTY_MAP) {
      return declaredVariables_;
    }
    return Collections.unmodifiableMap(declaredVariables_);
  }

  public Map<String, Variable<?>> getReferencedClassVariables() {
    if (referencedClassVariables_ == Collections.EMPTY_MAP) {
      return referencedClassVariables_;
    }
    return Collections.unmodifiableMap(referencedClassVariables_);
  }

  public Iterator<Variable<?>> declaredVariablesIterator() {
    return declaredVariables_.values().iterator();
  }

  public Iterator<Variable<?>> referencedLocalVariablesIterator() {
    return referencedLocalVariables_.values().iterator();
  }

  public Iterator<Variable<?>> referencedClassVariablesIterator() {
    return referencedClassVariables_.values().iterator();
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
