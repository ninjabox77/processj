package ast.expr;

import ast.SourceAST;
import ast.Sequence;
import ast.stmt.Statement;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an expression with a block, that is, a pair of curly
 * brackets that can be used as expression that contains a sequence
 * of statements.
 *
 * @author Ben
 */
public class BlockExpression extends Expression<BlockExpression> {

  private Sequence<Statement> statements_;

  public BlockExpression() {
    this(Sequence.sequenceList());
  }

  public BlockExpression(Sequence<Statement> statements) {
    this(null, statements);
  }

  public BlockExpression(Token token, Sequence<Statement> statements) {
    super(token);
    setStatements(statements);
  }

  public BlockExpression setStatements(Sequence<Statement> statements) {
    if (statements == statements_) {
      return this;
    }
    if (statements_ != null) {
      statements_.setParentNode(null);
    }
    statements_ = statements;
    setAsParentNodeOf(statements);
    return this;
  }

  public Sequence<Statement> getStatements() {
    return statements_;
  }

  @Override
  public boolean isBlockExpression() {
    return true;
  }

  @Override
  public BlockExpression asBlockExpression() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < statements_.size(); ++i) {
      if (node == statements_.get(i)) {
        statements_.set(i, (Statement) replaceWith);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < statements_.size(); ++i) {
      if (node == statements_.get(i)) {
        statements_.remove(i);
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
