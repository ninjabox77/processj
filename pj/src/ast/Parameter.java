package ast;

import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a parameter in a method or procedure.
 *
 * @author Ben
 */
public class Parameter extends AnnotatedNode implements Modifier<Parameter>, Variable<Parameter> {

  private int modifiers_;
  private ASTType type_;
  private String name_;
  private boolean isVarargs_;

  public Parameter() {
    this(null, null);
  }

  public Parameter(ASTType type, final String name) {
    this(ACC_NATIVE, type, name);
  }

  public Parameter(final int modifiers, ASTType type, final String name) {
    this(null, modifiers, type, name);
  }

  public Parameter(Token token, final int modifiers, ASTType type, final String name) {
    super(token);
    setASTType(type);
    setName(name);
  }

  @Override
  public Parameter setASTType(ASTType type) {
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
  public Parameter setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setASTType((ASTType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public int getModifiers() {
    return modifiers_;
  }

  @Override
  public Parameter setModifiers(final int modifiers) {
    if (modifiers == modifiers_) {
      return this;
    }
    modifiers_ = modifiers;
    return null;
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
