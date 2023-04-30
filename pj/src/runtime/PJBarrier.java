package runtime;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a barrier in ProcessJ.
 *
 * @author Ben
 */
public class PJBarrier {

  /**
   * The list of processes that have synchronized on the barrier.
   */
  private final List<PJProcess> declaredProcesses_;

  /**
   * The number of processes enrolled on the barrier.
   */
  private int enrolledCount_;

  /**
   * Any process that declares a barrier is itself enrolled in it,
   * so the enrolled count should always start at 1.
   */
  public PJBarrier() {
    declaredProcesses_ = new ArrayList<>();
    enrolledCount_ = 1;
  }

  /**
   * The -1 represents the process that declared the barrier.
   */
  public synchronized void enroll(int m) {
    enrolledCount_ = enrolledCount_ + m - 1;
  }

  public synchronized void resign() {
    if (enrolledCount_ > 1) {
      enrolledCount_ -= 1;
    }
  }

  /**
   * Synchronizes on the barrier. A process is needed to set all
   * processes ready once everyone has synchronized on the barrier.
   */
  public synchronized void sync(final PJProcess process) {
    process.setNotReady();
    declaredProcesses_.add(process);
    if (declaredProcesses_.size() == enrolledCount_) {
      for (PJProcess p : declaredProcesses_) {
        p.setReady();
      }
      declaredProcesses_.clear();
    }
  }
}
