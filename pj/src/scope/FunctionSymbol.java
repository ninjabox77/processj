package scope;

import ast.Node;
import ast.types.ASTType;

/**
 * Represents a function.
 *
 * @author Ben
 */
public abstract class FunctionSymbol extends SymbolWithScope {

  protected Node nodeRef_;
  protected ASTType typer_;

  public FunctionSymbol(String name) {
    super(name);
  }

  public Node getNodeRef() {
    return nodeRef_;
  }

  public void setNodeRef(Node nodeRef) {
    nodeRef_ = nodeRef;
  }

  public int getNumberOfVariables() {
    return Utils.filter(variables_.values(), v -> v instanceof VariableSymbol).size();
  }

  public int getNumberOfParameters() {
    return Utils.filter(variables_.values(), v -> v instanceof ParameterSymbol).size();
  }

  public boolean isProcedureSymbol() {
    return false;
  }

  public ProcedureSymbol asProcedureSymbol() {
    throw new IllegalStateException(String.format("%s is not a ProcedureSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMethodSymbol() {
    return false;
  }

  public MethodSymbol asMethodSymbol() {
    throw new IllegalStateException(String.format("%s is not a MethodSymbol, it is a %s", this, getClass().getSimpleName()));
  }
}
