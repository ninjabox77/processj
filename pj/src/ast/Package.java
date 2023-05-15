package ast;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a package statement.
 *
 * @author Ben
 */
public class Package extends AnnotatedNode {
  private String name_;

  public Package(final String name) {
    this(null, name);
  }

  public Package(Token token, final String name) {
    super(token);
    setName(name);
  }

  public Package setName(String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
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
