package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;

public class AssignmentExpr extends BinaryExpr {

  public static final int EQ = 0;
  public static final int MULTEQ = 1;
  public static final int DIVEQ = 2;
  public static final int MODEQ = 3;
  public static final int PLUSEQ = 4;
  public static final int MINUSEQ = 5;
  public static final int LSHIFTEQ = 6;
  public static final int RSHIFTEQ = 7;
  public static final int RRSHIFTEQ = 8;
  public static final int ANDEQ = 9;
  public static final int OREQ = 10;
  public static final int XOREQ = 11;

  public static final String[] symbols = {"=", "*=", "/=", "%=", "+=", "-=",
      "<<=", ">>=", ">>>=", "&=", "|=", "^="};

  public AssignmentExpr() {
    this(null, null, EQ);
  }

  public AssignmentExpr(Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    super(leftExpression, rightExpression, operator);
  }

  public AssignmentExpr(Token token, Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    super(token, leftExpression, rightExpression, operator);
  }

  @Override
  public BinaryExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }
}
