package runtime;

import java.util.List;

/**
 * Represents an alt statement in ProcessJ.
 *
 * @author Ben
 */
public class PJAlt {

  /**
   * Guards may be a skip, a timer, or any type of channel read.
   */
  private List<Object> declaredGuards;

  /**
   * These are expressions associated with the above declared guards.
   */
  private List<Boolean> declaredBooleanGuards_;

  /**
   * The process declaring the alt statement.
   */
  private PJProcess process_;
}
