package ast.expr;

import ast.AnnotatedNode;
import ast.Node;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;

/**
 * Base class for all expressions.
 *
 * @author Ben
 */
public abstract class Expression<E extends Expression<?>> extends AnnotatedNode {

  private ASTType type_;

  public Expression() {
    this(null);
  }

  public Expression(Token token) {
    super(token);
    customInitialization();
  }

  @Override
  public E setASTType(ASTType type) {
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

  @Override
  public ASTType getASTType() {
    return type_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == type_) {
      setASTType((ASTType) replaceWith);
      return true;
    }
    return false;
  }

  public boolean isBinaryExpr() {
    return false;
  }

  public BinaryExpr asBinaryExpr() {
    throw new IllegalStateException(String.format("%s is not a BinaryExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBooleanExpr() {
    return false;
  }

  public BooleanExpr asBooleanExpr() {
    throw new IllegalStateException(String.format("%s is not a BooleanExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCastExpr() {
    return false;
  }

  public CastExpr asCastExpr() {
    throw new IllegalStateException(String.format("%s is not a CastExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isConstantExpr() {
    return false;
  }

  public ConstantExpr asConstantExpr() {
    throw new IllegalStateException(String.format("%s is not a ConstantExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isEmptyExpr() {
    return false;
  }

  public EmptyExpr asEmptyExpr() {
    throw new IllegalStateException(String.format("%s is not an EmptyExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPrefixExpr() {
    return false;
  }

  public PrefixExpr asPrefixExpr() {
    throw new IllegalStateException(String.format("%s is not a PrefixExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPostfixExpr() {
    return false;
  }

  public PostfixExpr asPostfixExpr() {
    throw new IllegalStateException(String.format("%s is not a PostfixExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isTernaryExpr() {
    return false;
  }

  public TernaryExpr asTernaryExpr() {
    throw new IllegalStateException(String.format("%s is not a TernaryExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isSkipExpr() {
    return false;
  }

  public SkipExpr asSkipExpr() {
    throw new IllegalStateException(String.format("%s is not a SkipExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isStopExpr() {
    return false;
  }

  public StopExpr asStopExpr() {
    throw new IllegalStateException(String.format("%s is not a StopExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isCallableExpr() {
    return false;
  }

  public CallableExpr asCallableExpr() {
    throw new IllegalStateException(String.format("%s is not a CallableExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isVariableExpr() {
    return false;
  }

  public VariableExpr asVariableExpr() {
    throw new IllegalStateException(String.format("%s is not a VariableExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isDeclarationExpr() {
    return false;
  }

  public DeclarationExpr asDeclarationExpr() {
    throw new IllegalStateException(String.format("%s is not a DeclarationExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isUnaryMinusExpr() {
    return false;
  }

  public UnaryMinusExpr asUnaryMinusExpr() {
    throw new IllegalStateException(String.format("%s is not a UnaryMinusExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isUnaryPlusExpr() {
    return false;
  }

  public UnaryPlusExpr asUnaryPlusExpr() {
    throw new IllegalStateException(String.format("%s is not a UnaryPlusExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isFieldExpr() {
    return false;
  }

  public FieldExpr asFieldExpr() {
    throw new IllegalStateException(String.format("%s is not a FieldExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isClassExpr() {
    return false;
  }

  public ClassExpr asClassExpr() {
    throw new IllegalStateException(String.format("%s is not a ClassExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMapEntryExpr() {
    return false;
  }

  public MapEntryExpr asMapEntryExpr() {
    throw new IllegalStateException(String.format("%s is not a MapEntryExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMapExp() {
    return false;
  }

  public MapExpr asMapExpr() {
    throw new IllegalStateException(String.format("%s is not a MapExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMethodCallExpr() {
    return false;
  }

  public MethodCallExpr asMethodCallExpr() {
    throw new IllegalStateException(String.format("%s is not a MethodCallExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isRecordExpr() {
    return false;
  }

  public RecordExpr asRecordExpr() {
    throw new IllegalStateException(String.format("%s is not a RecordExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isProtocolExpr() {
    return false;
  }

  public ProtocolExpr asProtocolExpr() {
    throw new IllegalStateException(String.format("%s is not a ProtocolExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isListExpression() {
    return false;
  }

  public ListExpression<?> asListExpression() {
    throw new IllegalStateException(String.format("%s is not a ListExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isNewArrayExpr() {
    return false;
  }

  public NewArrayExpr asNewArrayExpr() {
    throw new IllegalStateException(String.format("%s is not a NewArrayExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isBlockExpr() {
    return false;
  }

  public BlockExpr asBlockExpr() {
    throw new IllegalStateException(String.format("%s is not a BlockExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelReadExpr() {
    return false;
  }

  public ChannelReadExpr asChannelReadExpr() {
    throw new IllegalStateException(String.format("%s is not a ChannelReadExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isChannelWriteExpr() {
    return false;
  }

  public ChannelWriteExpr asChannelWriteExpr() {
    throw new IllegalStateException(String.format("%s is not a ChannelWriteExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isPathExpression() {
    return false;
  }

  public PathExpression asPathExpression() {
    throw new IllegalStateException(String.format("%s is not a PathExpression, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isGroupExpr() {
    return false;
  }

  public GroupExpr asGroupExpr() {
    throw new IllegalStateException(String.format("%s is not a GroupExpr, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isArrayAccess() {
    return false;
  }

  public ArrayAccess asArrayAccess() {
    throw new IllegalStateException(String.format("%s is not an ArrayAccess, it is a %s", this, getClass().getSimpleName()));
  }
}
