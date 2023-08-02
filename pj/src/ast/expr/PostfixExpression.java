package ast.expr;

import ast.SourceAST;
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
public class PostfixExpression extends Expression<PostfixExpression> {

  public static final int PLUSPLUS = 0; // ++
  public static final int MINUSMINUS = 1; // --
  public static final String[] symbols = {"++", "--"};

  public String opString() { return symbols[operator_]; }

  private Expression<?> expression_;
  private int operator_;

  public PostfixExpression() {
    this(PLUSPLUS, null);
  }

  public PostfixExpression(final int operator, Expression<?> expression) {
    this(null, operator, expression);
  }

  public PostfixExpression(Token token, final int operator, Expression<?> expression) {
    super(token);
    setOperator(operator);
    setExpression(expression);
  }

  public PostfixExpression setOperator(final int operator) {
    if (operator == operator_) {
      return this;
    }
    operator_ = operator;
    return this;
  }

  public int getOperator() {
    return operator_;
  }

  public PostfixExpression setExpression(Expression<?> expression) {
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
  public boolean isPostfixExpression() {
    return true;
  }

  @Override
  public PostfixExpression asPostfixExpression() {
    return this;
  }

  @Override
  public PostfixExpression setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
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
