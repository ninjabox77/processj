package ast.stmt;

import ast.SourceAST;
import ast.expr.BooleanExpression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents an if (...) { ... } else { ... } statement. Note
 * that the else is optional.
 *
 * @author Ben
 */
public class IfStatement extends Statement {

  private BooleanExpression conditional_;
  private Statement ifPart_;
  private Statement elsePart_;

  public IfStatement() {
    this(new BooleanExpression(), new EmptyStatement());
  }

  public IfStatement(BooleanExpression conditional, Statement ifPart) {
    this(conditional, ifPart, null);
  }

  public IfStatement(BooleanExpression conditional, Statement ifPart, Statement elsePart) {
    this(null, conditional, ifPart, elsePart);
  }

  public IfStatement(Token token, BooleanExpression conditional, Statement ifPart, Statement elsePart) {
    super(token, null);
    setConditional(conditional);
    setIfPart(ifPart);
    setElsePart(elsePart);
  }

  public IfStatement setConditional(BooleanExpression conditional) {
    if (conditional == conditional_) {
      return this;
    }
    if (conditional_ != null) {
      conditional_.setParentNode(null);
    }
    conditional_ = conditional;
    setAsParentNodeOf(conditional);
    return this;
  }

  public BooleanExpression getConditional() {
    return conditional_;
  }

  public IfStatement setIfPart(Statement ifPart) {
    if (ifPart == ifPart_) {
      return this;
    }
    if (ifPart_ != null) {
      ifPart_.setParentNode(null);
    }
    ifPart_ = ifPart;
    setAsParentNodeOf(ifPart);
    return this;
  }

  public Statement getIfPart() {
    return ifPart_;
  }

  public IfStatement setElsePart(Statement elsePart) {
    if (elsePart == elsePart_) {
      return this;
    }
    if (elsePart_ != null) {
      elsePart_.setParentNode(null);
    }
    elsePart_ = elsePart;
    setAsParentNodeOf(elsePart);
    return this;
  }

  public Optional<Statement> getElsePart() {
    return Optional.ofNullable(elsePart_);
  }

  public boolean hasIfBlock() {
    return ifPart_.isBlockStatement();
  }

  public boolean hasElseBlock() {
    return elsePart_.isBlockStatement();
  }

  public boolean hasIfElsePart() {
    return elsePart_.isIfStatement();
  }

  public boolean hasElseBranch() {
    return elsePart_ != null;
  }

  @Override
  public boolean isIfStatement() {
    return true;
  }

  @Override
  public IfStatement asIfStatement() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == conditional_) {
      setConditional((BooleanExpression) replaceWith);
      return true;
    }
    if (node == ifPart_) {
      setIfPart((Statement) replaceWith);
      return true;
    }
    if (node == elsePart_) {
      setElsePart((Statement) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (node == elsePart_) {
      setElsePart(null);
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
