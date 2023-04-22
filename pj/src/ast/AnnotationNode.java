package ast;

import ast.expr.Expression;
import org.antlr.v4.runtime.Token;

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

  private Map<String, Expression<?>> members;

  public AnnotationNode(Token token) {
    super(token);
  }

  public Map<String, Expression<?>> getMembers() {
    if (members == null) {
      return Collections.emptyMap();
    }
    return Collections.unmodifiableMap(members);
  }

  public Expression<?> getMember(final String name) {
    if (members == null) {
      return null;
    }
    return members.get(name);
  }

  private void check() {
    if (members == null) {
      members = new LinkedHashMap<>();
    }
  }

  public AnnotationNode setMember(final String name, Expression<?> value) {
    check();
    members.put(name, value);
    return this;
  }

  public AnnotationNode addMember(final String name, Expression<?> value) {
    check();
    Expression<?> oldValue = members.get(name);
    if (oldValue == null) {
      members.put(name, value);
    } else {
      throw new RuntimeException(String.format("Annotation member %s has already been added.", name));
    }
    return this;
  }
}
