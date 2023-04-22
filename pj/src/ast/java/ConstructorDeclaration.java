package ast.java;

import ast.Parameter;
import ast.Sequence;
import ast.stmt.BlockStmt;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import typesystem.VoidType;

/**
 * Represents a constructor declaration.
 *
 * @author Ben
 */
public class ConstructorDeclaration extends MethodDeclaration<ConstructorDeclaration> {

  public ConstructorDeclaration() {
    this(ACC_PUBLIC, new VoidType(), "<ini>");
  }

  public ConstructorDeclaration(final int modifiers, Type type, final String name) {
    this(modifiers, type, name, new BlockStmt());
  }

  public ConstructorDeclaration(final int modifiers, Type type, final String name, BlockStmt body) {
    this(null, modifiers, type, name, null, body);
  }

  public ConstructorDeclaration(Token token, final int modifiers, Type type, final String name, Sequence<Parameter> parameters, BlockStmt body) {
    super(token, modifiers, type, name, parameters, body);
  }

  @Override
  public boolean isConstructorDeclaration() {
    return true;
  }

  @Override
  public ConstructorDeclaration asConstructorDeclaration() {
    return this;
  }
}
