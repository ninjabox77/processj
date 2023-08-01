package ast.expr;

import ast.Sequence;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an array literal such as "{{1,2},{3,4}}", where the
 * outer { } is an ArrayLiteral that has two expressions inside,
 * which are also two ArrayLiterals.
 *
 * @author Ben
 */
public class ArrayInitializer extends ListExpression<ArrayInitializer> {

  public ArrayInitializer() {
    this(new EmptyExpression());
  }

  public ArrayInitializer(Expression<?>... values) {
    this(null, Sequence.sequenceList(values));
  }

  public ArrayInitializer(Token token, Sequence<Expression<?>> values) {
    super(token, values);
  }

  public ArrayInitializer setValues(Sequence<Expression<?>> values) {
    return super.setValues(values);
  }

  @Override
  public boolean isArrayInitializer() {
    return true;
  }

  @Override
  public ArrayInitializer asArrayInitializer() {
    return this;
  }

  @Override
  public ArrayInitializer setNodeType(NodeType type) {
    return super.setNodeType(type);
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
