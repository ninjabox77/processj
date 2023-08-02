package ast.java;

import ast.SourceAST;
import ast.Variable;
import ast.VariableDeclarator;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a declaration of a field in a record, a protocol as part of
 * its tag, or a class (which is only used during code transformation).
 *
 * @author Ben
 */
public class FieldDeclaration extends BodyDeclaration<FieldDeclaration> implements Variable<FieldDeclaration> {

  private VariableDeclarator variable_;

  private FieldDeclaration() {
    this(null);
  }

  public FieldDeclaration(VariableDeclarator variable) {
    this(null, variable);
  }

  public FieldDeclaration(Token token, VariableDeclarator variable) {
    super(token);
    setVariable(variable);
  }

  public FieldDeclaration setVariable(VariableDeclarator variable) {
    if (variable == variable_) {
      return this;
    }
    if (variable_ != null) {
      variable_.setParentNode(null);
    }
    variable_ = variable;
    setAsParentNodeOf(variable);
    return this;
  }

  public VariableDeclarator getVariable() {
    return variable_;
  }

  @Override
  public FieldDeclaration setModifiers(final int modifiers) {
    return super.setModifiers(modifiers);
  }

  @Override
  public FieldDeclaration setIdentifier(String name) {
    if (Objects.equals(name, variable_.getIdentifier())) {
      return this;
    }
    variable_.setIdentifier(name);
    return this;
  }

  @Override
  public String getIdentifier() {
    return variable_.getIdentifier();
  }

  @Override
  public boolean hasInitialExpression() {
    return variable_.getRightExpression() != null;
  }

  @Override
  public Expression<?> getInitialExpression() {
    return hasInitialExpression() ? variable_.getRightExpression() : null;
  }

  @Override
  public boolean isFieldDeclaration() {
    return true;
  }

  @Override
  public FieldDeclaration asFieldDeclaration() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == variable_) {
      setVariable((VariableDeclarator) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
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
