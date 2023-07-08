package ast;

import org.antlr.v4.runtime.Token;

import java.util.Objects;

/**
 * Base class for any concrete node. This class contains information
 * used in all nodes of the AST.
 *
 * @author Ben
 */
public abstract class ASTNode<N extends ASTNode<?>> implements NodeWithParent<N> {

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

  /**
   * The line of text that contains this token.
   */
  private String text_ = null;

  public ASTNode(Token token) {
    if (token != null) {
      line_ = token.getLine();
      start_ = token.getStartIndex();
      stop_ = token.getStopIndex();
      charPosition_ = token.getCharPositionInLine();
      text_ = token.getText();
    }
  }

  public ASTNode(int line, int start, int stop, int charPosition, final String text) {
    setLine(line);
    setStart(start);
    setStop(stop);
    setCharPosition(charPosition);
    setText(text);
  }

  public String getText() {
    return text_ != null ? text_ : String.format("Text not implemented for %s", this.getClass().getSimpleName());
  }

  public N setText(final String text) {
    if (Objects.equals(text, text_)) {
      return (N) this;
    }
    text_ = text;
    return (N) this;
  }

  public int getLine() {
    return line_;
  }

  public N setLine(int line) {
    if (line == line_) {
      return (N) this;
    }
    line_ = line;
    return (N) this;
  }

  public int getStart() {
    return start_;
  }

  public N setStart(int start) {
    if (start == start_) {
      return (N) this;
    }
    start_ = start;
    return (N) this;
  }

  public int getCharPosition() {
    return charPosition_;
  }

  public N setCharPosition(int charPosition) {
    if (charPosition == charPosition_) {
      return (N) this;
    }
    charPosition_ = charPosition;
    return (N) this;
  }

  public int getStop() {
    return stop_;
  }

  public N setStop(int stop) {
    if (stop == stop_) {
      return (N) this;
    }
    stop_ = stop;
    return (N) this;
  }

  public N setSourcePositionFromNode(ASTNode<?> node) {
    start_ = node.getStart();
    charPosition_ = node.getCharPosition();
    stop_ = node.getStop();
    line_ = node.getLine();
    text_ = node.getText();
    return (N) this;
  }
}
