package ast;

import ast.expr.EmptyExpr;
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
public class VariableDecl extends AnnotatedNode implements Variable<VariableDecl>, Modifier<VariableDecl> {

  private int modifiers_;
  private ASTType type_;
  private String name_;
  private Expression<?> rightExpression_;

  public VariableDecl() {
    this(null, null);
  }

  public VariableDecl(ASTType type, final String name) {
    this(0, type, name);
  }

  public VariableDecl(final int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, new EmptyExpr());
  }

  public VariableDecl(final int modifiers, ASTType type, final String name, Expression<?> initExpression) {
    this(null, modifiers, type, name, initExpression);
  }

  public VariableDecl(Token token, final int modifiers, ASTType type, final String name, Expression<?> rightExpression) {
    super(token);
    setModifiers(modifiers);
    setASTType(type);
    setName(name);
    setRightExpression(rightExpression);
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  @Override
  public VariableDecl setModifiers(int modifiers) {
    if (modifiers == modifiers_) {
      return this;
    }
    modifiers_ = modifiers;
    return this;
  }

  @Override
  public VariableDecl setASTType(ASTType type) {
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

  @Override
  public ASTType getASTType() {
    return type_;
  }

  @Override
  public VariableDecl setName(String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public VariableDecl setRightExpression(Expression<?> rightExpression) {
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
