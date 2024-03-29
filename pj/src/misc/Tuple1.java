package misc;

public class Tuple1<T1> extends Tuple {
  private T1 v1;

  public Tuple1(T1 v1) {
    super(v1);
    this.v1 = v1;
  }

  public Tuple1(Tuple1<T1> tuple) {
    this(tuple.v1);
  }

  @Override
  public boolean isTuple1() {
    return true;
  }

  @Override
  public Tuple1<T1> asTuple1() {
    return this;
  }

  public T1 getV1() {
    return v1;
  }

  @Override
  protected Tuple1<T1> clone() {
    return new Tuple1<>(this);
  }
}
