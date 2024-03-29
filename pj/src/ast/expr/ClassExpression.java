package ast.expr;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents access to a Java class in an expression, that is, when
 * invoking a static method or access a static member of the class.
 *
 * @author Ben
 */
public class ClassExpression extends Expression<ClassExpression> {

  public ClassExpression() {
    this(null);
  }

  public ClassExpression(ASTType type) {
    this(null, type);
  }

  public ClassExpression(Token token, ASTType type) {
    super(token);
    this.setASTType(type);
  }

  @Override
  public ClassExpression setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean isClassExpression() {
    return true;
  }

  @Override
  public ClassExpression asClassExpression() {
    return this;
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
