package ast.expr;

import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a prefix expression such as ++expression or --expression.
 *
 * @author Ben
 */
public class PrefixExpression extends Expression<PrefixExpression> {

  public static final int PLUSPLUS = 0; // ++
  public static final int MINUSMINUS = 1; // --
  public static final int PLUS = 2; // +
  public static final int MINUS = 3; // -
  public static final int COMP = 4; // ~
  public static final int NOT = 5; // !
  private static final String[] symbols = {"++", "--", "+", "-", "~", "!"};

  public String opString() { return symbols[operator_]; }

  private Expression<?> expression_;
  private int operator_;

  public PrefixExpression() {
    this(PLUSPLUS, null);
  }

  public PrefixExpression(final int operator, Expression<?> expression) {
    this(null, operator, expression);
  }

  public PrefixExpression(Token token, final int operator, Expression<?> expression) {
    super(token);
    setOperator(operator);
    setExpression(expression);
  }

  public PrefixExpression setOperator(final int operator) {
    if (operator == operator_) {
      return this;
    }
    operator_ = operator;
    return this;
  }

  public int getOperator() {
    return operator_;
  }

  public PrefixExpression setExpression(Expression<?> expression) {
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
  public boolean isPrefixExpression() {
    return true;
  }

  @Override
  public PrefixExpression asPrefixExpression() {
    return this;
  }

  @Override
  public PrefixExpression setNodeType(NodeType type) {
    return super.setNodeType(type);
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
