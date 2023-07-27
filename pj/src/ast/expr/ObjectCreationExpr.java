package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a constructor call or an object creation (i.e, this
 * should be used when creating an object using the keyword new).
 *
 * @author Ben
 */
public class ObjectCreationExpr extends MethodCallExpr {

  public ObjectCreationExpr() {
    this(null, null, Sequence.sequenceList(), Sequence.sequenceList());
  }

  public ObjectCreationExpr(Expression<?> methodExpression, final String identifier, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    this(null, methodExpression, identifier, arguments, typeArguments);
  }

  public ObjectCreationExpr(Token token, Expression<?> methodExpression, final String identifier, Sequence<Expression<?>> arguments, Sequence<Type> typeArguments) {
    super(token, methodExpression, identifier, arguments, typeArguments);
  }

  @Override
  public boolean isObjectCreationExpr() {
    return true;
  }

  @Override
  public ObjectCreationExpr asObjectCreationExpr() {
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
