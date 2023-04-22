package ast.comments;

import ast.Node;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

/**
 * Base class for all comments.
 *
 * @author Ben
 */
public abstract class Comment extends Node {

  private String content_;

  private Node commentedNode_;

  public Comment(final String content) {
    this(null, content);
  }

  public Comment(Token token, final String content) {
    super(token);
    setContent(content);
    customInitialization();
  }

  public Comment setContent(final String content) {
    if (Objects.equals(content, content_)) {
      return this;
    }
    content_ = content;
    return this;
  }

  public String getContent() {
    return content_;
  }

  public Comment setCommentedNode(Node commentedNode) {
    if (commentedNode == commentedNode_) {
      return this;
    }
    commentedNode_ = commentedNode;
    return this;
  }

  public Node getCommentedNode() {
    return commentedNode_;
  }

  public boolean isLineComment() {
    return false;
  }

  public LineComment asLineComment() {
    throw new IllegalStateException(String.format("%s is not a LineComment, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBlockComment() {
    return false;
  }

  public BlockComment asBlockComment() {
    throw new IllegalStateException(String.format("%s is not a BlockComment, it is a %s", this, getClass().getSimpleName()));
  }
}
