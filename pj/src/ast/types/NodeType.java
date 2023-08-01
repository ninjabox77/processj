package ast.types;

import ast.AnnotatedNode;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Base class for all nodes that represent a type in the AST.
 *
 * @author Ben
 */
public abstract class NodeType extends AnnotatedNode {

  public NodeType() {
    this(null);
    customInitialization();
  }

  public NodeType(Token token) {
    super(token);
  }

  public abstract NodeType setTSType(Type type);

  public abstract Type getTSType();

  public boolean isArrayNode() {
    return false;
  }

  public ArrayNode asArrayNode() {
    throw new IllegalStateException(String.format("%s is not a ArrayNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBarrierNode() {
    return false;
  }

  public BarrierNode asBarrierNode() {
    throw new IllegalStateException(String.format("%s is not a BarrierNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelEndNode() {
    return false;
  }

  public ChannelEndNode asChannelEndNode() {
    throw new IllegalStateException(String.format("%s is not a ChannelEndNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelNode() {
    return false;
  }

  public ChannelNode asChannelNode() {
    throw new IllegalStateException(String.format("%s is not a ChannelNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isExternalNode() {
    return false;
  }

  public ExternalNode asExternalNode() {
    throw new IllegalStateException(String.format("%s is not a ExternalNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPrimitiveNode() {
    return false;
  }

  public PrimitiveNode asPrimitiveNode() {
    throw new IllegalStateException(String.format("%s is not a PrimitiveNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTimerNode() {
    return false;
  }

  public TimerNode asTimerNode() {
    throw new IllegalStateException(String.format("%s is not a TimerNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isVoidNode() {
    return false;
  }

  public VoidNode asVoidNode() {
    throw new IllegalStateException(String.format("%s is not a VoidNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isConstructedNode() {
    return false;
  }

  public ConstructedNode asConstructedNode() {
    throw new IllegalStateException(String.format("%s is not a ConstructedNode, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isErrorNode() {
    return false;
  }

  public ErrorNode asErrorNode() {
    throw new IllegalStateException(String.format("%s is not an ErrorNode, it is a %s", this, getClass().getSimpleName()));
  }
}
