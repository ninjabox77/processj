package runtime;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A simple implementation of a queue of processes.
 *
 * @author Ben
 */
public class RunQueue {

  /**
   * A linked list of processes that may be ready or not ready to run.
   */
  protected Queue<PJProcess> queue_;

  public RunQueue() {
    queue_ = new LinkedList<>();
  }

  /**
   * Inserts a (new or existing) process into the queue.
   */
  public synchronized void insert(PJProcess process) {
    queue_.add(process);
  }

  /**
   * Returns the front process in the queue.
   */
  public synchronized PJProcess getNext() {
    return queue_.remove();
  }

  /**
   * Returns the total number of processes (both ready and not
   * ready) currently stored in the queue.
   */
  public synchronized int getSize() {
    return queue_.size();
  }
}
