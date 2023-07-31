package ast.java;

import ast.Parameter;
import ast.Sequence;
import ast.stmt.BlockStatement;
import ast.types.ASTType;
import ast.types.PrimitiveNode;
import org.antlr.v4.runtime.Token;
import typesystem.VoidType;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a constructor declaration.
 *
 * @author Ben
 */
public class ConstructorDeclaration extends MethodDeclaration<ConstructorDeclaration> {

  public ConstructorDeclaration() {
    this(ACC_PUBLIC, new PrimitiveNode(new VoidType()), "<ini>");
  }

  public ConstructorDeclaration(final int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, new BlockStatement());
  }

  public ConstructorDeclaration(final int modifiers, ASTType type, final String name, BlockStatement body) {
    this(null, modifiers, type, name, null, body);
  }

  public ConstructorDeclaration(Token token, final int modifiers, ASTType type, final String name, Sequence<Parameter> parameters, BlockStatement body) {
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
