package ast.expr;

import ast.VariableDecl;
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
public class DeclarationExpr extends Expression<DeclarationExpr> {

  private VariableDecl declaration_;

  public DeclarationExpr() {
    this(null);
  }

  public DeclarationExpr(VariableDecl declaration) {
    this(null, declaration);
  }

  public DeclarationExpr(Token token, VariableDecl declaration) {
    super(token);
  }

  @Override
  public void customInitialization() {
    if (declaration_ != null) {
      setASTType(declaration_.getASTType());
    }
  }

  public DeclarationExpr setDeclaration(VariableDecl declaration) {
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

  public VariableDecl getDeclaration() {
    return declaration_;
  }

  @Override
  public DeclarationExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean isDeclarationExpr() {
    return true;
  }

  @Override
  public DeclarationExpr asDeclarationExpr() {
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
