package runtime;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a one-to-many channel.
 *
 * @author Ben
 */
public class PJOne2ManyChannel<T> extends PJOne2OneChannel<T> {

  protected PJProcess readerClaim_;
  protected Queue<PJProcess> readersQueue_;

  public PJOne2ManyChannel() {
    readersQueue_ = new LinkedList<>();
    type_ = PJChannel.ONE2MANY;
  }

  @Override
  public synchronized boolean claimReader(PJProcess process) {
    if (readerClaim_ == null || readerClaim_ == process) {
      readerClaim_ = process;
      return true;
    }
    process.setNotReady();
    readersQueue_.add(process);
    return false;
  }

  @Override
  public synchronized void unclaimReader() {
    if (readersQueue_.isEmpty()) {
      readerClaim_ = null;
    } else {
      readerClaim_ = readersQueue_.remove();
      readerClaim_.setReady();
    }
  }
}
