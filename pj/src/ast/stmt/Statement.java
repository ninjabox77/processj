package ast.stmt;

import ast.AnnotatedNode;
import ast.Label;
import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Base class for all statements.
 *
 * @author Ben
 */
public abstract class Statement extends AnnotatedNode {

  private Sequence<Label> labels_;

  public Statement() {
    this(null, Sequence.sequenceList());
  }

  public Statement(Sequence<Label> labels) {
    this(null, labels);
  }

  public Statement(Token token, Sequence<Label> statementLabels) {
    super(token);
    setStatementLabels(statementLabels);
    customInitialization();
  }

  public Statement setStatementLabels(Sequence<Label> labels) {
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

  public Optional<Sequence<Label>> getStatementLabels() {
    return Optional.ofNullable(labels_);
  }

  public Statement addStatementLabel(Label expression) {
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
    stat.getStatementLabels().ifPresent(labels -> labels.forEach(this::addStatementLabel));
    return this;
  }

  public boolean isAltStatement() {
    return false;
  }

  public AltStatement<?> asAltStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a AltStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isAltCaseStatement() {
    return false;
  }

  public AltCaseStatement asAltCaseStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a AltCaseStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isEmpty() {
    return false;
  }

  public boolean isForStatement() {
    return false;
  }

  public ForStatement asForStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a ForStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isWhileStatement() {
    return false;
  }

  public WhileStatement asWhileStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a WhileStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isDoWhileStatement() {
    return false;
  }

  public DoWhileStatement asDoWhileStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a DoWhileStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isBlockStatement() {
    return false;
  }

  public BlockStatement asBlockStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a BlockStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isSwitchStatement() {
    return false;
  }

  public SwitchStatement asSwitchStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a SwitchStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isSwitchCaseStatement() {
    return false;
  }

  public SwitchCaseStatement asSwitchCaseStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a SwitchCaseStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isIfStatement() {
    return false;
  }

  public IfStatement asIfStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a IfStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isEmptyStatement() {
    return false;
  }

  public EmptyStatement asEmptyStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a EmptyStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isContinueStatement() {
    return false;
  }

  public ContinueStatement asContinueStmt() {
    throw new IllegalStateException(String.format("\"%s\" is not a ContinueStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isBreakStatement() {
    return false;
  }

  public BreakStatement asBreakStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a BreakStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isReturnStatement() {
    return false;
  }

  public ReturnStatement asReturnStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a ReturnStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isForEachStatement() {
    return false;
  }

  public ForEachStatement asForEachStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a ForEachStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isExpressionStatement() {
    return false;
  }

  public ExpressionStatement asExpressionStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a ExpressionStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isParStatement() {
    return false;
  }

  public ParStatement<?> asParStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a ParStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isSkipStatement() {
    return false;
  }

  public SkipStatement asSkipStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a SkipStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isStopStatement() {
    return false;
  }

  public StopStatement asStopStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a StopStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  public boolean isLabelStatement() {
    return false;
  }

  public LabelStatement asLabelStatement() {
    throw new IllegalStateException(String.format("\"%s\" is not a LabelStatement, it is a \"%s\"",
        this, getClass().getSimpleName()));
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    for (int i = 0; i < labels_.size(); ++i) {
      if (node == labels_.get(i)) {
        labels_.set(i, (Label) replaceWith);
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
    for (int i = 0; i < labels_.size(); ++i) {
      if (node == labels_.get(i)) {
        labels_.remove(i);
        return true;
      }
    }
    return false;
  }
}
