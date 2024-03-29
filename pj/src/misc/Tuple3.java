package misc;

public class Tuple3<T1, T2, T3> extends Tuple {
  private T1 v1;
  private T2 v2;
  private T3 v3;

  public Tuple3(T1 v1, T2 v2, T3 v3) {
    super(v1, v2, v3);
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
  }

  public Tuple3(Tuple3<T1, T2, T3> tuple) {
    this(tuple.v1, tuple.v2, tuple.v3);
  }

  @Override
  public boolean isTuple3() {
    return true;
  }

  @Override
  public Tuple3<T1, T2, T3> asTuple3() {
    return this;
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

  @Override
  protected Tuple3<T1, T2, T3> clone() {
    return new Tuple3<>(this);
  }
}
