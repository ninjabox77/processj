package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStmt;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure or a mobile declaration.
 *
 * @author Ben
 */
public abstract class CallableDecl<T extends CallableDecl<?>> extends TopLevelDeclaration<T> {

  private Sequence<Parameter> parameters_;
  private BlockStmt body_;
  private boolean yields_;

  public CallableDecl() {
    this(null, null);
  }

  public CallableDecl(ASTType type, final String name) {
    this(ACC_PUBLIC, type, name);
  }

  public CallableDecl(int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public CallableDecl(int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames) {
    this(modifiers, type, name, implementedNames, new BlockStmt());
  }

  public CallableDecl(int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, BlockStmt body) {
    this(null, modifiers, type, name, implementedNames, null, body);
  }

  public CallableDecl(Token token, int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, Sequence<Parameter> parameters, BlockStmt body) {
    super(token, modifiers, type, name, implementedNames);
    setParameters(parameters);
    setBody(body);
  }

  public T setParameters(Sequence<Parameter> parameters) {
    if (parameters == parameters_) {
      return (T) this;
    }
    if (parameters_ != null) {
      parameters_.setParentNode(null);
    }
    parameters_ = parameters;
    setAsParentNodeOf(parameters);
    return (T) this;
  }

  public Sequence<Parameter> getParameters() {
    return parameters_;
  }

  public T setBody(BlockStmt body) {
    if (body == body_) {
      return (T) this;
    }
    if (body_ != null) {
      body_.setParentNode(null);
    }
    body_ = body;
    setAsParentNodeOf(body);
    return (T) this;
  }

  public BlockStmt getBody() {
    return body_;
  }

  public boolean isProcedureTopLevel() {
    return false;
  }

  public ProcedureTopLevel asProcedureTopLevel() {
    throw new IllegalStateException(String.format("%s is not a ProcedureDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isCallableDecl() {
    return true;
  }

  @Override
  public CallableDecl<?> asCallableDecl() {
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
