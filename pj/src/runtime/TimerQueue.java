package runtime;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Represents a queue of timer processes that runs on its own Java
 * thread and handles all timeout statements. Note that both start()
 * and kill() are called once from the Scheduler class.
 *
 * @author Ben
 */
public class TimerQueue {

  public static BlockingQueue<PJTimer> delayQueue_ = new DelayQueue<>();
  private int size_ = 0;

  private final Thread timeThread_ = new Thread(() -> {
    while (true) {
      // Take out timeout timer objects, one by one, from the delay queue.
      // Thread will wait here until one of them is available.
      PJTimer timer;
      try {
        timer = delayQueue_.take();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      // Set the timer's expired flag to true.
      timer.setExpired();
      // Get the process in which the timeout was initiated.
      PJProcess process = timer.getProcess();
      // If the process is still around, set it ready to run again.
      // time.getProcess() will return null if the process has terminated.
      if (process != null) {
        // The synchronized is no longer needed because the process
        // has synchronized methods - Dr. Pedersen's own words
//        synchronized (process) {
//          process.setReady();
//        }
        process.setReady();
      }
      --size_;
    }
  });

  public synchronized void insert(PJTimer timer) {
    ++size_;
    delayQueue_.offer(timer);
  }

  public void start() {
    timeThread_.start();
  }

  public synchronized void kill() {
    timeThread_.interrupt();
  }

  public int getSize() {
    return size_;
  }
}
