package ast.comments;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a block comment.
 *
 * @author Ben
 */
public class BlockComment extends Comment {

  public BlockComment() {
    this(null);
  }

  public BlockComment(final String content) {
    this(null, content);
  }

  public BlockComment(Token token, final String content) {
    super(token, content);
  }

  @Override
  public boolean isBlockComment() {
    return true;
  }

  @Override
  public BlockComment asBlockComment() {
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
