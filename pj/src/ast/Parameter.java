package ast;

import ast.types.NodeType;
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
  private NodeType type_;
  private String name_;
  private boolean isVarargs_;

  public Parameter() {
    this(null, null);
  }

  public Parameter(NodeType type, final String name) {
    this(ACC_NATIVE, type, name);
  }

  public Parameter(final int modifiers, NodeType type, final String name) {
    this(null, modifiers, type, name);
  }

  public Parameter(Token token, final int modifiers, NodeType type, final String name) {
    super(token);
    setNodeType(type);
    setName(name);
  }

  public Parameter setVarargs(boolean isVarargs) {
    if (isVarargs == isVarargs_) {
      return this;
    }
    isVarargs_ = isVarargs;
    return this;
  }

  public boolean isVarargs() {
    return isVarargs_;
  }

  public Parameter setNodeType(NodeType type) {
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

  public NodeType getNodeType() {
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
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setNodeType((NodeType) replaceWith);
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
