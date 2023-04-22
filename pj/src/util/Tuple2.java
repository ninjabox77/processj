package util;

public class Tuple2<T1, T2> extends Tuple {
  private T1 v1;
  private T2 v2;

  public Tuple2(T1 v1, T2 v2) {
    super(v1, v2);
    this.v1 = v1;
    this.v2 = v2;
  }

  public Tuple2(Tuple2<T1, T2> tuple) {
    this(tuple.v1, tuple.v2);
  }

  public T1 getV1() {
    return v1;
  }

  public T2 getV2() {
    return v2;
  }

  @Override
  protected Tuple2<T1, T2> clone() {
    return new Tuple2<>(this);
  }
}