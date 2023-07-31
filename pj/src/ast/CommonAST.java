package ast;

import org.antlr.v4.runtime.Token;

import java.util.Objects;

/**
 * Base class for any concrete node. This class contains information
 * used in all nodes of the AST.
 *
 * @author Ben
 */
public abstract class CommonAST<N extends CommonAST<?>> implements NodeWithParent<N> {

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
   * The line of text that contains this token.
   */
  private String text_ = null;

  public CommonAST(Token token) {
    if (token != null) {
      line_ = token.getLine();
      start_ = token.getStartIndex();
      stop_ = token.getStopIndex();
      text_ = token.getText();
    }
  }

  public CommonAST(int line, int start, int stop, final String text) {
    setLine(line);
    setStart(start);
    setStop(stop);
    setText(text);
  }

  public String asString() {
    throw new UnsupportedOperationException(String.format("Text not implemented for %s", this.getClass().getSimpleName()));
  }

  public String getText() {
    return text_ != null ? text_ : String.format("Text not implemented for %s", this.getClass().getSimpleName());
  }

  @SuppressWarnings("unchecked")
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

  @SuppressWarnings("unchecked")
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

  @SuppressWarnings("unchecked")
  public N setStart(int start) {
    if (start == start_) {
      return (N) this;
    }
    start_ = start;
    return (N) this;
  }

  public int getStop() {
    return stop_;
  }

  @SuppressWarnings("unchecked")
  public N setStop(int stop) {
    if (stop == stop_) {
      return (N) this;
    }
    stop_ = stop;
    return (N) this;
  }

  @SuppressWarnings("unchecked")
  public N setSourcePositionFromNode(CommonAST<?> node) {
    start_ = node.getStart();
    stop_ = node.getStop();
    line_ = node.getLine();
    text_ = node.asString();
    return (N) this;
  }
}
