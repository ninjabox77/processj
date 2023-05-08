package ast.expr;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents an array literal such as "{{1,2},{3,4}}", where the
 * outer { } is an ArrayLiteral that has two expressions inside,
 * which are also two ArrayLiterals.
 *
 * @author Ben
 */
public class ArrayExpr extends ListExpression<ArrayExpr> {

  public ArrayExpr() {
    this(null);
  }

  public ArrayExpr(Expression<?>... values) {
    this(null, Sequence.sequenceList(values));
  }

  public ArrayExpr(Token token, Sequence<Expression<?>> values) {
    super(token, values);
  }

  public ArrayExpr setValues(Sequence<Expression<?>> values) {
    return super.setValues(values);
  }

  @Override
  public boolean isArrayExpr() {
    return true;
  }

  @Override
  public ArrayExpr asArrayExpr() {
    return this;
  }
}
