package ast.expr;

import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a double literal value.
 *
 * @author Ben
 */
public class DoubleLiteral extends ConstantExpression {

  public DoubleLiteral() {
    this("0");
  }

  public DoubleLiteral(final String value) {
    this(null, value);
  }

  public DoubleLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isDoubleLiteral() {
    return true;
  }

  @Override
  public DoubleLiteral asDoubleLiteral() {
    return this;
  }

  @Override
  public DoubleLiteral setNodeType(NodeType type) {
    return (DoubleLiteral) super.setNodeType(type);
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
