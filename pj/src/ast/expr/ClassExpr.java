package ast.expr;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

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
}
