package runtime;

/**
 * Represents a one-to-one channel.
 *
 * @author Ben
 */
public class PJOne2OneChannel<T> extends PJChannel<T> {

  protected PJProcess writer_;
  protected PJProcess reader_;

  public PJOne2OneChannel() {
    type_ = PJChannel.ONE2ONE;
  }

  @Override
  public synchronized void write(PJProcess process, T data) {
    data_ = data;             // sets the data on this channel
    writer_ = process;        // registers the writer
    writer_.setNotReady();    // sets the writer not ready to run
    if (reader_ != null) {    // if a reader is presents
      reader_.setReady();     // set it ready to run
    }
  }

  @Override
  public synchronized T read(PJProcess process) {
    writer_.setReady();       // sets the writer ready to run
    writer_ = null;           // removes the writer's reference
    reader_ = null;           // removes the reader's reference
    return data_;             // returns the data in the channel
  }

  @Override
  public synchronized boolean isReadyToRead(PJProcess process) {
    if (writer_ != null) {    // if a writers is present
      return true;            // return true
    } else {                  // otherwise
      reader_ = process;      // register the process as the reader
      reader_.setNotReady();  // set the reader not ready to run
    }
    return false;
  }

  @Override
  public boolean isReadyToWrite() {
    return true;
  }

  @Override
  public T preReadRendezvous(PJProcess process) {
    T data = data_;
    data = null;
    return data_;
  }

  @Override
  public void postReadRendezvous(PJProcess process) {
    writer_.setReady();
    writer_ = null;
    reader_ = null;
  }

  @Override
  public synchronized PJProcess altGetWriter(PJProcess process) {
    if (writer_ == null) {    // if a writer is not present
      reader_ = process;      // register the process as the reader (alt) process
    }
    return writer_;           // return the writer (even if it is not present)
  }

  @Override
  public PJProcess setReaderGetWriter(PJProcess process) {
    reader_ = process;
    return writer_;
  }
}
