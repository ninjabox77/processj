package ast.stmt;

import ast.AnnotatedNode;
import ast.Node;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Base class for all statements.
 *
 * @author Ben
 */
public abstract class Statement extends AnnotatedNode {

  private Sequence<Expression<?>> labels_;

  public Statement() {
    this(null, null);
  }

  public Statement(Sequence<Expression<?>> labels) {
    this(null, labels);
  }

  public Statement(Token token, Sequence<Expression<?>> statementLabels) {
    super(token);
    setStatementLabels(statementLabels);
    customInitialization();
  }

  public Statement setStatementLabels(Sequence<Expression<?>> labels) {
    if (labels == labels_) {
      return this;
    }
    if (labels_ != null) {
      labels_.setParentNode(null);
    }
    labels_ = labels;
    setAsParentNodeOf(labels);
    return this;
  }

  public Optional<Sequence<Expression<?>>> getStatementLabels() {
    return Optional.ofNullable(labels_);
  }

  public Statement addStatementLabel(Expression<?> expression) {
    if (labels_ == null) {
      labels_ = Sequence.sequenceList();
    }
    if (expression != null) {
      expression.setParentNode(null);
      setAsParentNodeOf(expression);
      labels_.add(expression);
    }
    return this;
  }

  public Statement copyStatementLabel(Statement stat) {
    stat.getStatementLabels().ifPresent(labels -> {
      labels.forEach(this::addStatementLabel);
    });
    return this;
  }

  public boolean isAltStmt() {
    return false;
  }

  public AltStmt<?> asAltStmt() {
    throw new IllegalStateException(String.format("%s is not a AltStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isGuardStmt() {
    return false;
  }

  public GuardStmt asGuardStmt() {
    throw new IllegalStateException(String.format("%s is not a GuardStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isEmpty() {
    return false;
  }

  public boolean isForStmt() {
    return false;
  }

  public ForStmt asForStmt() {
    throw new IllegalStateException(String.format("%s is not a ForStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isWhileStmt() {
    return false;
  }

  public WhileStmt asWhileStmt() {
    throw new IllegalStateException(String.format("%s is not a WhileStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDoWhileStmt() {
    return false;
  }

  public DoWhileStmt asDoWhileStmt() {
    throw new IllegalStateException(String.format("%s is not a DoWhileStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBlockStmt() {
    return false;
  }

  public BlockStmt asBlockStmt() {
    throw new IllegalStateException(String.format("%s is not a BlockStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isSwitchStmt() {
    return false;
  }

  public SwitchStmt asSwitchStmt() {
    throw new IllegalStateException(String.format("%s is not a SwitchStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCaseStmt() {
    return false;
  }

  public CaseStmt asCaseStmt() {
    throw new IllegalStateException(String.format("%s is not a CaseStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isIfStmt() {
    return false;
  }

  public IfStmt asIfStmt() {
    throw new IllegalStateException(String.format("%s is not a IfStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isEmptyStmt() {
    return false;
  }

  public EmptyStmt asEmptyStmt() {
    throw new IllegalStateException(String.format("%s is not an EmptyStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isContinueStmt() {
    return false;
  }

  public ContinueStmt asContinueStmt() {
    throw new IllegalStateException(String.format("%s is not an ContinueStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBreakStmt() {
    return false;
  }

  public BreakStmt asBreakStmt() {
    throw new IllegalStateException(String.format("%s is not an BreakStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isReturnStmt() {
    return false;
  }

  public ReturnStmt asReturnStmt() {
    throw new IllegalStateException(String.format("%s is not a ReturnStmt, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isForEachStmt() {
    return false;
  }

  public ForEachStmt asForEachStmt() {
    throw new IllegalStateException(String.format("%s is not a ForEachStmt, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (labels_ == null) {
      return false;
    }
    for (int i = 0; i < labels_.size(); ++i) {
      if (node == labels_.get(i)) {
        labels_.set(i, (Expression<?>) replaceWith);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (labels_ == null) {
      return false;
    }
    for (int i = 0; i < labels_.size(); ++i) {
      if (node == labels_.get(i)) {
        labels_.remove(i);
        return true;
      }
    }
    return false;
  }
}
