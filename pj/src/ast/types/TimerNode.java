package ast.types;

import ast.AnnotatedNode;
import org.antlr.v4.runtime.Token;
import typesystem.TimerType;
import typesystem.Type;

/**
 * Represents a timer type in the AST.
 *
 * @author Ben
 */
public class TimerNode extends AnnotatedNode {

  private TimerType type_;

  public TimerNode() {
    this(null, null);
  }

  public TimerNode(Type type) {
    this(null, type);
  }

  public TimerNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public TimerNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asPrimitiveType().asTimerType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public TimerType getType() {
    return type_;
  }
}