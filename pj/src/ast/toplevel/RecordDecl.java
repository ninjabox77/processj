package ast.toplevel;

import ast.java.FieldDeclaration;
import ast.Node;
import ast.Sequence;
import ast.types.ASTType;
import ast.types.ConstructedNode;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Optional;

/**
 * Represents a record declaration.
 *
 * @author Ben
 */
public class RecordDecl extends TopLevelDeclaration<RecordDecl> {

  private Sequence<FieldDeclaration> declaredFields_;

  public RecordDecl() {
    this(null, null);
  }

  public RecordDecl(ASTType type, final String name) {
    this(ACC_PUBLIC, type, name);
  }

  public RecordDecl(int modifiers, ASTType type, final String name) {
    this(modifiers, type, name, null);
  }

  public RecordDecl(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames) {
    this(null, modifiers, type, name, implementedNames, null);
  }

  public RecordDecl(int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<FieldDeclaration> declaredFields) {
    this(null, modifiers, type, name, implementedNames, declaredFields);
  }

  public RecordDecl(Token token, int modifiers, ASTType type, final String name, Sequence<ConstructedNode> implementedNames, Sequence<FieldDeclaration> declaredFields) {
    super(token, modifiers, type, name, implementedNames);
    setDeclaredFields(declaredFields);
  }

  public RecordDecl setDeclaredFields(Sequence<FieldDeclaration> fields) {
    if (fields == declaredFields_) {
      return this;
    }
    if (declaredFields_ != null) {
      declaredFields_.setParentNode(null);
    }
    declaredFields_ = fields;
    setAsParentNodeOf(fields);
    return this;
  }

  public Optional<Sequence<FieldDeclaration>> getDeclaredFields() {
    return Optional.ofNullable(declaredFields_);
  }

  @Override
  public boolean isRecordDecl() {
    return true;
  }

  @Override
  public RecordDecl asRecordDecl() {
    return this;
  }

  @Override
  public boolean remove(Node node) {
    if (node == null) {
      return false;
    }
    if (declaredFields_ != null) {
      for (int i = 0; i < declaredFields_.size(); ++i) {
        if (node == declaredFields_.get(i)) {
          declaredFields_.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (declaredFields_ != null) {
      for (int i = 0; i < declaredFields_.size(); ++i) {
        if (node != declaredFields_.get(i)) {
          declaredFields_.set(i, (FieldDeclaration) replaceWith);
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
