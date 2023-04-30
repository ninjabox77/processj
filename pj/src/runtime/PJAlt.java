package runtime;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an alt statement in ProcessJ.
 *
 * @author Ben
 */
public class PJAlt {

  public static final String SKIP = "skip";

  /**
   * Guards may be a skip, a timer, or any type of channel read.
   */
  private List<Object> declaredGuards_;

  /**
   * These are expressions associated with the above declared guards.
   */
  private List<Boolean> declaredBooleanGuards_;

  /**
   * The process declaring the alt statement.
   */
  private final PJProcess process_;

  private List<PJAltGuard> dynamicAlts_;

  public PJAlt(PJProcess process) {
    process_ = process;
    declaredGuards_ = new ArrayList<>();
    declaredBooleanGuards_ = new ArrayList<>();
  }

  public PJAlt(int count, PJProcess process) {
    process_ = process;
    declaredGuards_ = new ArrayList<>(count);
    declaredBooleanGuards_ = new ArrayList<>(count);
  }

  public boolean setGuards(List<Object> declaredGuards, List<Boolean> declaredBooleanGuards) {
    declaredGuards_ = declaredGuards;
    declaredBooleanGuards_ = declaredBooleanGuards;

    return declaredBooleanGuards_.stream().anyMatch(boolanValue -> boolanValue);
  }

  public void setDynamicAlts(List<PJAltGuard> dynamicAlts) {
    dynamicAlts_ = dynamicAlts;
  }

  public PJAltGuard getDynamicAlts(int index) {
    return dynamicAlts_.get(index);
  }

  public int enable() {
    for (int i = 0; i < declaredGuards_.size(); ++i) {
      // If no boolean guard is ready then continue.
      if (!declaredBooleanGuards_.get(i)) {
        continue;
      }
      // A skip guard?
      if (declaredGuards_.get(i) == PJAlt.SKIP) {
        process_.setReady();
        return i;
      }
      // A channel read guard?
      if (declaredGuards_.get(i) instanceof PJChannel<?>) {
        final PJChannel<?> chan = (PJChannel<?>) declaredGuards_.get(i);
        if (chan.altGetWriter(process_) != null) {
          process_.setReady();
          return i;
        }
      }
      // A timer guard?
      if (declaredGuards_.get(i) instanceof PJTimer) {
        final PJTimer timer = (PJTimer) declaredGuards_.get(i);
        if (timer.getDelay() <= 0L) {
          process_.setReady();
          timer.setExpired();
          return i;
        } else {
          timer.start();
        }
      }
    }
    return -1;
  }

  public int disable(int i) {
    int selected = -1;
    if (i == -1) {
      i = declaredGuards_.size() - 1;
    }
    for (int j = i; j >= 0; --j) {
      // If no boolean guard is ready then continue.
      if (!declaredBooleanGuards_.get(j)) {
        continue;
      }
      // A skip guard?
      if (declaredGuards_.get(j) == PJAlt.SKIP) {
        selected = j;
      }
      // A channel guard?
      if (declaredGuards_.get(j) instanceof PJChannel<?>) {
        // No race condition on this channel as it is a one-to-one and
        // only the process has access to it. This simply means that we
        // are de-registering from the channel for now, but may still read
        // from it if selected is not updated with a value less than j.
        final PJChannel<?> chan = (PJChannel<?>) declaredGuards_.get(j);
        if (chan.setReaderGetWriter(null) != null) {
          selected = j;
        }
      }
      // A timer guard?
      if (declaredGuards_.get(j) instanceof PJTimer) {
        final PJTimer timer = (PJTimer) declaredGuards_.get(j);
        if (timer.isExpired()) {
          selected = j;
        } else {
          timer.setKilled();
        }
      }
    }
    return selected;
  }
}
