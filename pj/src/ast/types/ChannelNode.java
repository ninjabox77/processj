package ast.types;

import ast.AnnotationNode;
import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.ChannelType;
import typesystem.Type;

/**
 * Represents a channel type in the AST.
 *
 * @author Ben
 */
public class ChannelNode extends AnnotationNode {

  private ChannelType type_;

  public ChannelNode() {
    this(null, null);
  }

  public ChannelNode(Type type) {
    this(null, type);
  }

  public ChannelNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public ChannelNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asConstructedType().asChannelType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public ChannelType getType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setType((ChannelType) replaceWith);
      return true;
    }
    return false;
  }
}
