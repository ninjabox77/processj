package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a constructor call or an object creation (i.e, this
 * should be used when creating an object using the keyword new).
 *
 * @author Ben
 */
public class ConstructorCallExpr extends MethodCallExpr {

  public ConstructorCallExpr() {
    this(null, null, null, null);
  }

  public ConstructorCallExpr(Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    this(null, objectExpression, methodExpression, arguments, typeArguments);
  }

  public ConstructorCallExpr(Token token, Expression<?> objectExpression, Expression<?> methodExpression, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    super(token, objectExpression, methodExpression, arguments, typeArguments);
  }

  @Override
  public boolean isConstructorCallExpr() {
    return true;
  }

  @Override
  public ConstructorCallExpr asConstructorCallExpr() {
    return this;
  }
}
