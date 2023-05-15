package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents access to a Java class in an expression, that is, when
 * invoking a static method or access a static member of the class.
 *
 * @author Ben
 */
public class ClassExpr extends Expression<ClassExpr> {

  public ClassExpr() {
    this(null);
  }

  public ClassExpr(Type type) {
    this(null, type);
  }

  public ClassExpr(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public ClassExpr setType(Type type) {
    return super.setType(type);
  }

  @Override
  public boolean isClassExpr() {
    return true;
  }

  @Override
  public ClassExpr asClassExpr() {
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
