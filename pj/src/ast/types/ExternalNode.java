package ast.types;

import ast.AnnotatedNode;
import ast.Node;
import org.antlr.v4.runtime.Token;
import typesystem.ExternalType;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an external Java type in the AST.
 *
 * @author Ben
 */
public class ExternalNode extends AnnotatedNode {

  private ExternalType type_;

  public ExternalNode() {
    this(null, null);
  }

  public ExternalNode(Token token, Type type) {
    super(token);
    setType(type);
  }

  @Override
  public ExternalNode setType(Type type) {
    if (type == type_) {
      return this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type.asExternalType();
    setAsParentNodeOf(type);
    return this;
  }

  @Override
  public ExternalType getType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setType((ExternalType) replaceWith);
      return true;
    }
    return false;
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
