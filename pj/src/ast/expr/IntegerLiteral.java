package ast.expr;

import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an integer literal value.
 *
 * @author Ben
 */
public class IntegerLiteral extends ConstantExpression {

  public IntegerLiteral() {
    this("0");
  }

  public IntegerLiteral(final String value) {
    this(null, value);
  }

  public IntegerLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isIntegerLiteral() {
    return true;
  }

  @Override
  public IntegerLiteral asIntegerLiteral() {
    return this;
  }

  @Override
  public IntegerLiteral setNodeType(NodeType type) {
    return (IntegerLiteral) super.setNodeType(type);
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
