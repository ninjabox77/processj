package ast;

import ast.expr.Expression;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An annotation that can be attached to any node in the AST. Typically,
 * this should be used with nodes that contain Java types or Java libraries.
 *
 * @author Ben
 */
public class AnnotationNode extends Node {

  private Map<String, Expression<?>> members_;

  public AnnotationNode(Token token) {
    super(token);
  }

  public Map<String, Expression<?>> getMembers() {
    if (members_ == null) {
      return Collections.emptyMap();
    }
    return Collections.unmodifiableMap(members_);
  }

  public Expression<?> getMember(final String name) {
    if (members_ == null) {
      return null;
    }
    return members_.get(name);
  }

  private void check() {
    if (members_ == null) {
      members_ = new LinkedHashMap<>();
    }
  }

  public AnnotationNode setMember(final String name, Expression<?> value) {
    check();
    members_.put(name, value);
    return this;
  }

  public AnnotationNode addMember(final String name, Expression<?> value) {
    check();
    Expression<?> oldValue = members_.get(name);
    if (oldValue == null) {
      members_.put(name, value);
    } else {
      throw new RuntimeException(String.format("Annotation member %s has already been added.", name));
    }
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
