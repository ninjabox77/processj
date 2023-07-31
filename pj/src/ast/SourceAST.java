package ast;

import ast.comments.Comment;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;
import visitor.EqualsVisitor;
import visitor.HashcodeVisitor;

import java.util.Optional;

/**
 * The main AST node from which different types of nodes extend.
 *
 * @author Ben
 */
public abstract class SourceAST extends CommonAST<SourceAST> implements NodeWithASTType<SourceAST, ASTType>, Visitor {

  // The children of this node if it is the root of a subtree.
  protected final Sequence<SourceAST> children_ = new Sequence<>();
  // The parent of this node if it is not the main compilation unit.
  protected SourceAST parentNode_;
  // Single line comments attached to this node. Note that these are orphan
  // comments that appear after the first single line or block comment.
  protected Sequence<Comment> orphanComments_;
  // Main comment attached to this node.
  protected Comment comment_;

  public SourceAST(Token token) {
    super(token);
    orphanComments_ = Sequence.sequenceList();
  }

  public boolean replace(SourceAST node, SourceAST replaceWith) {
    return false;
  }

  public boolean remove(SourceAST node) {
    return false;
  }

  protected void setAsParentNodeOf(SourceAST node) {
    if (node != null) {
      node.setParentNode(getParentNodeForChildren());
    }
  }

  protected void setAsParentNodeOf(Sequence<? extends SourceAST> nodes) {
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

  public SourceAST setComment(Comment firstComment) {
    if (firstComment == comment_) {
      return this;
    }
    if (comment_ != null) {
      comment_.setParentNode(null);
    }
    comment_ = firstComment;
    setAsParentNodeOf(firstComment);
    return this;
  }

  public Optional<Comment> getComment() {
    return Optional.ofNullable(comment_);
  }

  @Override
  public Optional<SourceAST> getParentNode() {
    return Optional.ofNullable(parentNode_);
  }

  @Override
  public SourceAST setParentNode(SourceAST node) {
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
  public SourceAST getParentNodeForChildren() {
    return this;
  }

  @Override
  public int hashCode() {
    return HashcodeVisitor.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SourceAST)) {
      return false;
    }
    return EqualsVisitor.equals(this, (SourceAST) obj);
  }
}
