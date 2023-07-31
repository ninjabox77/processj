package ast.java;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a class { ... } definition.
 *
 * @author Ben
 */
public class ClassDeclaration extends TypeDeclaration<ClassDeclaration> {

  private boolean isInterface_;
  private Sequence<ClassDeclaration> extendedClasses_;
  private Sequence<ClassDeclaration> implementedClasses_;

  public ClassDeclaration() {
    this(ACC_PUBLIC, null, null);
  }

  public ClassDeclaration(final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations) {
    this(modifiers, name, declarations, null);
  }

  public ClassDeclaration(final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations, Sequence<ClassDeclaration> extendedClasses) {
    this(modifiers, name, declarations, extendedClasses, null);
  }

  public ClassDeclaration(final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations, Sequence<ClassDeclaration> extendedClasses, Sequence<ClassDeclaration> implementedClasses) {
    this(null, modifiers, name, declarations, extendedClasses, implementedClasses);
  }

  public ClassDeclaration(Token token, final int modifiers, final String name, Sequence<BodyDeclaration<?>> declarations, Sequence<ClassDeclaration> extendedClasses, Sequence<ClassDeclaration> implementedClasses) {
    super(token, modifiers, name, declarations);
    setExtendedClasses(extendedClasses);
    setImplementedClasses(implementedClasses);
  }

  public ClassDeclaration setExtendedClasses(Sequence<ClassDeclaration> extendedClasses) {
    if (extendedClasses == extendedClasses_) {
      return this;
    }
    if (extendedClasses_ != null) {
      extendedClasses_.setParentNode(null);
    }
    extendedClasses_ = extendedClasses;
    setAsParentNodeOf(extendedClasses);
    return this;
  }

  public Optional<Sequence<ClassDeclaration>> getExtendedClasses() {
    return Optional.ofNullable(extendedClasses_);
  }

  public ClassDeclaration setImplementedClasses(Sequence<ClassDeclaration> implementedClasses) {
    if (implementedClasses == implementedClasses_) {
      return this;
    }
    if (implementedClasses_ != null) {
      implementedClasses_.setParentNode(null);
    }
    implementedClasses_ = implementedClasses;
    setAsParentNodeOf(implementedClasses);
    return this;
  }

  public Optional<Sequence<ClassDeclaration>> getImplementedClasses() {
    return Optional.ofNullable(implementedClasses_);
  }

  public ClassDeclaration setIsInterface(final boolean isInterface) {
    if (isInterface == isInterface_) {
      return this;
    }
    isInterface_ = isInterface;
    return this;
  }

  public boolean isInterface() {
    return isInterface_;
  }

  @Override
  public boolean isClassDeclaration() {
    return true;
  }

  @Override
  public ClassDeclaration asClassDeclaration() {
    return this;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (extendedClasses_ != null) {
      for (int i = 0; i < extendedClasses_.size(); ++i) {
        if (node == extendedClasses_.get(i)) {
          extendedClasses_.set(i, (ClassDeclaration) replaceWith);
          return true;
        }
      }
    }
    if (implementedClasses_ != null) {
      for (int i = 0; i < implementedClasses_.size(); ++i) {
        if (node == implementedClasses_.get(i)) {
          implementedClasses_.set(i, (ClassDeclaration) replaceWith);
          return true;
        }
      }
    }
    return super.replace(node, replaceWith);
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (extendedClasses_ != null) {
      for (int i = 0; i < extendedClasses_.size(); ++i) {
        if (node == extendedClasses_.get(i)) {
          extendedClasses_.remove(i);
          return true;
        }
      }
    }
    if (implementedClasses_ != null) {
      for (int i = 0; i < implementedClasses_.size(); ++i) {
        if (node == implementedClasses_.get(i)) {
          implementedClasses_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
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
