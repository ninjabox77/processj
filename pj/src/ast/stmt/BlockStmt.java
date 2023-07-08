package ast.stmt;

import ast.Node;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a pair of curly brackets.
 *
 * @author Ben
 */
public class BlockStmt extends Statement {

  private Sequence<Statement> statements_;
//  private VariableScope scope_;

  public BlockStmt() {
    this(Sequence.sequenceList());
  }

  public BlockStmt(Sequence<Statement> statements) {
//    this(statements, null);
    this(null, statements);
  }

//  public BlockStmt(Sequence<Statement> statements, final VariableScope scope) {
//    this(null, statements, scope);
//  }

  public BlockStmt(Token token, Sequence<Statement> statements/*, VariableScope scope*/) {
    super(token, null);
    setStatements(statements);
//    setScope(scope);
  }

  public BlockStmt setStatements(Sequence<Statement> statements) {
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

  public BlockStmt addStatement(Statement stat) {
    if (stat != null) {
      stat.setParentNode(null);
      statements_.add(stat);
      setAsParentNodeOf(stat);
    }
    return this;
  }

  public BlockStmt addStatements(Sequence<Statement> stmts) {
    statements_.addAll(stmts);
    return this;
  }

  public Sequence<Statement> getStatements() {
    return statements_;
  }

//  public BlockStmt setScope(final VariableScope scope) {
//    if (scope == scope_) {
//      return this;
//    }
//    if (scope_ != null) {
//      scope_.setParentNode(null);
//    }
//    scope_ = scope;
//    return this;
//  }

//  public VariableScope getScope() {
//    return scope_;
//  }

  @Override
  public boolean isEmpty() {
    return statements_.isEmpty();
  }

  @Override
  public boolean isBlockStmt() {
    return true;
  }

  @Override
  public BlockStmt asBlockStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < statements_.size(); ++i) {
      if (node == statements_.get(i)) {
        statements_.set(i, (Statement) replaceWith);
        return true;
      }
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < statements_.size(); ++i) {
      if (node == statements_.get(i)) {
        statements_.remove(i);
        return true;
      }
    }
    return super.remove(node);
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
