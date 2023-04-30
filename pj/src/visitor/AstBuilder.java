package visitor;

import parser.ProcessJBaseVisitor;
import parser.ProcessJParser;

/**
 * Builds the AST from the parse tree generated by antlr4.
 *
 * @author Ben
 */
public class AstBuilder extends ProcessJBaseVisitor<Object> {

  @Override
  public Object visitInit(ProcessJParser.InitContext ctx) {
    return super.visitInit(ctx);
  }
}