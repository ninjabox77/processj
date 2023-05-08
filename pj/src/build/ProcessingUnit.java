package build;

import ast.AnnotatedNode;
import org.antlr.v4.runtime.Token;

/**
 * A base class for a compilation unit.
 *
 * @author Ben
 */
public abstract class ProcessingUnit extends AnnotatedNode {

  // The current phase number.
  protected int phase_;
  // true if the phase is completed.
  protected boolean isPhaseComplete_;

  public ProcessingUnit() {
    this(null);
  }

  public ProcessingUnit(Token token) {
    super(token);
    customInitialization();
  }

  @Override
  public void customInitialization() {
    phase_ = Phases.INITIALIZATION;
    isPhaseComplete_ = false;
  }

  public ProcessingUnit setPhase(final int phase) {
    if (phase == phase_) {
      return this;
    }
    phase_ = phase;
    return this;
  }

  public int getPhase() {
    return phase_;
  }

  public String getPhaseInformation() {
    return Phases.getInformation(phase_);
  }

  public ProcessingUnit setPhaseComplete(final boolean isPhaseComplete) {
    if (isPhaseComplete == isPhaseComplete_) {
      return this;
    }
    isPhaseComplete_ = isPhaseComplete;
    return this;
  }

  public boolean isPhaseComplete() {
    return isPhaseComplete_;
  }

  public ProcessingUnit completePhase() {
    isPhaseComplete_ = true;
    return this;
  }

  public ProcessingUnit nextPhase(final int phase) {
    if (!isPhaseComplete_) {
      completePhase();
    }
    phase_ = phase;
    isPhaseComplete_ = false;
    return this;
  }
}
