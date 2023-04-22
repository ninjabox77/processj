package ast;

import org.antlr.v4.runtime.Token;

/**
 * Base class for any concrete node. This class contains information
 * used in all nodes of the AST.
 *
 * @author Ben
 */
public abstract class ASTNode<N extends ASTNode<?>> implements ParentNode<N> {

  /**
   * The line number on which the token appears.
   */
  private int line_ = -1;
  /**
   * The starting character index of the token.
   */
  private int start_ = -1;
  /**
   * The last character index of the token.
   */
  private int stop_ = -1;
  /**
   * The index of the first character of this token relative to the line.
   */
  private int charPosition_ = -1;

  public ASTNode(Token token) {
    if (token != null) {
      line_ = token.getLine();
      start_ = token.getStartIndex();
      stop_ = token.getStopIndex();
      charPosition_ = token.getCharPositionInLine();
    }
  }

  public ASTNode(int line, int start, int stop, int charPosition) {
    setLine(line);
    setStart(start);
    setStop(stop);
    setCharPosition(charPosition);
  }

  public String getText() {
    return "<not implemented for class: " + this.getClass().getSimpleName() + ">";
  }

  public int getLine() {
    return line_;
  }

  public N setLine(int line) {
    line_ = line;
    return (N) this;
  }

  public int getStart() {
    return start_;
  }

  public N setStart(int start) {
    start_ = start;
    return (N) this;
  }

  public int getCharPosition() {
    return charPosition_;
  }

  public N setCharPosition(int charPosition) {
    charPosition_ = charPosition;
    return (N) this;
  }

  public int getStop() {
    return stop_;
  }

  public N setStop(int stop) {
    stop_ = stop;
    return (N) this;
  }

  public N setSourcePositionFromNode(ASTNode<?> node) {
    start_ = node.getStart();
    charPosition_ = node.getCharPosition();
    stop_ = node.getStop();
    line_ = node.getLine();
    return (N) this;
  }
}
