package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a null literal value.
 *
 * @author Ben
 */
public class NullLiteral extends ConstantExpr {

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
  public NullLiteral setASTType(ASTType type) {
    return (NullLiteral) super.setASTType(type);
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
