package ast.toplevel;

import ast.SourceAST;
import ast.Sequence;
import ast.types.ASTType;
import ast.types.ConstructedNode;
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
public class ProtocolDeclaration extends TopLevelDeclaration<ProtocolDeclaration> {

  private Sequence<ProtocolCaseDeclaration> declaredTags_;

  public ProtocolDeclaration() {
    this(null, null);
  }

  public ProtocolDeclaration(ASTType type, final String name) {
    this(ACC_PRIVATE, type, name);
  }

  public ProtocolDeclaration(int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public ProtocolDeclaration(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames) {
    this(modifiers, type, name, implementedNames, null);
  }

  public ProtocolDeclaration(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<ProtocolCaseDeclaration> declaredTags) {
    this(null, modifiers, type, name, implementedNames, declaredTags);
  }

  public ProtocolDeclaration(Token token, int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<ProtocolCaseDeclaration> declaredTags) {
    super(token, modifiers, type, name, implementedNames);
    setDeclaredTags(declaredTags);
  }

  public ProtocolDeclaration setDeclaredTags(Sequence<ProtocolCaseDeclaration> declaredTags) {
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

  public Optional<Sequence<ProtocolCaseDeclaration>> getDeclaredTags() {
    return Optional.ofNullable(declaredTags_);
  }

  @Override
  public boolean isProtocolDeclaration() {
    return true;
  }

  @Override
  public ProtocolDeclaration asProtocolDeclaration() {
    return this;
  }

  @Override
  public boolean remove(SourceAST node) {
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
  public boolean replace(SourceAST node, SourceAST replaceWith) {
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
