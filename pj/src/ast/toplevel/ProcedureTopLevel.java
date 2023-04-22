package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStmt;
import org.antlr.v4.runtime.Token;
import typesystem.Type;

/**
 * Represents a procedure declaration.
 *
 * @author Ben
 */
public class ProcedureTopLevel extends MethodCallTopLevel<ProcedureTopLevel> {

  public ProcedureTopLevel() {
    this(null, null, false);
  }

  public ProcedureTopLevel(Type type, final String name, final boolean yields) {
    this(ACC_PUBLIC, type, name, yields);
  }

  public ProcedureTopLevel(final int modifiers, Type type, final String name, final boolean yields) {
    this(modifiers, type, name, null, yields);
  }

  public ProcedureTopLevel(final int modifiers, Type type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, final boolean yields) {
    this(modifiers, type, name, implementedNames, new BlockStmt(), yields);
  }

  public ProcedureTopLevel(final int modifiers, Type type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, BlockStmt body, final boolean yields) {
    this(null, modifiers, type, name, implementedNames, null, body, yields);
  }

  public ProcedureTopLevel(Token token, final int modifiers, Type type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, Sequence<Parameter> parameters, BlockStmt body, final boolean yields) {
    super(token, modifiers, type, name, implementedNames, parameters, body, yields);
  }

  @Override
  public boolean isProcedureTopLevel() {
    return true;
  }

  @Override
  public ProcedureTopLevel asProcedureTopLevel() {
    return this;
  }
}