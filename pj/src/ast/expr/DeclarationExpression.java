package ast.expr;

import ast.Variable;
import ast.VariableDeclarator;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents one or more local variables. Normally, this is a single
 * local variable declared with an expression like "int a", but the
 * multiple assignment feature allows us two declare two or more variables
 * like "int a, b, c", and even initialize variables using an expression
 * like "int a = 1, b = 2, c = 3". In this last case, each assignment
 * represents a declaration expression.
 *
 * @author Ben
 */
public class DeclarationExpression extends Expression<DeclarationExpression> implements Variable<DeclarationExpression> {

  private VariableDeclarator variable_;

  public DeclarationExpression() {
    this(null);
  }

  public DeclarationExpression(VariableDeclarator variable) {
    this(null, variable);
  }

  public DeclarationExpression(Token token, VariableDeclarator variable) {
    super(token);
    setVariable(variable);
  }

  @Override
  public void customInitialization() {
    if (variable_ != null) {
      setNodeType(variable_.getNodeType());
    }
  }

  public DeclarationExpression setVariable(VariableDeclarator declaration) {
    if (declaration == variable_) {
      return this;
    }
    if (variable_ != null) {
      variable_.setParentNode(null);
    }
    variable_ = declaration;
    setAsParentNodeOf(declaration);
    return this;
  }

  public VariableDeclarator getVariable() {
    return variable_;
  }


  @Override
  public DeclarationExpression setName(String name) {
    if (!Objects.equals(variable_.getName(), name)) {
      variable_.setName(name);
    }
    return this;
  }

  @Override
  public String getName() {
    return variable_.getName();
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
  public DeclarationExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
  }

  @Override
  public boolean isDeclarationExpression() {
    return true;
  }

  @Override
  public DeclarationExpression asDeclarationExpression() {
    return this;
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
