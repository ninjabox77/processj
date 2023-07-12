package misc;

public class Tuple0 extends Tuple {
  public static final Tuple0 INSTANCE = new Tuple0();

  private Tuple0() {
  }

  @Override
  public boolean isTuple0() {
    return true;
  }

  @Override
  public Tuple0 asTuple0() {
    return this;
  }

  @Override
  protected Tuple0 clone() {
    return INSTANCE;
  }
}
