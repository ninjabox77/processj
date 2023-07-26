package ast.stmt;

import ast.Node;
import ast.Parameter;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents the enhanced for loop.
 *
 * @author Ben
 */
public class ForEachStmt extends Statement {

  private Parameter variable_;
  private Expression<?> collectionExpr_;
  private Statement loopBlock_;

  public ForEachStmt() {
    this(null, null);
  }

  public ForEachStmt(Parameter variable, Expression<?> collectionExpr) {
    this(variable, collectionExpr, null);
  }

  public ForEachStmt(Parameter variable, Expression<?> collectionExpr, Statement loopBlock) {
    this(null, variable, collectionExpr, loopBlock);
  }

  public ForEachStmt(Token token, Parameter variable, Expression<?> collectionExpr, Statement loopBlock) {
    super(token, null);
    setVariable(variable);
    setCollectionExpression(collectionExpr);
    setLoopBlock(loopBlock);
  }

  public ForEachStmt setVariable(Parameter variable) {
    if (variable == variable_) {
      return this;
    }
    if (variable_ != null) {
      variable_.setParentNode(null);
    }
    variable_ = variable;
    setAsParentNodeOf(variable);
    return this;
  }

  public Parameter getVariable() {
    return variable_;
  }

  public ForEachStmt setCollectionExpression(Expression<?> collectionExpr) {
    if (collectionExpr == collectionExpr_) {
      return this;
    }
    if (collectionExpr_ != null) {
      collectionExpr_.setParentNode(null);
    }
    collectionExpr_ = collectionExpr;
    setAsParentNodeOf(collectionExpr);
    return this;
  }

  public Expression<?> getCollectionExpr() {
    return collectionExpr_;
  }

  public ForEachStmt setLoopBlock(Statement loopBlock) {
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

  public Optional<Statement> getLoopBlock() {
    return Optional.ofNullable(loopBlock_);
  }

  @Override
  public boolean isForEachStmt() {
    return true;
  }

  @Override
  public ForEachStmt asForEachStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == variable_) {
      setVariable((Parameter) replaceWith);
      return true;
    }
    if (node == collectionExpr_) {
      setCollectionExpression((Expression<?>) replaceWith);
      return true;
    }
    if (node == loopBlock_) {
      setLoopBlock((Statement) replaceWith);
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
