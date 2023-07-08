package scope;

import ast.Node;
import ast.types.ASTType;

/**
 * Base class for all symbols in ProcessJ.
 *
 * @author Ben
 */
public abstract class BaseSymbol implements Symbol {

  protected String name_;
  protected ASTType type_;
  protected Scope scope_;
  protected Node nodeRef_;

  public BaseSymbol(String name) {
    name_ = name;
  }

  @Override
  public String getName() {
    return name_;
  }

  @Override
  public Scope getScope() {
    return scope_;
  }

  @Override
  public void setScope(Scope scope) {
    scope_ = scope;
  }

  public ASTType getType() {
    return type_;
  }

  public void setType(ASTType type) {
    type_ = type;
  }

  public Node getNodeRef() {
    return nodeRef_;
  }

  public void setNodeRef(Node node) {
    nodeRef_ = node;
  }

  @Override
  public int hashCode() {
    return name_.hashCode() * 31 + nodeRef_.hashCode() * 31;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Symbol)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    BaseSymbol symbol = (BaseSymbol) obj;
    return name_.equals(symbol.getName()) &&
        nodeRef_ != null &&
        symbol.getNodeRef() != null &&
        nodeRef_.equals(symbol.getNodeRef());
  }
}
