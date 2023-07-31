package ast.stmt;

import ast.SourceAST;
import ast.Sequence;
import ast.expr.BooleanExpression;
import ast.expr.BooleanLiteral;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents the standard for (booleanExpr) { ... } loop.
 *
 * @author Ben
 */
public class ForStatement extends Statement implements LoopingStmt<ForStatement> {

  private Sequence<Expression<?>> initialization_;
  private BooleanExpression condition_;
  private Sequence<Expression<?>> update_;
  private Statement loopBlock_;

  public ForStatement() {
    this(Sequence.sequenceList(), new BooleanExpression(new BooleanLiteral("false")), Sequence.sequenceList(), new EmptyStatement());
  }

  public ForStatement(Sequence<Expression<?>> initialization, BooleanExpression condition, Sequence<Expression<?>> update, Statement loopBlock) {
    this(null, initialization, condition, update, loopBlock);
  }

  public ForStatement(Token token, Sequence<Expression<?>> initialization, BooleanExpression condition, Sequence<Expression<?>> update, Statement loopBlock) {
    super(token, null);
    setInitialization(initialization);
    setCondition(condition);
    setUpdate(update);
    setLoopBlock(loopBlock);
  }

  public ForStatement setInitialization(Sequence<Expression<?>> initialization) {
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

  public ForStatement setCondition(BooleanExpression condition) {
    if (condition == condition_) {
      return this;
    }
    if (condition_ != null) {
      condition_.setParentNode(null);
    }
    condition_ = condition;
    setAsParentNodeOf(condition);
    return this;
  }

  public Expression<?> getConditional() {
    return condition_;
  }

  public ForStatement setUpdate(Sequence<Expression<?>> update) {
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

  public boolean hasBlock() {
    return loopBlock_.isBlockStatement();
  }

  @Override
  public Optional<Statement> getLoopBlock() {
    return Optional.ofNullable(loopBlock_);
  }

  @Override
  public ForStatement setLoopBlock(Statement loopBlock) {
    if (loopBlock == loopBlock_) {
      return this;
    }
    if (loopBlock_ != null) {
      loopBlock_.setParentNode(null);
    }
    loopBlock_ = loopBlock;
    setAsParentNodeOf(loopBlock);
    return null;
  }

  @Override
  public boolean isForStatement() {
    return true;
  }

  @Override
  public ForStatement asForStatement() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (initialization_ != null) {
      for (int i = 0; i < initialization_.size(); ++i) {
        if (node == initialization_.get(i)) {
          initialization_.set(i, (Expression<?>) replaceWith);
          return true;
        }
      }
    }
    if (node == condition_) {
      setCondition((BooleanExpression) replaceWith);
      return true;
    }
    if (update_ != null) {
      for (int i = 0; i < update_.size(); ++i) {
        if (node == update_.get(i)) {
          update_.set(i, (Expression<?>) replaceWith);
          return true;
        }
      }
    }
    if (node == loopBlock_) {
      setLoopBlock((Statement) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < initialization_.size(); ++i) {
      if (node == initialization_.get(i)) {
        initialization_.remove(i);
        return true;
      }
    }
    if (node == condition_) {
      setCondition(null);
      return true;
    }
    for (int i = 0; i < update_.size(); ++i) {
      if (node == update_.get(i)) {
        update_.remove(i);
        return true;
      }
    }
    return false;
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
