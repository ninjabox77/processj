package ast.stmt;

import ast.Node;
import ast.expr.BooleanExpr;
import ast.expr.BooleanLiteral;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a while (booleanExpr) { ... } loop.
 *
 * @author Ben
 */
public class WhileStmt extends Statement implements LoopingStmt<WhileStmt> {

  private BooleanExpr condition_;
  private Statement loopBlock_;

  public WhileStmt() {
    this(new BooleanExpr(new BooleanLiteral("false")), new EmptyStmt());
  }

  public WhileStmt(BooleanExpr condition, Statement loopBlock) {
    this(null, condition, loopBlock);
  }

  public WhileStmt(Token token, BooleanExpr condition, Statement loopBlock) {
    super(token, null);
    setCondition(condition);
    setLoopBlock(loopBlock);
  }

  public WhileStmt setCondition(BooleanExpr condition) {
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

  public BooleanExpr getCondition() {
    return condition_;
  }

  @Override
  public WhileStmt setLoopBlock(Statement loopBlock) {
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
  public boolean isWhileStmt() {
    return true;
  }

  @Override
  public WhileStmt asWhileStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == condition_) {
      setCondition((BooleanExpr) replaceWith);
      return true;
    }
    if (node == loopBlock_) {
      setLoopBlock((Statement) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (node == loopBlock_) {
      setLoopBlock(null);
      return true;
    }
    return super.remove(node);
  }
}
