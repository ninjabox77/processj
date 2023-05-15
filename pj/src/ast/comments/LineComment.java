package ast.comments;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a line comment.
 *
 * @author Ben
 */
public class LineComment extends Comment {

  public LineComment() {
    this(null);
  }

  public LineComment(final String content) {
    this(null, content);
  }

  public LineComment(Token token, final String content) {
    super(token, content);
  }

  @Override
  public boolean isLineComment() {
    return true;
  }

  @Override
  public LineComment asLineComment() {
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
