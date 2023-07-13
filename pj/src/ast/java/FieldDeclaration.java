package ast.java;

import ast.Node;
import ast.VariableDecl;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a declaration of a field in a record, a protocol as part of
 * its tag, or a class (which is only used during code transformation).
 *
 * @author Ben
 */
public class FieldDeclaration extends BodyDeclaration<FieldDeclaration> {

  private VariableDecl variable_;

  private FieldDeclaration() {
    this(null);
  }

  public FieldDeclaration(VariableDecl variable) {
    this(null, variable);
  }

  public FieldDeclaration(Token token, VariableDecl variable) {
    super(token);
    setVariable(variable);
  }

  public FieldDeclaration setVariable(VariableDecl variable) {
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

  public VariableDecl getVariable() {
    return variable_;
  }

  @Override
  public FieldDeclaration setModifiers(final int modifiers) {
    return super.setModifiers(modifiers);
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
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == variable_) {
      setVariable((VariableDecl) replaceWith);
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
