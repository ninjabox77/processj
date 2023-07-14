package ast.expr;

import ast.Name;
import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure, a record, a protocol, or a constant name
 * access without importing the library.
 *
 * @author Ben
 */
public class PathAccessExpr extends Expression<PathAccessExpr> {

  private Name name_;

  public PathAccessExpr() {
    this(null);
  }

  public PathAccessExpr(Name name) {
    this(null, name);
  }

  public PathAccessExpr(Token token, Name name) {
    super(token);
    setName(name);
  }

  public PathAccessExpr setName(Name name) {
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

  public Name getName() {
    return name_;
  }

  @Override
  public boolean isPathAccessExpr() {
    return true;
  }

  @Override
  public PathAccessExpr asPathAccessExpr() {
    return this;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == name_) {
      setName((Name) replaceWith);
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
