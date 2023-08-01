package ast.toplevel;

import ast.SourceAST;
import ast.expr.Expression;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a variable declaration.
 *
 * @author Ben
 */
public class ConstantDeclaration extends TopLevelDeclaration<ConstantDeclaration> {

  private Expression<?> rightExpression_;

  public ConstantDeclaration() {
    this(null, null);
  }

  public ConstantDeclaration(NodeType type, String name) {
    this(ACC_PUBLIC, type, name);
  }

  public ConstantDeclaration(int modifier, NodeType type, String name) {
    this(modifier, type, name, null);
  }

  public ConstantDeclaration(int modifier, NodeType type, String name, Expression<?> rightExpression) {
    this(null, modifier, type, name, rightExpression);
  }

  public ConstantDeclaration(Token token, int modifier, NodeType type, String name, Expression<?> rightExpression) {
    super(token, modifier, type, name, null);
    setRightExpression(rightExpression);
  }

  public ConstantDeclaration setRightExpression(Expression<?> rightExpression) {
    if (rightExpression == rightExpression_) {
      return this;
    }
    if (rightExpression_ != null) {
      rightExpression_.setParentNode(null);
    }
    rightExpression_ = rightExpression;
    setAsParentNodeOf(rightExpression);
    return this;
  }

  public Optional<Expression<?>> getRightExpression() {
    return Optional.ofNullable(rightExpression_);
  }

  @Override
  public boolean isConstantDeclaration() {
    return true;
  }

  @Override
  public ConstantDeclaration asConstantDeclaration() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == rightExpression_) {
      setRightExpression((Expression<?>) replaceWith);
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
