package runtime;

import java.util.Arrays;

/**
 * Represents a guard in an alt statement.
 *
 * @author Ben
 */
public class PJAltGuard {

  /**
   * Case number.
   */
  private int altCase_;

  /**
   * Number of elements in the set.
   */
  private int size_;

  /**
   * A set of indices that belong to a replicated alt statement.
   */
  private Integer[] indices_;

  /**
   * The current selected index.
   */
  private int selectedIndex_;

  public PJAltGuard(int size) {
    size_ = size;
    indices_ = new Integer[size];
  }

  public void addCaseNumber(int altCase) {
    altCase_ = altCase;
  }

  public void addIndex(Integer... numbers) {
    int index = 0;
    for (Integer n : numbers) {
      indices_[index++] = n;
    }
  }

  public int getIndex(int index) {
    return indices_[index];
  }

  public int getIndex() {
    return indices_[selectedIndex_++];
  }

  public int getSize() {
    return size_;
  }

  public int getAltCase() {
    return altCase_;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PJAltGuard other = (PJAltGuard) obj;
    return altCase_ == other.altCase_ && size_ == other.size_ &&
        Arrays.deepEquals(indices_, other.indices_);
  }

  @Override
  public int hashCode() {
    return altCase_ * 31 + size_ * 31 + Arrays.deepHashCode(indices_) * 31;
  }
}
