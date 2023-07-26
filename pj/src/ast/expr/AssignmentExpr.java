package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;

public class AssignmentExpr extends BinaryExpr {

  public AssignmentExpr() {
    this(null, null, null);
  }

  public AssignmentExpr(Expression<?> leftExpression, Expression<?> rightExpression, Token operation) {
    super(leftExpression, rightExpression, operation);
  }

  public AssignmentExpr(Token token, Expression<?> leftExpression, Expression<?> rightExpression, Token operation) {
    super(token, leftExpression, rightExpression, operation);
  }

  @Override
  public BinaryExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }
}
