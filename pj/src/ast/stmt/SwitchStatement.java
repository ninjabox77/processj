package ast.stmt;

import ast.SourceAST;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a switch (expression) case value: ... case value: ... default: ... } statement.
 *
 * @author Ben
 */
public class SwitchStatement extends Statement {

  private Expression<?> selector_;
  private Sequence<SwitchCaseStatement> switchCases_;
  private Statement defaultStmt_;

  public SwitchStatement() {
    this(null);
  }

  public SwitchStatement(Expression<?> selector) {
    this(selector, Sequence.sequenceList());
  }

  public SwitchStatement(Expression<?> selector, Sequence<SwitchCaseStatement> switchCases) {
    this(null, selector, switchCases, null);
  }

  public SwitchStatement(Expression<?> selector, Sequence<SwitchCaseStatement> switchCases, Statement defaultStmt) {
    this(null, selector, switchCases, defaultStmt);
  }

  public SwitchStatement(Token token, Expression<?> selector, Sequence<SwitchCaseStatement> switchCases, Statement defaultStmt) {
    super(token, null);
    setSelector(selector);
    setSwitchCases(switchCases);
    setDefaultStmt(defaultStmt);
  }

  public SwitchStatement setSelector(Expression<?> selector) {
    if (selector == selector_) {
      return this;
    }
    if (selector_ != null) {
      selector_.setParentNode(null);
    }
    selector_ = selector;
    setAsParentNodeOf(selector);
    return this;
  }

  public Expression<?> getSelector() {
    return selector_;
  }

  public SwitchStatement setSwitchCases(Sequence<SwitchCaseStatement> switchCases) {
    if (switchCases == switchCases_) {
      return this;
    }
    if (switchCases_ != null) {
      switchCases_.setParentNode(null);
    }
    switchCases_ = switchCases;
    setAsParentNodeOf(switchCases);
    return this;
  }

  public Sequence<SwitchCaseStatement> getSwitchCases() {
    return switchCases_;
  }

  public SwitchStatement setDefaultStmt(Statement defaultStmt) {
    if (defaultStmt == defaultStmt_) {
      return this;
    }
    if (defaultStmt_ != null) {
      defaultStmt_.setParentNode(null);
    }
    defaultStmt_ = defaultStmt;
    setAsParentNodeOf(defaultStmt);
    return this;
  }

  public Optional<Statement> getDefaultStmt() {
    return Optional.ofNullable(defaultStmt_);
  }

  public SwitchCaseStatement getSwitchCase(int index) {
    if (switchCases_ != null) {
      if (index >= 0 && index < switchCases_.size()) {
        return switchCases_.get(index);
      }
    }
    return null;
  }

  @Override
  public boolean isEmpty() {
    return Optional.ofNullable(switchCases_).map(Sequence::isEmpty).orElse(false);
  }

  @Override
  public boolean isSwitchStatement() {
    return true;
  }

  @Override
  public SwitchStatement asSwitchStatement() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == selector_) {
      setSelector((Expression<?>) replaceWith);
      return true;
    }
    for (int i = 0; i < switchCases_.size(); ++i) {
      if (node == switchCases_.get(i)) {
        switchCases_.set(i, (SwitchCaseStatement) replaceWith);
        return true;
      }
    }
    if (node == defaultStmt_) {
      setDefaultStmt((Statement) replaceWith);
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
