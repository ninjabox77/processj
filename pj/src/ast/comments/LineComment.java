package ast.comments;

import org.antlr.v4.runtime.Token;

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
}
