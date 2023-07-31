package ast.types;

import ast.SourceAST;
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
public class ExternalNode extends ASTType {

  private ExternalType type_;

  public ExternalNode() {
    this(null, null);
  }

  public ExternalNode(Token token, Type type) {
    super(token);
    setTSType(type);
  }

  @Override
  public ExternalNode setTSType(Type type) {
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
  public ExternalType getTSType() {
    return type_;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setTSType((ExternalType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isExternalNode() {
    return true;
  }

  @Override
  public ExternalNode asExternalNode() {
    return this;
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
