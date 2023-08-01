package ast.toplevel;

import ast.Sequence;
import ast.Parameter;
import ast.stmt.BlockStatement;
import ast.types.NodeType;
import ast.types.ConstructedNode;
import org.antlr.v4.runtime.Token;
import scope.VariableScope;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents a procedure or a mobile declaration.
 *
 * @author Ben
 */
public class ProcedureDeclaration extends TopLevelDeclaration<ProcedureDeclaration> {

  private Sequence<Parameter> parameters_;
  private BlockStatement body_;
  private boolean yields_;
  protected VariableScope variableScope_;

  public ProcedureDeclaration() {
    this(null, null);
  }

  public ProcedureDeclaration(NodeType type, final String name) {
    this(ACC_PUBLIC, type, name);
  }

  public ProcedureDeclaration(int modifiers, NodeType type, final String name) {
    this(modifiers, type, name, null);
  }

  public ProcedureDeclaration(int modifiers, NodeType type, final String name, Sequence<ConstructedNode> implementedNames) {
    this(modifiers, type, name, implementedNames, new BlockStatement());
  }

  public ProcedureDeclaration(int modifiers, NodeType type, final String name, Sequence<ConstructedNode> implementedNames, BlockStatement body) {
    this(null, modifiers, type, name, implementedNames, null, body);
  }

  public ProcedureDeclaration(Token token, int modifiers, NodeType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<Parameter> parameters, BlockStatement body) {
    super(token, modifiers, type, name, implementedNames);
    setParameters(parameters);
    setBody(body);
  }

  public ProcedureDeclaration setVariableScope(VariableScope variableScope) {
    if (variableScope == variableScope_) {
      return this;
    }
    variableScope_ = variableScope;
    return this;
  }

  public VariableScope getVariableScope() {
    return variableScope_;
  }

  public ProcedureDeclaration setParameters(Sequence<Parameter> parameters) {
    if (parameters == parameters_) {
      return this;
    }
    if (parameters_ != null) {
      parameters_.setParentNode(null);
    }
    parameters_ = parameters;
    setAsParentNodeOf(parameters);
    variableScope_ = new VariableScope();
    parameters_.forEach(p -> variableScope_.putDeclaredVariable(p));
    return this;
  }

  public Sequence<Parameter> getParameters() {
    return parameters_;
  }

  public Parameter getParameter(int index) {
    Objects.checkIndex(index, parameters_.size());
    return parameters_.get(index);
  }

  public ProcedureDeclaration setBody(BlockStatement body) {
    if (body == body_) {
      return this;
    }
    if (body_ != null) {
      body_.setParentNode(null);
    }
    body_ = body;
    setAsParentNodeOf(body);
    return this;
  }

  public BlockStatement getBody() {
    return body_;
  }

  public boolean doesYield() {
    return yields_;
  }

  @Override
  public boolean isProcedureDeclaration() {
    return true;
  }

  @Override
  public ProcedureDeclaration asProcedureDeclaration() {
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
