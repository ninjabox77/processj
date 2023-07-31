package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStatement;
import ast.types.ASTType;
import ast.types.ConstructedNode;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure declaration.
 *
 * @author Ben
 */
public class ProcedureTopLevel extends CallableDeclaration<ProcedureTopLevel> {

  private boolean yields_;

  public ProcedureTopLevel() {
    this(null, null, false);
  }

  public ProcedureTopLevel(ASTType type, final String name, final boolean yields) {
    this(ACC_PUBLIC, type, name, yields);
  }

  public ProcedureTopLevel(final int modifiers, ASTType type, final String name, final boolean yields) {
    this(modifiers, type, name, null, yields);
  }

  public ProcedureTopLevel(final int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, final boolean yields) {
    this(modifiers, type, name, implementedNames, new BlockStatement(), yields);
  }

  public ProcedureTopLevel(final int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, BlockStatement body, final boolean yields) {
    this(null, modifiers, type, name, implementedNames, null, body, yields);
  }

  public ProcedureTopLevel(Token token, final int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<Parameter> parameters, BlockStatement body, final boolean yields) {
    super(token, modifiers, type, name, implementedNames, parameters, body);
    setYields(yields);
  }

  public ProcedureTopLevel setYields(final boolean yields) {
    if (yields == yields_) {
      return this;
    }
    yields_ = yields;
    return this;
  }

  public boolean doesYield() {
    return yields_;
  }

  @Override
  public boolean isProcedureTopLevel() {
    return true;
  }

  @Override
  public ProcedureTopLevel asProcedureTopLevel() {
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
