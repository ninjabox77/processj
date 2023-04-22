package ast;

import org.antlr.v4.runtime.Token;

/**
 * Base class for any AST node that can be annotated.
 *
 * @author Ben
 */
public abstract class AnnotatedNode extends Node {

  /**
   * Indicates if this node (typically an expression) has been rewritten.
   */
  private boolean rewritten_;

  /**
   * Indicates if this node was added by the compiler.
   */
  private boolean fabricated_;

  private Sequence<AnnotationNode> annotations_;

  public AnnotatedNode() {
    this(null);
  }

  public AnnotatedNode(Token token) {
    super(token);
    setAnnotations(Sequence.sequenceList());
  }

  public <N extends Node> N setRewritten(final boolean rewritten) {
    if (rewritten == rewritten_) {
      return (N) this;
    }
    rewritten_ = rewritten;
    return (N) this;
  }

  public boolean isRewritten() {
    return rewritten_;
  }

  public <N extends Node> N setFabricated(final boolean fabricated) {
    if (fabricated == fabricated_) {
      return (N) this;
    }
    fabricated_ = fabricated;
    return (N) this;
  }

  public boolean isFrabricated() {
    return fabricated_;
  }

  public AnnotatedNode setAnnotations(Sequence<AnnotationNode> annotations) {
    if (annotations == annotations_) {
      return this;
    }
    if (annotations_ != null) {
      annotations_.setParentNode(null);
    }
    annotations_ = annotations;
    setAsParentNodeOf(annotations);
    return this;
  }

  public Sequence<AnnotationNode> getAnnotations() {
    return annotations_;
  }

  public void addAnnotation(AnnotationNode annotation) {
    if (annotation != null) {
      if (annotations_ == null) {
        annotations_ = Sequence.sequenceList();
      }
      annotations_.add(annotation);
    }
  }

  public void addAnnotations(Sequence<AnnotationNode> annotations) {
    for (AnnotationNode annotation : annotations) {
      addAnnotation(annotation);
    }
  }
}
