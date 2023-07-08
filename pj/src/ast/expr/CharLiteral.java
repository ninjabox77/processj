package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a character literal value.
 *
 * @author Ben
 */
public class CharLiteral extends ConstantExpr {

  public CharLiteral() {
    this("?");
  }

  public CharLiteral(final String value) {
    this(null, value);
  }

  public CharLiteral(Token token, final String value) {
    super(token, value);
  }

  @Override
  public boolean isCharLiteral() {
    return true;
  }

  @Override
  public CharLiteral asCharLiteral() {
    return this;
  }

  @Override
  public CharLiteral setASTType(ASTType type) {
    return (CharLiteral) super.setASTType(type);
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
