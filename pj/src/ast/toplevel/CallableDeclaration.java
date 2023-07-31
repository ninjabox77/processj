package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStatement;
import ast.types.ASTType;
import ast.types.ConstructedNode;
import org.antlr.v4.runtime.Token;
import scope.VariableScope;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure or a mobile declaration.
 *
 * @author Ben
 */
public abstract class CallableDeclaration<T extends CallableDeclaration<?>> extends TopLevelDeclaration<T> {

  private Sequence<Parameter> parameters_;
  private BlockStatement body_;
  private boolean yields_;
  protected VariableScope variableScope_;

  public CallableDeclaration() {
    this(null, null);
  }

  public CallableDeclaration(ASTType type, final String name) {
    this(ACC_PUBLIC, type, name);
  }

  public CallableDeclaration(int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public CallableDeclaration(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames) {
    this(modifiers, type, name, implementedNames, new BlockStatement());
  }

  public CallableDeclaration(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, BlockStatement body) {
    this(null, modifiers, type, name, implementedNames, null, body);
  }

  public CallableDeclaration(Token token, int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<Parameter> parameters, BlockStatement body) {
    super(token, modifiers, type, name, implementedNames);
    setParameters(parameters);
    setBody(body);
  }

  @SuppressWarnings("unchecked")
  public T setVariableScope(VariableScope variableScope) {
    if (variableScope == variableScope_) {
      return (T) this;
    }
    variableScope_ = variableScope;
    return (T) this;
  }

  public VariableScope getVariableScope() {
    return variableScope_;
  }

  @SuppressWarnings("unchecked")
  public T setParameters(Sequence<Parameter> parameters) {
    if (parameters == parameters_) {
      return (T) this;
    }
    if (parameters_ != null) {
      parameters_.setParentNode(null);
    }
    parameters_ = parameters;
    setAsParentNodeOf(parameters);
    variableScope_ = new VariableScope();
    parameters_.forEach(p -> variableScope_.putDeclaredVariable(p));
    return (T) this;
  }

  public Sequence<Parameter> getParameters() {
    return parameters_;
  }

  @SuppressWarnings("unchecked")
  public T setBody(BlockStatement body) {
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

  public BlockStatement getBody() {
    return body_;
  }

  public boolean isProcedureTopLevel() {
    return false;
  }

  public ProcedureTopLevel asProcedureTopLevel() {
    throw new IllegalStateException(String.format("%s is not a ProcedureDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isCallableDeclaration() {
    return true;
  }

  @Override
  public CallableDeclaration<?> asCallableDeclaration() {
    return this;
  }

  @Override
  public <V, A> V accept(GenericVisitor<V, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <V> V accept(DefaultVisitor<V> v) {
    return v.visit(this);
  }
}
