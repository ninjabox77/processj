package misc;

public class Tuple0 extends Tuple {
  public static final Tuple0 INSTANCE = new Tuple0();

  private Tuple0() {
  }

  @Override
  protected Tuple0 clone() {
    return INSTANCE;
  }
}
