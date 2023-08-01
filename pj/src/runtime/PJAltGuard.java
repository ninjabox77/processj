package runtime;

import java.util.Arrays;

/**
 * Represents a guard in a replicated alt statement.
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
  private final int size_;

  /**
   * A set of indices that belong to a replicated alt statement.
   */
  private final Integer[] indices_;

  /**
   * The current selected index.
   */
  private int nextIndex_;

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

  public int nextIndex() {
    if (nextIndex_ < 0 || nextIndex_ >= indices_.length) {
      throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + (indices_.length - 1));
    }
    return indices_[nextIndex_++];
  }

  public int getSize() {
    return size_;
  }

  public int getAltCase() {
    return altCase_;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && getClass() != obj.getClass()) {
      return false;
    }
    PJAltGuard other = (PJAltGuard) obj;
    return altCase_ == other.altCase_ && size_ == other.size_ &&
        Arrays.deepEquals(indices_, other.indices_);
  }

  @Override
  public int hashCode() {
    final int hash = 31;
    return altCase_ * hash + size_ * hash + Arrays.deepHashCode(indices_) * hash;
  }
}
