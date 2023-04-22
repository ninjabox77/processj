package util;

import ast.ASTNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Objects;

/**
 * Utility for configuring a node position.
 *
 * @author Ben
 */
public class ConfigureNodeUtils {

  public static <N extends ASTNode<?>> N configureAST(N astNode, ParserRuleContext ctx) {
    Token start = ctx.getStart();
    Token stop = ctx.getStop();
    astNode.setLine(start.getLine());
    astNode.setCharPosition(stop.getCharPositionInLine() + 1);
//        positionEnd(astNode, stop);
    return astNode;
  }

  public static Tuple2<Integer, Integer> endPosition(Token token) {
    String text = token.getText();
    int stop = 0;
    int lineCount = 0;
    if (!Objects.isNull(text)) {
      stop = text.length();
      lineCount = (int) text.chars().filter(ch -> ch == '\n').count();
    }
    if (lineCount == 0) {
      return Tuple.tuple(token.getLine(), token.getCharPositionInLine() + 1 + token.getText().length());
    } else {
      return Tuple.tuple(token.getLine() + lineCount, stop - text.lastIndexOf('\n'));
    }
  }

  public static <N extends ASTNode<?>> N configureAST(N astNode, TerminalNode terminalNode) {
    return configureAST(astNode, terminalNode.getSymbol());
  }

  public static <N extends ASTNode<?>> N configureAST(N astNode, Token token) {
    astNode.setLine(token.getLine());
    astNode.setStart(token.getStartIndex());
    astNode.setStop(token.getStopIndex());
    astNode.setCharPosition(token.getCharPositionInLine() + 1);
    return astNode;
  }

  public static <N extends ASTNode<N>> N configureAST(N astNode, ASTNode<?> src) {
    astNode.setLine(src.getLine());
    astNode.setStart(src.getStart());
    astNode.setStop(src.getStop());
    astNode.setCharPosition(src.getCharPosition() + 1);
    return astNode;
  }

  public static <N extends ASTNode<?>> N configureEndPosition(N astNode, Token token) {
    Tuple2<Integer, Integer> endPosition = endPosition(token);
    // TODO:
    return astNode;
  }
}
