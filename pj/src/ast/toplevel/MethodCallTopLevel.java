package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStmt;
import org.antlr.v4.runtime.Token;
import typesystem.Type;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a procedure or a mobile declaration.
 *
 * @author Ben
 */
public class MethodCallTopLevel<T extends MethodCallTopLevel<?>> extends TopLevelDeclaration<T> {

  private Sequence<Parameter> parameters_;
  private BlockStmt body_;
  private boolean yields_;

  public MethodCallTopLevel(Token token, int modifiers, Type type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, Sequence<Parameter> parameters, BlockStmt body, final boolean yields) {
    super(token, modifiers, type, name, implementedNames);
    setParameters(parameters);
    setBody(body);
    setYields(yields);
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

  public T setYields(final boolean yields) {
    if (yields == yields_) {
      return (T) this;
    }
    yields_ = yields;
    return (T) this;
  }

  public boolean doesYield() {
    return yields_;
  }

  public boolean isProcedureTopLevel() {
    return false;
  }

  public ProcedureTopLevel asProcedureTopLevel() {
    throw new IllegalStateException(String.format("%s is not a ProcedureDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  public boolean isMobileDeclaration() {
    return false;
  }

  public MobileDeclaration asMobileDeclaration() {
    throw new IllegalStateException(String.format("%s is not a MobileDeclaration, it is a %s", this, getClass().getSimpleName()));
  }

  @Override
  public boolean isMethodCallTopLevel() {
    return true;
  }

  @Override
  public MethodCallTopLevel<?> asMethodCallTopLevel() {
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
