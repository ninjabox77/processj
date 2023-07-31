package ast.stmt;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a label statement.
 *
 * @author Ben
 */
public class LabelStatement extends Statement {

  private Statement statement_;

  public LabelStatement() {
    this(null);
  }

  public LabelStatement(Statement statement) {
    this(null, statement);
  }

  public LabelStatement(Token token, Statement statement) {
    super(null);
    setStatement(statement);
  }

  public LabelStatement setStatement(Statement statement) {
    if (statement == statement_) {
      return this;
    }
    if (statement_ != null) {
      statement_.setParentNode(null);
    }
    statement_ = statement;
    setAsParentNodeOf(statement);
    return this;
  }

  public Statement getStatement() {
    return statement_;
  }

  @Override
  public boolean isLabelStatement() {
    return true;
  }

  @Override
  public LabelStatement asLabelStatement() {
    return this;
  }

  @Override
  public boolean remove(SourceAST node) {
    return super.remove(node);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == statement_) {
      setStatement((Statement) replaceWith);
      return true;
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
