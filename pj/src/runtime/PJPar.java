package runtime;

/**
 * Represents a par statement in ProcessJ.
 *
 * @author Ben.
 */
public class PJPar {

  /**
   * The process declaring the par block.
   */
  private final PJProcess process_;

  /**
   * The number of processes declared inside the par block.
   */
  private int processCount_;

  public PJPar(int processCount, PJProcess process) {
    processCount_ = processCount;
    process_ = process;
  }

  public void setProcessCount(int processCount) {
    processCount_ = processCount;
  }

  /**
   * Decrements the number of process in the par block. The last process
   * in the par block that calls this method causes the process that
   * represents the par statement to become ready to run again.
   */
  public synchronized void decrement() {
    --processCount_;
    if (processCount_ == 0) {
      process_.setReady();
    }
  }

  public synchronized boolean shouldYield() {
    if (processCount_ == 0) {
      return false;
    }
    process_.setNotReady();
    return true;
  }
}
