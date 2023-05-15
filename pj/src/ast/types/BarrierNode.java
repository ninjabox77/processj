package ast.types;

import ast.AnnotatedNode;
import org.antlr.v4.runtime.Token;
import typesystem.BarrierType;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

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
