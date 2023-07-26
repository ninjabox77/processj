package ast.toplevel;

import ast.Node;
import ast.Sequence;
import ast.types.ASTType;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a protocol declaration.
 *
 * @author Ben
 */
public class ProtocolDecl extends TopLevelDeclaration<ProtocolDecl> {

  private Sequence<ProtocolCaseDecl> declaredTags_;

  public ProtocolDecl() {
    this(null, null);
  }

  public ProtocolDecl(ASTType type, final String name) {
    this(ACC_PRIVATE, type, name);
  }

  public ProtocolDecl(int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public ProtocolDecl(int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames) {
    this(modifiers, type, name, implementedNames, null);
  }

  public ProtocolDecl(int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, Sequence<ProtocolCaseDecl> declaredTags) {
    this(null, modifiers, type, name, implementedNames, declaredTags);
  }

  public ProtocolDecl(Token token, int modifiers, ASTType type, final String name, Sequence<TopLevelDeclaration<?>> implementedNames, Sequence<ProtocolCaseDecl> declaredTags) {
    super(token, modifiers, type, name, implementedNames);
    setDeclaredTags(declaredTags);
  }

  public ProtocolDecl setDeclaredTags(Sequence<ProtocolCaseDecl> declaredTags) {
    if (declaredTags == declaredTags_) {
      return this;
    }
    if (declaredTags_ != null) {
      declaredTags_.setParentNode(null);
    }
    declaredTags_ = declaredTags;
    setAsParentNodeOf(declaredTags);
    return this;
  }

  public Optional<Sequence<ProtocolCaseDecl>> getDeclaredTags() {
    return Optional.ofNullable(declaredTags_);
  }

  @Override
  public boolean isProtocolDecl() {
    return true;
  }

  @Override
  public ProtocolDecl asProtocolDecl() {
    return this;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (declaredTags_ != null) {
      for (int i = 0; i < declaredTags_.size(); ++i) {
        if (node == declaredTags_.get(i)) {
          declaredTags_.remove(i);
          return true;
        }
      }
    }
    return super.remove(node);
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (declaredTags_ != null) {
      for (int i = 0; i < declaredTags_.size(); ++i) {
        if (node == declaredTags_.get(i)) {
          declaredTags_.remove(i);
          return true;
        }
      }
    }
    return super.replace(node, replaceWith);
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
