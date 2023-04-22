package ast.types;

import ast.AnnotatedNode;
import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.IntegerType;
import typesystem.Primitive;
import typesystem.Type;

/**
 * Represents a primitive type in the AST.
 *
 * @author Ben
 */
public class PrimitiveNode extends AnnotatedNode {

  private Primitive type_;

  public PrimitiveNode() {
    this(null, new IntegerType());
  }

  public PrimitiveNode(Type type) {
    this(null, type);
  }

  public PrimitiveNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public PrimitiveNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asPrimitiveType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public Primitive getType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setType((Primitive) replaceWith);
      return true;
    }
    return false;
  }
}
