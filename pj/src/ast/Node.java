package ast;

import ast.comments.Comment;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.CodeVisitor;

import java.util.Optional;

/**
 * The main AST node from which different types of nodes extend.
 *
 * @author Ben
 */
public abstract class Node extends ASTNode<Node> implements OptionalType<Node, Type>, CodeVisitor {

  /**
   * The children of this node if it is the root of a subtree.
   */
  protected final Sequence<Node> children_ = new Sequence<>();

  /**
   * The parent of this node if it is not the main compilation unit.
   */
  protected Node parentNode_;

  /**
   * Single line comments attached to this node. Note that these are orphan
   * comments that appear after the first single line or block comment.
   */
  protected Sequence<Comment> orphanComments_;

  /**
   * Main comment attached to this node.
   */
  protected Comment firstComment_;

  public Node(Token token) {
    super(token);
    orphanComments_ = Sequence.sequenceList();
  }

  public boolean replace(Node node, Node replaceWith) {
    return false;
  }

  public boolean remove(Node node) {
    return false;
  }

  protected void setAsParentNodeOf(Node node) {
    if (node != null) {
      node.setParentNode(getParentNodeForChildren());
    }
  }

  protected void setAsParentNodeOf(Sequence<? extends Node> nodes) {
    if (nodes != null) {
      nodes.setParentNode(getParentNodeForChildren());
    }
  }

  // Should be called in every constructor of a specific node.
  public void customInitialization() { }

  public void addOrphanComment(Comment comment) {
    orphanComments_.add(comment);
    comment.setParentNode(this);
  }

  public Comment getCommentAt(int index) {
    if (orphanComments_ != null) {
      if (index >= 0 && index < orphanComments_.size()) {
        return orphanComments_.get(index);
      } else {
        throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + (orphanComments_.size() - 1));
      }
    }
    return null;
  }

  public Node setComment(Comment firstComment) {
    if (firstComment == firstComment_) {
      return this;
    }
    if (firstComment_ != null) {
      firstComment_.setParentNode(null);
    }
    firstComment_ = firstComment;
    setAsParentNodeOf(firstComment);
    return this;
  }

  @Override
  public Optional<Node> getParentNode() {
    return Optional.ofNullable(parentNode_);
  }

  @Override
  public Node setParentNode(Node node) {
    if (parentNode_ == node) {
      return this;
    }
    // Removes the parent node from the children.
    if (parentNode_ != null) {
      final var children = parentNode_.children_;
      children.removeIf(n -> n == this);
      children.trimToSize();
    }
    parentNode_ = node;
    if (parentNode_ != null) {
      parentNode_.children_.add(this);
    }
    return this;
  }

  @Override
  public Node getParentNodeForChildren() {
    return this;
  }
}
