package scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public final class Utils {

  private Utils() {
  }

  public static <T> List<T> filter(List<T> lst, Predicate<T> pred) {
    List<T> result = new ArrayList<>();
    if (lst != null) {
      for (T v : lst) {
        if (pred.test(v)) {
          result.add(v);
        }
      }
    }
    return result;
  }

  public static <T> List<T> filter(Collection<T> lst, Predicate<T> pred) {
    List<T> result = new ArrayList<>();
    if (lst != null) {
      for (T v : lst) {
        if (pred.test(v)) {
          result.add(v);
        }
      }
    }
    return result;
  }
}
