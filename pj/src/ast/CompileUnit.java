package ast;

import ast.toplevel.TopLevelDeclaration;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents the contents of a compilation step that consists of one
 * package, one or more imports, and one or more top level declarations.
 *
 * @author Ben
 */
public class CompileUnit extends AnnotatedNode {

  private Package packet_;
  private Sequence<Import> imports_;
  private Sequence<TopLevelDeclaration<?>> typeDeclarations_;

  public CompileUnit() {
    this(null, null, null);
  }

  public CompileUnit(Package packet, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    this(null, packet, imports, typeDeclarations);
  }

  public CompileUnit(Token token, Package packet, Sequence<Import> imports, Sequence<TopLevelDeclaration<?>> typeDeclarations) {
    super(token);
    setPackage(packet);
    setImports(imports);
    setTypeDeclarations(typeDeclarations);
  }

  public CompileUnit setPackage(Package packet) {
    if (packet == packet_) {
      return this;
    }
    if (packet_ != null) {
      packet_.setParentNode(null);
    }
    packet_ = packet;
    setAsParentNodeOf(packet);
    return this;
  }

  public Package getPackage() {
    return packet_;
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
    if (node == packet_) {
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
    if (node == packet_) {
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
