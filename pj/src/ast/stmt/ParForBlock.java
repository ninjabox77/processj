package ast.stmt;

import ast.Sequence;
import ast.expr.BooleanLiteral;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a par-block that looks like a for-statement.
 *
 * @author Ben
 */
public class ParForBlock extends ParStatement<ParForBlock> {

  private Sequence<Expression<?>> initialization_;
  private Expression<?> conditional_;
  private Sequence<Expression<?>> update_;

  public ParForBlock() {
    this(Sequence.sequenceList(), new BooleanLiteral("false"), Sequence.sequenceList(), null);
  }

  public ParForBlock(Sequence<Expression<?>> initialization, Expression<?> conditional, Sequence<Expression<?>> update, Statement loopBlock) {
    this(null, initialization, conditional, update, loopBlock);
  }

  public ParForBlock(Token token, Sequence<Expression<?>> initialization, Expression<?> conditional, Sequence<Expression<?>> update, Statement loopBlock) {
    super(token, loopBlock);
    setInitialization(initialization);
    setConditional(conditional);
    setUpdate(update);
  }

  public ParForBlock setInitialization(Sequence<Expression<?>> initialization) {
    if (initialization == initialization_) {
      return this;
    }
    if (initialization_ != null) {
      initialization_.setParentNode(null);
    }
    initialization_ = initialization;
    setAsParentNodeOf(initialization);
    return this;
  }

  public Sequence<Expression<?>> getInitialization() {
    return initialization_;
  }

  public ParForBlock setConditional(Expression<?> conditional) {
    if (conditional == conditional_) {
      return this;
    }
    if (conditional_ != null) {
      conditional_.setParentNode(null);
    }
    conditional_ = conditional;
    setAsParentNodeOf(conditional);
    return this;
  }

  public Expression<?> getConditional() {
    return conditional_;
  }

  public ParForBlock setUpdate(Sequence<Expression<?>> update) {
    if (update == update_) {
      return this;
    }
    if (update_ != null) {
      update_.setParentNode(null);
    }
    update_ = update;
    setAsParentNodeOf(update);
    return this;
  }

  public Sequence<Expression<?>> getUpdate() {
    return update_;
  }

  @Override
  public boolean isParForBlock() {
    return true;
  }

  @Override
  public ParForBlock asParForBlock() {
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
