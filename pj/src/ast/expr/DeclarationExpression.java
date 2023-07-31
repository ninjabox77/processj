package ast.expr;

import ast.VariableDeclarator;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
public class DeclarationExpression extends Expression<DeclarationExpression> {

  private VariableDeclarator declaration_;

  public DeclarationExpression() {
    this(null);
  }

  public DeclarationExpression(VariableDeclarator declaration) {
    this(null, declaration);
  }

  public DeclarationExpression(Token token, VariableDeclarator declaration) {
    super(token);
    setDeclaration(declaration);
  }

  @Override
  public void customInitialization() {
    if (declaration_ != null) {
      setASTType(declaration_.getASTType());
    }
  }

  public DeclarationExpression setDeclaration(VariableDeclarator declaration) {
    if (declaration == declaration_) {
      return this;
    }
    if (declaration_ != null) {
      declaration_.setParentNode(null);
    }
    declaration_ = declaration;
    setAsParentNodeOf(declaration);
    return this;
  }

  public VariableDeclarator getDeclaration() {
    return declaration_;
  }

  @Override
  public DeclarationExpression setASTType(ASTType type) {
    return super.setASTType(type);
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
