package visitor;

/**
 * A simple implementation of the visitor pattern.
 *
 * @author Ben
 */
public interface Visitor {

  <T, A> T accept(GenericVisitor<T, A> v, A arg);

  <A> void accept(VoidVisitor<A> v, A arg);

  /**
   * Represents the visitor pattern from one of the original
   * implementations of the compiler.
   */
  <T> T accept(DefaultVisitor<T> v);
}
