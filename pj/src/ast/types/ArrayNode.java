package ast.types;

import ast.SourceAST;
import org.antlr.v4.runtime.Token;
import typesystem.ArrayType;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents an array type in the AST.
 */
public class ArrayNode extends NodeType {

  /**
   * Used to determine whether brackets appear next to the type
   * or next to a variable declaration with [] on; for example:
   * - int[] a;
   * - int[][] a;
   * - int a[];
   * - int a[][];
   * - int[] a[];
   */
  public enum BracketPosition {
    NAME,
    TYPE;
  }

  private BracketPosition position_;
  private ArrayType arrayType_;

  public ArrayNode() {
    this(null);
  }

  public ArrayNode(Type arrayType) {
    this(arrayType, BracketPosition.TYPE);
  }

  public ArrayNode(Type arrayType, BracketPosition position) {
    this(null, arrayType, position);
  }

  public ArrayNode(Token token, Type arrayType, BracketPosition position) {
    super(token);
    setTSType(arrayType);
    setBracketPosition(position);
  }

  @Override
  public ArrayNode setTSType(Type arrayType) {
    if (arrayType == arrayType_) {
      return this;
    }
    if (arrayType_ != null) {
      arrayType_.setParentNode(null);
    }
    arrayType_ = arrayType.asConstructedType().asArrayType();
    setAsParentNodeOf(arrayType);
    return this;
  }

  @Override
  public ArrayType getTSType() {
    return arrayType_;
  }

  public ArrayNode setBracketPosition(BracketPosition origin) {
    if (origin == position_) {
      return this;
    }
    position_ = origin;
    return this;
  }

  public BracketPosition getBracketPosition() {
    return position_;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == arrayType_) {
      setTSType((ArrayType) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isArrayNode() {
    return true;
  }

  @Override
  public ArrayNode asArrayNode() {
    return this;
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
