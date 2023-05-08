package buildin;

import ast.AnnotatedNode;

/**
 * A base class for a compilation unit.
 *
 * @author Ben
 */
public class ProcessingUnit extends AnnotatedNode {

  // The current phase number.
  protected int phase_;
  // true if the phase is completed.
  protected boolean isPhaseComplete_;

  public ProcessingUnit() {
    phase_ = Phases.INITIALIZATION;
    isPhaseComplete_ = false;
  }

  public int getPhase() {
    return phase_;
  }

  public String getPhaseInformation() {
    return Phases.getInformation(phase_);
  }

  public boolean isPhaseComplete() {
    return isPhaseComplete_;
  }

  public void completePhase() {
    isPhaseComplete_ = true;
  }

  public void nextPhase(int phase) {
    if (!isPhaseComplete_) {
      completePhase();
    }
    phase_ = phase;
    isPhaseComplete_ = false;
  }
}
