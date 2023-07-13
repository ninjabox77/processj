package ast.toplevel;

import ast.Node;
import ast.expr.Expression;
import ast.types.ASTType;
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
public class ConstantDecl extends TopLevelDeclaration<ConstantDecl> {

  private Expression<?> rightExpression_;

  public ConstantDecl() {
    this(null, null);
  }

  public ConstantDecl(ASTType type, String name) {
    this(ACC_PUBLIC, type, name);
  }

  public ConstantDecl(int modifier, ASTType type, String name) {
    this(modifier, type, name, null);
  }

  public ConstantDecl(int modifier, ASTType type, String name, Expression<?> rightExpression) {
    this(null, modifier, type, name, rightExpression);
  }

  public ConstantDecl(Token token, int modifier, ASTType type, String name, Expression<?> rightExpression) {
    super(token, modifier, type, name, null);
    setRightExpression(rightExpression);
  }

  public ConstantDecl setRightExpression(Expression<?> rightExpression) {
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
  public boolean isConstantDecl() {
    return true;
  }

  @Override
  public ConstantDecl asConstantDecl() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
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
