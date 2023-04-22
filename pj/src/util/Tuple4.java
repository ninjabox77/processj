package util;

public class Tuple4<T1, T2, T3, T4> extends Tuple {
  private T1 v1;
  private T2 v2;
  private T3 v3;
  private T4 v4;

  public Tuple4(T1 v1, T2 v2, T3 v3, T4 v4) {
    super(v1, v2, v3, v4);
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
  }

  public Tuple4(Tuple4<T1, T2, T3, T4> tuple) {
    this(tuple.v1, tuple.v2, tuple.v3, tuple.v4);
  }

  public T1 getV1() {
    return v1;
  }

  public T2 getV2() {
    return v2;
  }

  public T3 getV3() {
    return v3;
  }

  public T4 getV4() {
    return v4;
  }

  @Override
  protected Tuple4<T1, T2, T3, T4> clone() {
    return new Tuple4<>(this);
  }
}
