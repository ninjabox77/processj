package ast.expr;

import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a null literal value.
 *
 * @author Ben
 */
public class NullLiteral extends ConstantExpression {

  public NullLiteral() {
    this("null");
  }

  public NullLiteral(final String value) {
    this(null, value);
  }

  public NullLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isNullLiteral() {
    return true;
  }

  @Override
  public NullLiteral asNullLiteral() {
    return this;
  }

  @Override
  public NullLiteral setNodeType(NodeType type) {
    return (NullLiteral) super.setNodeType(type);
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
