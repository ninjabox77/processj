package runtime;

/**
 * Represents a channel in ProcessJ.
 *
 * @author Ben
 */
public class PJChannel<T> {

  public static final int ONE2ONE = 0x0001;
  public static final int ONE2MANY = 0x0002;
  public static final int MANY2ONE = 0x0004;
  public static final int MANY2MANY = 0x0008;

  protected T data_;
  protected int type_;

  public boolean isOne2One() {
    return (type_ & ONE2ONE) != 0;
  }

  public boolean isOne2Many() {
    return (type_ & ONE2MANY) != 0;
  }

  public boolean isMany2One() {
    return (type_ & MANY2ONE) != 0;
  }

  public boolean isMany2Many() {
    return (type_ & MANY2MANY) != 0;
  }

  // *****************************************************************
  // The methods below must be defined by the appropriate subclass.
  // *****************************************************************

  /**
   * One-2-One Channel
   */
  public synchronized void write(PJProcess process, T data) { }

  public synchronized T read(PJProcess process) {
    return null;
  }

  public synchronized boolean isReadyToRead(PJProcess process) {
    return false;
  }

  public boolean isReadyToWrite() {
    return false;
  }

  public T preReadRendezvous(PJProcess process) {
    return null;
  }

  public void postReadRendezvous(PJProcess process) { }

  /**
   * One-2-Many Channel: shared-read end.
   */
  public synchronized boolean claimReader(PJProcess process) {
    return false;
  }

  public synchronized void unclaimReader() { }

  /**
   * Many-2-One Channel: shared-read end.
   */
  public synchronized boolean claimWriter(PJProcess process) {
    return false;
  }

  public synchronized void unclaimWriter() { }

  /**
   * Alts (short for alternatives)
   */
  public synchronized PJProcess altGetWriter(PJProcess process) {
    return null;
  }

  public PJProcess setReaderGetWriter(PJProcess process) {
    return null;
  }
}
