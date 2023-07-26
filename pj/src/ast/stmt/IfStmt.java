package ast.stmt;

import ast.Node;
import ast.expr.BooleanExpr;
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
public class IfStmt extends Statement {

  private BooleanExpr conditional_;
  private Statement ifPart_;
  private Statement elsePart_;

  public IfStmt() {
    this(new BooleanExpr(), new EmptyStmt());
  }

  public IfStmt(BooleanExpr conditional, Statement ifPart) {
    this(conditional, ifPart, null);
  }

  public IfStmt(BooleanExpr conditional, Statement ifPart, Statement elsePart) {
    this(null, conditional, ifPart, elsePart);
  }

  public IfStmt(Token token, BooleanExpr conditional, Statement ifPart, Statement elsePart) {
    super(token, null);
    setConditional(conditional);
    setIfPart(ifPart);
    setElsePart(elsePart);
  }

  public IfStmt setConditional(BooleanExpr conditional) {
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

  public BooleanExpr getConditional() {
    return conditional_;
  }

  public IfStmt setIfPart(Statement ifPart) {
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

  public IfStmt setElsePart(Statement elsePart) {
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
    return ifPart_.isBlockStmt();
  }

  public boolean hasElseBlock() {
    return elsePart_.isBlockStmt();
  }

  public boolean hasIfElsePart() {
    return elsePart_.isIfStmt();
  }

  public boolean hasElseBranch() {
    return elsePart_ != null;
  }

  @Override
  public boolean isIfStmt() {
    return true;
  }

  @Override
  public IfStmt asIfStmt() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == conditional_) {
      setConditional((BooleanExpr) replaceWith);
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
  public boolean remove(Node node) {
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
