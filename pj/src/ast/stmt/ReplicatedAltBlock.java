package ast.stmt;

import ast.SourceAST;
import ast.Sequence;
import ast.expr.BooleanLiteral;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a replicated alt statement.
 *
 * @author Ben
 */
public class ReplicatedAltBlock extends AltStatement<ReplicatedAltBlock> {

  private Sequence<Expression<?>> initialization_;
  private Expression<?> conditional_;
  private Sequence<Expression<?>> update_;

  public ReplicatedAltBlock() {
    this(Sequence.sequenceList(), new BooleanLiteral("false"), Sequence.sequenceList(), null);
  }

  public ReplicatedAltBlock(Sequence<Expression<?>> initialization, Expression<?> conditional, Sequence<Expression<?>> update, Statement loopBlock) {
    this(null, initialization, conditional, update, loopBlock);
  }

  public ReplicatedAltBlock(Token token, Sequence<Expression<?>> initialization, Expression<?> conditional, Sequence<Expression<?>> update, Statement loopBlock) {
    super(token, loopBlock);
    setInitialization(initialization);
    setConditional(conditional);
    setUpdate(update);
  }

  public ReplicatedAltBlock setInitialization(Sequence<Expression<?>> initialization) {
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

  public ReplicatedAltBlock setConditional(Expression<?> conditional) {
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

  public ReplicatedAltBlock setUpdate(Sequence<Expression<?>> update) {
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
  public boolean isReplicatedAltBlock() {
    return true;
  }

  @Override
  public ReplicatedAltBlock asReplicatedAltBlock() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < initialization_.size(); ++i) {
      if (node == initialization_.get(i)) {
        initialization_.set(i, (Expression<?>) replaceWith);
        return true;
      }
    }
    if (node == conditional_) {
      setConditional((Expression<?>) replaceWith);
      return true;
    }
    for (int i = 0; i < update_.size(); ++i) {
      if (node == update_.get(i)) {
        update_.set(i, (Expression<?>) replaceWith);
        return true;
      }
    }
    return super.replace(node, replaceWith);
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
