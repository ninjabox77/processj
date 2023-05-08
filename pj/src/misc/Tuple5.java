package misc;

public class Tuple5<T1, T2, T3, T4, T5> extends Tuple {
  private T1 v1;
  private T2 v2;
  private T3 v3;
  private T4 v4;
  private T5 v5;

  public Tuple5(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5) {
    super(v1, v2, v3, v4, v5);
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.v5 = v5;
  }

  public Tuple5(Tuple5<T1, T2, T3, T4, T5> tuple) {
    this(tuple.v1, tuple.v2, tuple.v3, tuple.v4, tuple.v5);
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

  public T5 getV5() {
    return v5;
  }

  @Override
  protected Tuple5<T1, T2, T3, T4, T5> clone() {
    return new Tuple5<>(this);
  }
}
