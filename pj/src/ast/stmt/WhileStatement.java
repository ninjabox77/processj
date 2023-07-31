package ast.stmt;

import ast.SourceAST;
import ast.expr.BooleanExpression;
import ast.expr.BooleanLiteral;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a while (booleanExpr) { ... } loop.
 *
 * @author Ben
 */
public class WhileStatement extends Statement implements LoopingStmt<WhileStatement> {

  private BooleanExpression condition_;
  private Statement loopBlock_;

  public WhileStatement() {
    this(new BooleanExpression(new BooleanLiteral("false")), new EmptyStatement());
  }

  public WhileStatement(BooleanExpression condition, Statement loopBlock) {
    this(null, condition, loopBlock);
  }

  public WhileStatement(Token token, BooleanExpression condition, Statement loopBlock) {
    super(token, null);
    setCondition(condition);
    setLoopBlock(loopBlock);
  }

  public WhileStatement setCondition(BooleanExpression condition) {
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

  public BooleanExpression getCondition() {
    return condition_;
  }

  @Override
  public WhileStatement setLoopBlock(Statement loopBlock) {
    if (loopBlock == loopBlock_) {
      return this;
    }
    if (loopBlock_ != null) {
      loopBlock_.setParentNode(null);
    }
    loopBlock_ = loopBlock;
    setAsParentNodeOf(loopBlock);
    return this;
  }

  @Override
  public Optional<Statement> getLoopBlock() {
    return Optional.ofNullable(loopBlock_);
  }

  @Override
  public boolean isWhileStatement() {
    return true;
  }

  @Override
  public WhileStatement asWhileStatement() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == condition_) {
      setCondition((BooleanExpression) replaceWith);
      return true;
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
    if (node == loopBlock_) {
      setLoopBlock(null);
      return true;
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
