package ast;

import org.antlr.v4.runtime.Token;

/**
 * Base class for any AST node that can be annotated.
 *
 * @author Ben
 */
public abstract class AnnotatedNode extends SourceAST {

  /**
   * Indicates if this node (typically an expression) has been rewritten.
   */
  private boolean isRewritten_;

  /**
   * Indicates if this node was added by the compiler.
   */
  private boolean isFabricated_;

  private Sequence<AnnotationNode> annotations_;

  public AnnotatedNode() {
    this(null);
  }

  public AnnotatedNode(Token token) {
    super(token);
    setAnnotations(Sequence.sequenceList());
  }

  @SuppressWarnings("unchecked")
  public <N extends SourceAST> N setRewritten(final boolean isRewritten) {
    if (isRewritten == isRewritten_) {
      return (N) this;
    }
    isRewritten_ = isRewritten;
    return (N) this;
  }

  public boolean isRewritten() {
    return isRewritten_;
  }

  @SuppressWarnings("unchecked")
  public <N extends SourceAST> N setFabricated(final boolean isFabricated) {
    if (isFabricated == isFabricated_) {
      return (N) this;
    }
    isFabricated_ = isFabricated;
    return (N) this;
  }

  public boolean isFabricated() {
    return isFabricated_;
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
