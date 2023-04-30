package ast;

import ast.toplevel.TopLevelDeclaration;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

/**
 * Represents the contents of a compilation step that consists of one
 * package, one or more imports, and one or more top level declarations.
 *
 * @author Ben
 */
public class CompileUnit extends AnnotatedNode {

  private Package package_;
  private Sequence<Import> imports_;
  private Sequence<TopLevelDeclaration<?>> typeDeclarations_;

  public CompileUnit() {
    this(null, null, null);
  }

  public CompileUnit(Package packge, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    this(null, packge, imports, typeDeclarations);
  }

  public CompileUnit(Token token, Package packge, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    super(token);
    setPackage(packge);
    setImports(imports);
    setTypeDeclarations(typeDeclarations);
  }

  public CompileUnit setPackage(Package packge) {
    if (packge == package_) {
      return this;
    }
    if (package_ != null) {
      package_.setParentNode(null);
    }
    package_ = packge;
    setAsParentNodeOf(packge);
    return this;
  }

  public Package getPackage() {
    return package_;
  }

  public CompileUnit setImports(Sequence<Import> imports) {
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

  public CompileUnit setTypeDeclarations(Sequence<TopLevelDeclaration<?>> typeDeclarations) {
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

  @Override
  public boolean replace(Node node, Node replaceWith) {
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
  public boolean remove(Node node) {
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
}
