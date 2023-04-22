package ast;

import org.antlr.v4.runtime.Token;
import typesystem.Type;

import java.util.Objects;

/**
 * Represents a parameter in a method or procedure.
 *
 * @author Ben
 */
public class Parameter extends AnnotatedNode implements Modifier<Parameter>, Variable<Parameter> {

  private int modifiers_;
  private Type type_;
  private String name_;

  public Parameter() {
    this(null, null);
  }

  public Parameter(Type type, final String name) {
    this(ACC_NATIVE, type, name);
  }

  public Parameter(final int modifiers, Type type, final String name) {
    this(null, modifiers, type, name);
  }

  public Parameter(Token token, final int modifiers, Type type, final String name) {
    super(token);
    setType(type);
    setName(name);
  }

  @Override
  public Parameter setType(Type type) {
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
  public Type getType() {
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
      setType((Type) replaceWith);
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
}
