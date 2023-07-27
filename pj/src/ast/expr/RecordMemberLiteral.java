package ast.expr;

import ast.AnnotatedNode;
import ast.Name;
import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a record member literal.
 *
 * @author Ben
 */
public class RecordMemberLiteral extends AnnotatedNode {

  private Name name_;
  private Expression<?> expression_;

  public RecordMemberLiteral() {
    this(null, null);
  }

  public RecordMemberLiteral(Name name, Expression<?> expression) {
    this(null, name, expression);
  }

  public RecordMemberLiteral(Token token, Name name, Expression<?> expression) {
    super(token);
    setName(name);
    setExpression(expression);
  }

  public RecordMemberLiteral setName(Name name) {
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

  public RecordMemberLiteral setExpression(Expression<?> expression) {
    if (expression == expression_) {
      return this;
    }
    if (expression_ != null) {
      expression_.setParentNode(null);
    }
    expression_ = expression;
    setAsParentNodeOf(expression);
    return this;
  }

  public Expression<?> getExpression() {
    return expression_;
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
    if (node == expression_) {
      setExpression((Expression<?>) replaceWith);
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
