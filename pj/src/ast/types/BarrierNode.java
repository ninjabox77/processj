package ast.types;

import ast.AnnotatedNode;
import org.antlr.v4.runtime.Token;
import typesystem.BarrierType;
import typesystem.Type;

/**
 * Represents a barrier type in the AST.
 *
 * @author Ben
 */
public class BarrierNode extends AnnotatedNode {

  private BarrierType type_;

  public BarrierNode() {
    this(null, null);
  }

  public BarrierNode(Type type) {
    this(null, type);
  }

  public BarrierNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public BarrierNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asPrimitiveType().asBarrierType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public BarrierType getType() {
    return type_;
  }
}
