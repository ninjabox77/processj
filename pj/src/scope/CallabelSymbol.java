package scope;

import ast.Node;

/**
 * Base for all callabel symbols.
 *
 * @author Ben
 */
public class CallabelSymbol extends SymbolWithScope {

  protected Node node_;

  public CallabelSymbol(String name) {
    super(name);
  }

  public void setNode(Node node) {
    node_ = node;
  }

  public Node getNode() {
    return node_;
  }

  public int getNumberOfVariables() {
    return 0;
  }

  public int getNumberOfParams() {
    return 0;
  }
}
