package ast.expr;

import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Collections;

/**
 * Represents a tuple "(1,2,3,...)" expression.
 *
 * @author Ben
 */
public class TupleExpression extends ListExpression<TupleExpression> {

  public TupleExpression() {
    this(null, Sequence.sequenceList());
  }

  public TupleExpression(Expression<?> e1) {
    addExpression(e1);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2) {
    addExpression(e1);
    addExpression(e2);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3, Expression<?> e4) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
    addExpression(e4);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3, Expression<?> e4, Expression<?> e5) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
    addExpression(e4);
    addExpression(e5);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3, Expression<?> e4, Expression<?> e5, Expression<?> e6) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
    addExpression(e4);
    addExpression(e5);
    addExpression(e6);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3, Expression<?> e4, Expression<?> e5, Expression<?> e6, Expression<?> e7) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
    addExpression(e4);
    addExpression(e5);
    addExpression(e6);
    addExpression(e7);
  }

  public TupleExpression(Expression<?> e1, Expression<?> e2, Expression<?> e3, Expression<?> e4, Expression<?> e5, Expression<?> e6, Expression<?> e7, Expression<?> e8) {
    addExpression(e1);
    addExpression(e2);
    addExpression(e3);
    addExpression(e4);
    addExpression(e5);
    addExpression(e6);
    addExpression(e7);
    addExpression(e8);
  }

  public TupleExpression(Sequence<Expression<?>> tuples) {
    this(null, tuples);
  }

  public TupleExpression(Token token, Sequence<Expression<?>> tuples) {
    super(token, tuples);
  }

  public TupleExpression addExpression(Expression<?> expression) {
    if (values_ == Collections.EMPTY_LIST) {
      super.setValues(Sequence.sequenceList());
    }
    values_.add(expression);
    return this;
  }

  @Override
  public boolean isTupleExpression() {
    return true;
  }

  @Override
  public TupleExpression asTupleExpression() {
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
