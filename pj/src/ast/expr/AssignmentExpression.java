package ast.expr;

import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an assignment expression.
 *
 * @author Ben
 */
public class AssignmentExpression extends BinaryExpression {

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

  public String opString() { return symbols[operator_]; }

  public AssignmentExpression() {
    this(null, null, EQ);
  }

  public AssignmentExpression(Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    super(leftExpression, rightExpression, operator);
  }

  public AssignmentExpression(Token token, Expression<?> leftExpression, Expression<?> rightExpression, final int operator) {
    super(token, leftExpression, rightExpression, operator);
  }

  @Override
  public boolean isAssignmentExpression() {
    return true;
  }

  @Override
  public AssignmentExpression asAssignmentExpression() {
    return this;
  }

  @Override
  public BinaryExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
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
