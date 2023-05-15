package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;
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
public class DeclarationExpr extends BinaryExpr {

  public DeclarationExpr(Expression<?> leftExpression, Token operation, Expression<?> rightExpression) {
    this(null, leftExpression, operation, rightExpression);
  }

  public DeclarationExpr(Token token, Expression<?> leftExpression, Token operation, Expression<?> rightExpression) {
    super(token, leftExpression, operation, rightExpression);
  }

  @Override
  public void customInitialization() {
    final Expression<?> left = getLeftExpression();
    if (!left.isVariableExpr()) {
      throw new RuntimeException("");
    }
  }

  @Override
  public DeclarationExpr setType(Type type) {
    return (DeclarationExpr) super.setType(type);
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
