package ast.stmt;

import ast.Node;
import ast.Sequence;
import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents a switch (expression) case value: ... case value: ... default: ... } statement.
 *
 * @author Ben
 */
public class SwitchStmt extends Statement {

  private Expression<?> selector_;
  private Sequence<CaseStmt> switchCases_;
  private Statement defaultStmt_;

  public SwitchStmt() {
    this(null);
  }

  public SwitchStmt(Expression<?> selector) {
    this(selector, null);
  }

  public SwitchStmt(Expression<?> selector, Sequence<CaseStmt> switchCases) {
    this(null, selector, switchCases, null);
  }

  public SwitchStmt(Expression<?> selector, Sequence<CaseStmt> switchCases, Statement defaultStmt) {
    this(null, selector, switchCases, defaultStmt);
  }

  public SwitchStmt(Token token, Expression<?> selector, Sequence<CaseStmt> switchCases, Statement defaultStmt) {
    super(token, null);
    setSelector(selector);
    setSwitchCases(switchCases);
    setDefaultStmt(defaultStmt);
  }

  public SwitchStmt setSelector(Expression<?> selector) {
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

  public SwitchStmt setSwitchCases(Sequence<CaseStmt> switchCases) {
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

  public Optional<Sequence<CaseStmt>> getSwitchCases() {
    return Optional.ofNullable(switchCases_);
  }

  public SwitchStmt setDefaultStmt(Statement defaultStmt) {
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

  public CaseStmt getCase(int index) {
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
  public boolean isSwitchStmt() {
    return true;
  }

  @Override
  public SwitchStmt asSwitchStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == selector_) {
      setSelector((Expression<?>) replaceWith);
      return true;
    }
    if (switchCases_ != null) {
      for (int i = 0; i < switchCases_.size(); ++i) {
        if (node == switchCases_.get(i)) {
          switchCases_.set(i, (CaseStmt) replaceWith);
          return true;
        }
      }
    }
    if (node == defaultStmt_) {
      setDefaultStmt((Statement) replaceWith);
      return true;
    }
    return super.replace(node, replaceWith);
  }
}
