package ast;

import ast.expr.Expression;
import ast.expr.TupleExpression;
import ast.toplevel.ProcedureDeclaration;
import ast.toplevel.TopLevelDeclaration;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents the contents of a single source file consisting of one
 * package, one or more imports, and one or more top-level declarations.
 *
 * @author Ben
 */
public class Compilation extends AnnotatedNode {

  private String file_;
  private Package package_;
  private Sequence<Import> imports_;
  private Sequence<TopLevelDeclaration<?>> typeDeclarations_;

  public Compilation(final String file) {
    setFile(file);
  }

  public Compilation() {
    this(null, null, null);
  }

  public Compilation(Package packet, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    this(null, packet, imports, typeDeclarations);
  }

  public Compilation(Token token, Package packet, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    super(token);
    setPackage(packet);
    setImports(imports);
    setTypeDeclarations(typeDeclarations);
  }

  public void setFile(final String file) {
    file_ = file;
  }

  public String getFile() {
    return file_;
  }

  public Compilation setPackage(Package packet) {
    if (packet == package_) {
      return this;
    }
    if (package_ != null) {
      package_.setParentNode(null);
    }
    package_ = packet;
    setAsParentNodeOf(packet);
    return this;
  }

  public Package getPackage() {
    return package_;
  }

  public Compilation setImports(Sequence<Import> imports) {
    if (imports == imports_) {
      return this;
    }
    if (imports_ != null) {
      imports_.setParentNode(null);
    }
    imports_ = imports;
    setAsParentNodeOf(imports);
    return this;
  }

  public Optional<Sequence<Import>> getImports() {
    return Optional.ofNullable(imports_);
  }

  public Compilation setTypeDeclarations(Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    if (typeDeclarations == typeDeclarations_) {
      return this;
    }
    if (typeDeclarations_ != null) {
      typeDeclarations_.setParentNode(null);
    }
    typeDeclarations_ = typeDeclarations;
    setAsParentNodeOf(typeDeclarations);
    return this;
  }

  public Optional<Sequence<TopLevelDeclaration<?>>> getTypeDeclarations() {
    return Optional.ofNullable(typeDeclarations_);
  }

  public boolean hasPossibleProcedure(final String name, final Expression<?> arguments) {
    int count = 0;
    if (arguments.isListExpression()) {
      count = ((TupleExpression) arguments).getValues().size();
    }
    for (TopLevelDeclaration<?> decl : typeDeclarations_) {
      if (decl.isProcedureDeclaration()) {
        ProcedureDeclaration procedure = decl.asProcedureDeclaration();
        if (procedure.getIdentifier().equals(name) && hasCompatibleNumberOfArguments(procedure, count)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean hasCompatibleType(TupleExpression args, ProcedureDeclaration procedure, int idx) {
    int lastParamIndex = procedure.getParameters().size() - 1;
    return (idx <= lastParamIndex && args.getValue(idx).getASTType().equals(procedure.getParameter(idx).getASTType()))
        || (idx >= lastParamIndex && isPossibleVarArgs(procedure, lastParamIndex) &&
        (args.getValue(idx).getASTType().getType().typeEqual(procedure.getParameter(lastParamIndex).getASTType().getType())
            || args.getValue(idx).getASTType().getType().typeEquivalent(procedure.getParameter(lastParamIndex).getASTType().getType())
            || args.getValue(idx).getASTType().getType().typeAssignmentCompatible(procedure.getParameter(lastParamIndex).getASTType().getType())));
  }

  public boolean hasCompatibleNumberOfArguments(ProcedureDeclaration procedure, final int count) {
    int lastParamIndex = procedure.getParameters().size() - 1;
    return count == procedure.getParameters().size() || (count >= lastParamIndex && isPossibleVarArgs(procedure, lastParamIndex));
  }

  public boolean isPossibleVarArgs(ProcedureDeclaration fd, final int lastParamIndex) {
    return lastParamIndex >= 0 && fd.getParameter(lastParamIndex).getASTType().isArrayNode();
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == package_) {
      setPackage((Package) replaceWith);
      return true;
    }
    if (imports_ != null) {
      for (int i = 0; i < imports_.size(); ++i) {
        if (node == imports_.get(i)) {
          imports_.set(i, (Import) replaceWith);
        }
      }
    }
    if (typeDeclarations_ != null) {
      for (int i = 0; i < typeDeclarations_.size(); ++i) {
        if (node == typeDeclarations_.get(i)) {
          typeDeclarations_.set(i, (TopLevelDeclaration<?>) replaceWith);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (node == package_) {
      setPackage(null);
      return true;
    }
    if (imports_ != null) {
      for (int i = 0; i < imports_.size(); ++i) {
        if (node == imports_.get(i)) {
          imports_.remove(i);
          return true;
        }
      }
    }
    if (typeDeclarations_ != null) {
      for (int i = 0; i < typeDeclarations_.size(); ++i) {
        if (node == typeDeclarations_.get(i)) {
          typeDeclarations_.remove(i);
          return true;
        }
      }
    }
    return false;
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
