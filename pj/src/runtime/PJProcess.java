package runtime;

/**
 * The runtime representation of a ProcessJ process.
 *
 * @author Ben
 */
public class PJProcess {

  /**
   * The next label to restart from.
   */
  protected int runLabel_;

  /**
   * Indicates whether a process is ready to run or not.
   */
  protected boolean isReady_;

  /**
   * Indicates whether a process is done running or not.
   */
  protected boolean isTerminated_;

  /**
   * The shared scheduler.
   */
  public static Scheduler scheduler_;

  public PJProcess() {
    isReady_ = true;
  }

  public void run() { }

  public void schedule() {
    scheduler_.insert(this);
  }

  public void terminate() { }

  /**
   * Returns true if the process is ready to be scheduled.
   */
  public boolean isReady() {
    return isReady_;
  }

  /**
   * Sets the process ready to run.
   */
  public synchronized void setReady() {
    if (!isReady_) {
      isReady_ = true;
    }
  }

  /**
   * Sets the process not-ready to run.
   */
  public void setNotReady() {
    if (isReady_) {
      isReady_ = false;
    }
  }

  public void setTerminated() {
    isTerminated_ = true;
  }

  /**
   * Returns true if the process has terminated.
   */
  public boolean getTerminated() {
    return isTerminated_;
  }

  /**
   * Dummy method used by the asm instrumentation pass which
   * is used to indicate that a process is going back to the
   * running queue.
   */
  public void yield() { }

  /**
   * Dummy method used by the asm instrumentation pass that
   * takes a number that represents this label.
   */
  public void label(int label) { }

  /**
   * Dummy method used by the asm instrumentation pass that
   * takes a label to which the next resumption should jump.
   */
  public void resume(int label) { }

  /**
   * Dummy method used by the asm instrumentation pass which
   * takes a label to which the goto instruction should jump.
   */
  public static void ___label(int label) { }

  /**
   * Dummy method used by the asm instrumentation pass which
   * takes a label to which the loop should start.
   */
  public static void ___goto(int label) { }
}
