package runtime;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a many-to-many channel.
 *
 * @author Ben
 */
public class PJMany2ManyChannel<T> extends PJOne2OneChannel<T> {

  // ***********************************************
  // Shared-read end.
  // ***********************************************

  protected PJProcess readerClaim_;
  protected Queue<PJProcess> readersQueue_;

  public PJMany2ManyChannel() {
    readersQueue_ = new LinkedList<>();
    writersQueue_ = new LinkedList<>();
    type_ = PJChannel.MANY2MANY;
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

  // ***********************************************
  // Shared-write end.
  // ***********************************************

  protected PJProcess writerClaim_;
  protected Queue<PJProcess> writersQueue_;

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
