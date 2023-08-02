package ast.expr;

import ast.SourceAST;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an array access expression.
 *
 * @author Ben
 */
public class ArrayAccess extends Expression<ArrayAccess> {

  private Expression<?> name_;
  private Expression<?> index_;

  public ArrayAccess() {
    this(null, null);
  }

  public ArrayAccess(Expression<?> name, Expression<?> index) {
    this(null, name, index);
  }

  public ArrayAccess(Token token, Expression<?> name, Expression<?> index) {
    super(token);
    setName(name);
    setIndex(index);
  }

  public ArrayAccess setName(Expression<?> name) {
    if (name == name_) {
      return this;
    }
    if (name_ != null) {
      name_.setParentNode(null);
    }
    name_ = name;
    setAsParentNodeOf(name);
    return this;
  }

  public Expression<?> getName() {
    return name_;
  }

  public ArrayAccess setIndex(Expression<?> index) {
    if (index == index_) {
      return this;
    }
    if (index_ != null) {
      index_.setParentNode(null);
    }
    index_ = index;
    setAsParentNodeOf(index);
    return this;
  }

  public Expression<?> getIndex() {
    return index_;
  }

  @Override
  public ArrayAccess setASTType(ASTType type) {
    return super.setASTType(type);
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == name_) {
      setName((Expression<?>) replaceWith);
      return true;
    }
    if (node == index_) {
      setIndex((Expression<?>) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isArrayAccess() {
    return true;
  }

  @Override
  public ArrayAccess asArrayAccess() {
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
