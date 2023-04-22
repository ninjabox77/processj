package ast.comments;

import org.antlr.v4.runtime.Token;

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
}
