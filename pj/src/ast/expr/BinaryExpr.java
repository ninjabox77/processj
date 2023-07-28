package ast.expr;

import ast.Node;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents two expressions and an operation.
 *
 * @author Ben
 */
public class BinaryExpr extends Expression<BinaryExpr> {

  public static final int PLUS = 0;
  public static final int MINUS = 1;
  public static final int MULT = 2;
  public static final int DIV = 3;
  public static final int MOD = 4;
  public static final int LSHIFT = 5;
  public static final int RSHIFT = 6;
  public static final int RRSHIFT = 7;
  public static final int LT = 8;
  public static final int GT = 9;
  public static final int LTEQ = 10;
  public static final int GTEQ = 11;
  public static final int INSTANCEOF = 12;
  public static final int EQEQ = 13;
  public static final int NOTEQ = 14;
  public static final int AND = 15;
  public static final int OR = 16;
  public static final int XOR = 17;
  public static final int ANDAND = 18;
  public static final int OROR = 19;


  public static final String[] symbols = {"+", "-", "*", "/", "%", "<<", ">>", ">>>", "<", ">",
      "<=", ">=", "is", "==", "!=", "&", "|", "^", "&&", "||"};

  private Expression<?> leftExpression_;
  private Expression<?> rightExpression_;
  private int operator_;

  public BinaryExpr(Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    this(null, leftExpression, rightExpression, operator);
  }

  public BinaryExpr(Token token, Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    super(token);
    setLeftExpression(leftExpression);
    setRightExpression(rightExpression);
    setOperator(operator);
  }

  public BinaryExpr setLeftExpression(Expression<?> leftExpression) {
    if (leftExpression == leftExpression_) {
      return this;
    }
    if (leftExpression_ != null) {
      leftExpression_.setParentNode(null);
    }
    leftExpression_ = leftExpression;
    setAsParentNodeOf(leftExpression);
    return this;
  }

  public Expression<?> getLeftExpression() {
    return leftExpression_;
  }

  public BinaryExpr setRightExpression(Expression<?> rightExpression) {
    if (rightExpression == rightExpression_) {
      return this;
    }
    if (rightExpression_ != null) {
      rightExpression_.setParentNode(null);
    }
    rightExpression_ = rightExpression;
    setAsParentNodeOf(rightExpression);
    return this;
  }

  public Expression<?> getRightExpression() {
    return rightExpression_;
  }

  public BinaryExpr setOperator(final int operator) {
    if (operator == operator_) {
      return this;
    }
    operator_ = operator;
    return this;
  }

  public int getOperator() {
    return operator_;
  }

  @Override
  public BinaryExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean isBinaryExpr() {
    return true;
  }

  @Override
  public BinaryExpr asBinaryExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == leftExpression_) {
      setLeftExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == rightExpression_) {
      setRightExpression((Expression<?>) replaceWith);
      return true;
    }
    return false;
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
