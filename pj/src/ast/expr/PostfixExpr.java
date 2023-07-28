package ast.expr;

import ast.Node;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a postfix expression such as expression++ or expression--.
 *
 * @author Ben
 */
public class PostfixExpr extends Expression<PostfixExpr> {

  public static final int PLUSPLUS = 0; // ++
  public static final int MINUSMINUS = 1; // --
  public static final String[] symbols = {"++", "--"};

  private Expression<?> expression_;
  private int operator_;

  public PostfixExpr() {
    this(PLUSPLUS, null);
  }

  public PostfixExpr(final int operator, Expression<?> expression) {
    this(null, operator, expression);
  }

  public PostfixExpr(Token token, final int operator, Expression<?> expression) {
    super(token);
    setOperator(operator);
    setExpression(expression);
  }

  public PostfixExpr setOperator(final int operator) {
    if (operator == operator_) {
      return this;
    }
    operator_ = operator;
    return this;
  }

  public int getOperator() {
    return operator_;
  }

  public PostfixExpr setExpression(Expression<?> expression) {
    if (expression == expression_) {
      return this;
    }
    if (expression_ != null) {
      expression_.setParentNode(null);
    }
    expression_ = expression;
    setAsParentNodeOf(expression);
    return this;
  }

  public Expression<?> getExpression() {
    return expression_;
  }

  @Override
  public boolean isPostfixExpr() {
    return true;
  }

  @Override
  public PostfixExpr asPostfixExpr() {
    return this;
  }

  @Override
  public PostfixExpr setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
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
