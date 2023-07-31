package misc;

import ast.CommonAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Objects;

/**
 * Utility for configuring a node position.
 */
public class ConfigureAST {

  public static <N extends CommonAST<?>> N configureNode(N astNode, ParserRuleContext ctx) {
    Token start = ctx.getStart();
    Token stop = ctx.getStop();
    astNode.setLine(start.getLine());
    astNode.setStart(start.getCharPositionInLine() + 1);
    astNode.setStop(endPosition(stop));
    return astNode;
  }

  // this is to take care of multi-line comments in ProcessJ.
  public static int endPosition(Token token) {
    String text = token.getText();
    int stop = 0;
    int lineCount = 0;
    if (!Objects.isNull(text)) {
      stop = text.length();
      lineCount = (int) text.chars().filter(ch -> ch == '\n').count();
    }
    if (lineCount == 0) {
      stop = token.getCharPositionInLine() + 1 + token.getText().length();
    } else {
      stop = stop - text.lastIndexOf('\n');
    }
    return stop;
  }

  public static <N extends CommonAST<?>> N configureNode(N astNode, TerminalNode terminalNode) {
    return configureNode(astNode, terminalNode.getSymbol());
  }

  public static <N extends CommonAST<?>> N configureNode(N astNode, Token token) {
    astNode.setLine(token.getLine());
    astNode.setStart(token.getCharPositionInLine() + 1);
    astNode.setStop(token.getCharPositionInLine() + 1 + token.getText().length());
    return astNode;
  }

  public static <N extends CommonAST<?>> N configureNode(N astNode, CommonAST<?> src) {
    astNode.setLine(src.getLine());
    astNode.setStart(src.getStart());
    astNode.setStop(src.getStop());
    return astNode;
  }
}
