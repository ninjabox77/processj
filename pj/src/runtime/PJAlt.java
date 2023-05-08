package runtime;

import java.util.*;

/**
 * Represents an alt statement in ProcessJ.
 *
 * @author Ben
 */
public class PJAlt {

  public static final String SKIP = "skip";

  /**
   * A guard may be a skip, a timer, or any type of channel read.
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

  /**
   * Dynamic guards are only used in replicated alts.
   */
  private Map<Integer, PJAltGuard> dynamicAlts_;

  public PJAlt(PJProcess process) {
    process_ = process;
    declaredGuards_ = new ArrayList<>();
    declaredBooleanGuards_ = new ArrayList<>();
    dynamicAlts_ = Collections.emptyMap();
  }

  public PJAlt(int count, PJProcess process) {
    process_ = process;
    declaredGuards_ = new ArrayList<>(count);
    declaredBooleanGuards_ = new ArrayList<>(count);
    dynamicAlts_ = Collections.emptyMap();
  }

  public boolean setGuards(List<Object> declaredGuards, List<Boolean> declaredBooleanGuards) {
    declaredGuards_ = declaredGuards;
    declaredBooleanGuards_ = declaredBooleanGuards;

    return declaredBooleanGuards_.stream().anyMatch(bool -> bool);
  }

  public void setDynamicAlts(Map<Integer, PJAltGuard> dynamicAlts) {
    dynamicAlts_ = dynamicAlts;
  }

  public void put(Integer index, final PJAltGuard guard) {
    if (dynamicAlts_ == Collections.EMPTY_MAP) {
      dynamicAlts_ = HashMap.newHashMap(10);
    }
    dynamicAlts_.put(index, guard);
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
        final PJChannel<?> channel = (PJChannel<?>) declaredGuards_.get(i);
        if (channel.altGetWriter(process_) != null) {
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
        // No race condition should occur on this channel as it is a one-to-one
        // and only the process has access to it. This simply means that we are
        // de-registering from the channel for now, but may still read from it
        // if selected is not updated with a value less than j.
        final PJChannel<?> channel = (PJChannel<?>) declaredGuards_.get(j);
        if (channel.setReaderGetWriter(null) != null) {
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
