package ast.toplevel;

import ast.Name;
import ast.Node;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a declaration of a top-level constructed declaration.
 *
 * @author Ben
 */
public class TypeDeclarationTopLevel extends TopLevelDeclaration<TypeDeclarationTopLevel> {

  private Name qualifier_;

  public TypeDeclarationTopLevel() {
    this(null);
  }

  public TypeDeclarationTopLevel(Name name) {
    this(null, name);
  }

  public TypeDeclarationTopLevel(Token token, Name name) {
    super(token, ACC_PUBLIC, null, null, null);
  }

  public TypeDeclarationTopLevel setQualifier(Name name) {
    if (name == qualifier_) {
      return this;
    }
    if (qualifier_ != null) {
      qualifier_.setParentNode(null);
    }
    qualifier_ = name;
    setAsParentNodeOf(name);
    return this;
  }

  public Name getQualifier() {
    return qualifier_;
  }

  @Override
  public boolean replace(Node node, Node replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == qualifier_) {
      setQualifier((Name) replaceWith);
      return true;
    }
    return false;
  }

  @Override
  public boolean isTypeDeclarationTopLevel() {
    return true;
  }

  @Override
  public TypeDeclarationTopLevel asTypeDeclarationTopLevel() {
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
