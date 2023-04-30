package runtime;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a many-to-one channel.
 *
 * @author Ben
 */
public class PJMany2OneChannel<T> extends PJOne2OneChannel<T> {

  protected PJProcess writerClaim_;
  protected Queue<PJProcess> writersQueue_;

  public PJMany2OneChannel() {
    writersQueue_ = new LinkedList<>();
    type_ = PJChannel.MANY2ONE;
  }

  @Override
  public synchronized boolean claimWriter(PJProcess process) {
    if (writerClaim_ == null || writerClaim_ == process) {
      writerClaim_ = process;
      return true;
    }
    process.setNotReady();
    writersQueue_.add(process);
    return false;
  }

  @Override
  public synchronized void unclaimWriter() {
    if (writersQueue_.isEmpty()) {
      writerClaim_ = null;
    } else {
      writerClaim_ = writersQueue_.remove();
      writerClaim_.setReady();
    }
  }
}
