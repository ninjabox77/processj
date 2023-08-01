package ast.expr;

import ast.AnnotatedNode;
import ast.SourceAST;
import ast.types.NodeType;
import org.antlr.v4.runtime.Token;

/**
 * Base class for all expressions.
 *
 * @author Ben
 */
public abstract class Expression<E extends Expression<?>> extends AnnotatedNode {

  private NodeType type_;

  public Expression() {
    this(null);
  }

  public Expression(Token token) {
    super(token);
    customInitialization();
  }

  public E setNodeType(NodeType type) {
    if (type == type_) {
      return (E) this;
    }
    if (type_ != null) {
      type_.setParentNode(null);
    }
    type_ = type;
    setAsParentNodeOf(type);
    return (E) this;
  }

  public NodeType getNodeType() {
    return type_;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setNodeType((NodeType) replaceWith);
      return true;
    }
    return false;
  }

  public boolean isBinaryExpression() {
    return false;
  }

  public BinaryExpression asBinaryExpression() {
    throw new IllegalStateException(String.format("%s is not a BinaryExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBooleanExpression() {
    return false;
  }

  public BooleanExpression asBooleanExpression() {
    throw new IllegalStateException(String.format("%s is not a BooleanExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCastExpression() {
    return false;
  }

  public CastExpression asCastExpression() {
    throw new IllegalStateException(String.format("%s is not a CastExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isConstantExpression() {
    return false;
  }

  public ConstantExpression asConstantExpression() {
    throw new IllegalStateException(String.format("%s is not a ConstantExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isEmptyExpression() {
    return false;
  }

  public EmptyExpression asEmptyExpression() {
    throw new IllegalStateException(String.format("%s is not a EmptyExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPrefixExpression() {
    return false;
  }

  public PrefixExpression asPrefixExpression() {
    throw new IllegalStateException(String.format("%s is not a PrefixExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPostfixExpression() {
    return false;
  }

  public PostfixExpression asPostfixExpression() {
    throw new IllegalStateException(String.format("%s is not a PostfixExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTernaryExpression() {
    return false;
  }

  public TernaryExpression asTernaryExpression() {
    throw new IllegalStateException(String.format("%s is not a TernaryExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isSkipExpression() {
    return false;
  }

  public SkipExpression asSkipExpression() {
    throw new IllegalStateException(String.format("%s is not a SkipExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isStopExpression() {
    return false;
  }

  public StopExpression asStopExpression() {
    throw new IllegalStateException(String.format("%s is not a StopExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCallableExpression() {
    return false;
  }

  public CallabelExpression asCallableExpression() {
    throw new IllegalStateException(String.format("%s is not a CallableExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isVariableExpression() {
    return false;
  }

  public VariableExpression asVariableExpression() {
    throw new IllegalStateException(String.format("%s is not a VariableExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDeclarationExpression() {
    return false;
  }

  public DeclarationExpression asDeclarationExpression() {
    throw new IllegalStateException(String.format("%s is not a DeclarationExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isUnaryMinusExpression() {
    return false;
  }

  public UnaryMinusExpression asUnaryMinusExpression() {
    throw new IllegalStateException(String.format("%s is not a UnaryMinusExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isUnaryPlusExpression() {
    return false;
  }

  public UnaryPlusExpression asUnaryPlusExpression() {
    throw new IllegalStateException(String.format("%s is not a UnaryPlusExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isFieldExpression() {
    return false;
  }

  public FieldExpression asFieldExpression() {
    throw new IllegalStateException(String.format("%s is not a FieldExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isClassExpression() {
    return false;
  }

  public ClassExpression asClassExpression() {
    throw new IllegalStateException(String.format("%s is not a ClassExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMapEntryExpression() {
    return false;
  }

  public MapEntryExpression asMapEntryExpression() {
    throw new IllegalStateException(String.format("%s is not a MapEntryExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMapExpression() {
    return false;
  }

  public MapExpression asMapExpression() {
    throw new IllegalStateException(String.format("%s is not a MapExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMethodCallExpression() {
    return false;
  }

  public MethodCallExpression asMethodCallExpression() {
    throw new IllegalStateException(String.format("%s is not a MethodCallExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isListExpression() {
    return false;
  }

  public ListExpression<?> asListExpression() {
    throw new IllegalStateException(String.format("%s is not a ListExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNewArrayExpression() {
    return false;
  }

  public NewArrayExpression asNewArrayExpression() {
    throw new IllegalStateException(String.format("%s is not a NewArrayExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBlockExpression() {
    return false;
  }

  public BlockExpression asBlockExpression() {
    throw new IllegalStateException(String.format("%s is not a BlockExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isGroupExpression() {
    return false;
  }

  public GroupExpression asGroupExpression() {
    throw new IllegalStateException(String.format("%s is not a GroupExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isArrayAccess() {
    return false;
  }

  public ArrayAccess asArrayAccess() {
    throw new IllegalStateException(String.format("%s is not an ArrayAccess, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordLiteral() {
    return false;
  }

  public RecordLiteral asRecordLiteral() {
    throw new IllegalStateException(String.format("%s is not a RecordLiteral, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolLiteral() {
    return false;
  }

  public ProtocolLiteral asProtocolLiteral() {
    throw new IllegalStateException(String.format("%s is not a ProtocolLiteral, it is a %s", this, getClass().getSimpleName()));
  }
}
