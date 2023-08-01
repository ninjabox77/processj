package ast.stmt;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import scope.VariableScope;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a pair of curly brackets.
 *
 * @author Ben
 */
public class BlockStatement extends Statement {

  private Sequence<Statement> statements_;
  private VariableScope variableScope_;

  public BlockStatement() {
    this(Sequence.sequenceList());
  }

  public BlockStatement(Sequence<Statement> statements) {
    this(null, statements);
  }

  public BlockStatement(Token token, Sequence<Statement> statements) {
    super(token, null);
    setStatements(statements);
  }

  public BlockStatement setStatements(Sequence<Statement> statements) {
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

  public BlockStatement addStatement(Statement stat) {
    if (stat != null) {
      stat.setParentNode(null);
      statements_.add(stat);
      setAsParentNodeOf(stat);
    }
    return this;
  }

  public BlockStatement addStatements(Sequence<Statement> stmts) {
    statements_.addAll(stmts);
    return this;
  }

  public Sequence<Statement> getStatements() {
    return statements_;
  }

  public boolean isSequentialBlock() {
    return false;
  }

  public SequentialBlock asSequentialBlock() {
    throw new IllegalStateException(String.format("%s is not a SequentialBlock, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isEmpty() {
    return statements_.isEmpty();
  }

  @Override
  public boolean isBlockStatement() {
    return true;
  }

  @Override
  public BlockStatement asBlockStatement() {
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
