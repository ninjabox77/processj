package util;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class Tuple<E> extends AbstractList<E> implements Cloneable, Comparable<Tuple<E>> {
  protected E[] contents;

  @SafeVarargs
  public Tuple(E... contents) {
    if (contents == null) {
      throw new NullPointerException();
    }
    this.contents = contents;
  }

  public Tuple(Tuple<E> tuple) {
    contents = tuple.contents;
  }

  private static <T> int compare(T t1, T t2) {
    return t1 == null && t2 == null
        ? 0
        : t1 == null
        ? 1
        : t2 == null
        ? -1
        : t1 instanceof Comparable
        ? ((Comparable) t1).compareTo(t2)
        : t1.getClass() == t2.getClass()
        ? 0
        : 1;
  }

  public static <T> Tuple0 tuple() {
    return Tuple0.INSTANCE;
  }

  public static <T1> Tuple1<T1> tuple(T1 v1) {
    return new Tuple1<>(v1);
  }

  public static <T1, T2> Tuple2<T1, T2> tuple(T1 v1, T2 v2) {
    return new Tuple2<>(v1, v2);
  }

  public static <T1, T2, T3> Tuple3<T1, T2, T3> tuple(T1 v1, T2 v2, T3 v3) {
    return new Tuple3<>(v1, v2, v3);
  }

  public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tuple(T1 v1, T2 v2, T3 v3, T4 v4) {
    return new Tuple4<>(v1, v2, v3, v4);
  }

  public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5) {
    return new Tuple5<>(v1, v2, v3, v4, v5);
  }

  public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6) {
    return new Tuple6<>(v1, v2, v3, v4, v5, v6);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7) {
    return new Tuple7<>(v1, v2, v3, v4, v5, v6, v7);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8) {
    return new Tuple8<>(v1, v2, v3, v4, v5, v6, v7, v8);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9) {
    return new Tuple9<>(v1, v2, v3, v4, v5, v6, v7, v8, v9);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10) {
    return new Tuple10<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11) {
    return new Tuple11<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11, T12 v12) {
    return new Tuple12<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11, T12 v12, T13 v13) {
    return new Tuple13<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11, T12 v12, T13 v13, T14 v14) {
    return new Tuple14<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> tuple(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11, T12 v12, T13 v13, T14 v14, T15 v15) {
    return new Tuple15<>(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15);
  }

  @Override
  public int compareTo(Tuple<E> that) {
    int thisSize = this.size();
    int thatSize = that.size();
    for (int i = 0, n = Math.min(thisSize, thatSize); i < n; ++i) {
      int result = compare(this.get(i), that.get(i));
      if (result != 0) {
        return result;
      }
    }
    return Integer.compare(thisSize, thatSize);
  }

  @Override
  public E get(int index) {
    return contents[index];
  }

  @Override
  public int size() {
    return contents.length;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    int size = toIndex - fromIndex;
    E[] newContents = (E[]) new Object[size];
    System.arraycopy(contents, fromIndex, newContents, 0, size);
    return new Tuple<>(newContents);
  }

  public Tuple<E> subTuple(int fromIndex, int toIndex) {
    return (Tuple<E>) subList(fromIndex, toIndex);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof Tuple<?>)) {
      return false;
    }
    Tuple<?> that = (Tuple<?>) object;
    int size = size();
    if (size != that.size()) {
      return false;
    }
    for (int i = 0; i < size; ++i) {
      if (!compareEqual(get(i), that.get(i))) {
        return false;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash((Object) contents);
  }

  @Override
  protected Tuple<E> clone() {
    return new Tuple<>(this);
  }

  private static boolean compareEqual(final Object left, final Object right) {
    if (left == right)
      return true;
    if (left != null && right != null) {
      Class<?> leftClass = left.getClass();
      if (leftClass == right.getClass())
        return left.equals(right);
    }
    return false;
  }
}
