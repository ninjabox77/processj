package ast;

import ast.expr.Expression;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a variable declaration.
 *
 * @author Ben
 */
public class VariableDeclarator extends AnnotatedNode implements Modifier<VariableDeclarator> {

  private int modifiers_;
  private ASTType type_;
  private String name_;
  private Expression<?> rightExpression_;

  public VariableDeclarator() {
    this(null, null);
  }

  public VariableDeclarator(ASTType type, final String name) {
    this(0, type, name);
  }

  public VariableDeclarator(final int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public VariableDeclarator(final int modifiers, ASTType type, final String name, Expression<?> initExpression) {
    this(null, modifiers, type, name, initExpression);
  }

  public VariableDeclarator(Token token, final int modifiers, ASTType type, final String name, Expression<?> rightExpression) {
    super(token);
    setModifiers(modifiers);
    setASTType(type);
    setIdentifier(name);
    setRightExpression(rightExpression);
  }

  public String getIdentifier() {
    return name_;
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  @Override
  public VariableDeclarator setModifiers(int modifiers) {
    if (modifiers == modifiers_) {
      return this;
    }
    modifiers_ = modifiers;
    return this;
  }

  public VariableDeclarator setASTType(ASTType type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    setAsParentNodeOf(type);
    return this;
  }

  public ASTType getASTType() {
    return type_;
  }

  public VariableDeclarator setIdentifier(String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public VariableDeclarator setRightExpression(Expression<?> rightExpression) {
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

  public Expression<?> getRightExpression() {
    return rightExpression_;
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
