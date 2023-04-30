package runtime;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Represents a timer process.
 *
 * @author Ben
 */
public class PJTimer implements Delayed {

  private PJProcess process_;
  private long delay_;
  private boolean isKilled_;
  private boolean isExpired_;
  private final long timeout_;

  public PJTimer() {
    timeout_ = 0L;
  }

  public PJTimer(PJProcess process, long timeout) {
    process_ = process;
    timeout_ = timeout;
  }

  public void start() {
    delay_ = read() + timeout_;
    PJProcess.scheduler_.insert(this);
  }

  public synchronized void setExpired() {
    isExpired_ = true;
  }

  public synchronized boolean isExpired() {
    return isExpired_;
  }

  public static long read() {
    return System.currentTimeMillis();
  }

  public synchronized void setKilled() {
    isKilled_ = true;
  }

  public synchronized PJProcess getProcess() {
    return isKilled_ ? null : process_;
  }

  public long getDelay() {
    return delay_;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    final long diff = delay_ - read();
    return unit.convert(diff, TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    final PJTimer timer = (PJTimer) o;
    return Long.compare(delay_, timer.delay_);
  }
}
