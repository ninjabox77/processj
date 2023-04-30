package runtime;

/**
 * The ProcessJ runtime systems scheduler.
 *
 * @author Ben
 */
public class Scheduler extends Thread {

  private final TimerQueue timerQueue_ = new TimerQueue();
  private final RunQueue runQueue_ = new RunQueue();
  private long startTime_ = 0L;

  protected synchronized void insert(PJProcess p) {
    runQueue_.insert(p);
  }

  protected synchronized void insert(PJTimer t) {
    timerQueue_.insert(t);
  }

  private synchronized int getSize() {
    return runQueue_.getSize();
  }

  @Override
  public void run() {
    startTime_ = System.nanoTime();
    System.err.println("[Scheduler] Scheduler running..");

    timerQueue_.start();

    int contextSwitches = 0;

    while (runQueue_.getSize() > 0) {
      // Grab the next process (ready or not) from the queue
      final PJProcess process = runQueue_.getNext();
      // If the process is ready to run, let it run.
      if (process.isReady()) {
        process.run();
        contextSwitches++;
        if (!process.getTerminated()) {
          // Did not terminate, so insert it in queue. Note that the
          // process is responsible for setting its ready flag.
          runQueue_.insert(process);
        } else {
          // Did terminate so do nothing.
          process.terminate();
        }
      } else {
        // Not ready so put it back in the queue and count it as not ready
        runQueue_.insert(process);
      }
    }
    timerQueue_.kill();
    System.err.println("Total context switches: " + contextSwitches);
    System.err.println("Number of process: " + runQueue_.getSize());
    logExecutionTime();
  }

  private void logExecutionTime() {
    final long endTime = System.nanoTime();
    final long elapsedTime = endTime - startTime_;
    final double seconds = (double) elapsedTime / 1000000000.0;
    System.out.println("Total execution time: " + seconds + " secs");
  }
}
