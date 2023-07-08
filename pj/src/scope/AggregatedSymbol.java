package scope;

import ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all constructed types.
 *
 * @author Ben
 */
public abstract class AggregatedSymbol extends SymbolWithScope implements MemberSymbol {

  protected Node nodeRef_;

  public AggregatedSymbol(String name) {
    super(name);
  }

  public Node getNodeRef() {
    return nodeRef_;
  }

  public void setNodeRef(Node node) {
    nodeRef_ = node;
  }

  @Override
  public void addSymbolInScope(Symbol variable) {
    if (!(variable instanceof MemberSymbol)) {
      throw new IllegalArgumentException(String.format("%s is not a MemberSymbol", variable.getClass().getSimpleName()));
    }
    super.addSymbolInScope(variable);
  }

  @Override
  public List<MemberSymbol> getSymbols() {
    List<MemberSymbol> fields = new ArrayList<>();
    for (Symbol s : super.getSymbols()) {
      fields.add((MemberSymbol) s);
    }
    return fields;
  }

  @Override
  public boolean isType() {
    return true;
  }

  public Symbol lookUpMember(final String name) {
    Symbol variable = variables_.get(name);
    if (variable instanceof MemberSymbol) {
      return variable;
    }
    return null;
  }

  public Symbol lookUpField(final String name) {
    Symbol variable = lookUpMember(name);
    if (variable instanceof FieldSymbol) {
      return variable;
    }
    return null;
  }

  public int getNumberOfDefinedFields() {
    int count = 0;
    for (MemberSymbol m : getSymbols()) {
      if (m instanceof FieldSymbol) {
        ++count;
      }
    }
    return count;
  }

  public int getNumberOfFields() {
    return getNumberOfDefinedFields();
  }

  public List<? extends FieldSymbol> getDefinedFields() {
    List<FieldSymbol> fields = new ArrayList<>();
    for (MemberSymbol m : getSymbols()) {
      if (m instanceof FieldSymbol) {
        fields.add((FieldSymbol) m);
      }
    }
    return fields;
  }

  public List<? extends FieldSymbol> getFields() {
    return getDefinedFields();
  }

  public boolean isClassSymbol() {
    return false;
  }

  public ClassSymbol asClassSymbol() {
    throw new IllegalStateException(String.format("%s is not a ClassSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordSymbol() {
    return false;
  }

  public RecordSymbol asRecordSymbol() {
    throw new IllegalStateException(String.format("%s is not a RecordSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolSymbol() {
    return false;
  }

  public ProtocolSymbol asProtocolSymbol() {
    throw new IllegalStateException(String.format("%s is not a ProtocolSymbol, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isAggregatedSymbol() {
    return true;
  }
}
