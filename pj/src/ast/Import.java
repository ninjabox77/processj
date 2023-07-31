package ast;

import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;

/**
 * Represents an import statement.
 *
 * @author Ben
 */
public class Import extends AnnotatedNode {

  private Name name_;
  private String alias_;
  private String fieldName_;
  private boolean isJavaImport_;
  private boolean isStar_;

  public Import() {
    this(null, new Name(), null, null, false, false);
  }

  public Import(final String name) {
    this(name, false, false);
  }

  public Import(final String name, boolean isJavaImport, boolean isStar) {
    this(null, new Name(name), null, null, false, false);
  }

  public Import(Token token, Name name, final String alias, final String fieldName, boolean isJavaImport, boolean isStar) {
    super(token);
    setName(name);
    setAlias(alias);
    setFieldName(fieldName);
    setJavaImport(isJavaImport);
    setStar(isStar);
  }

  public Import setName(Name name) {
    if (name == name_) {
      return this;
    }
    if (name_ != null) {
      name_.setParentNode(null);
    }
    name_ = name;
    setAsParentNodeOf(name);
    return this;
  }

  public Name getName() {
    return name_;
  }

  public Import setJavaImport(boolean isJavaImport) {
    if (isJavaImport_ == isJavaImport) {
      return this;
    }
    isJavaImport_ = isJavaImport;
    return this;
  }

  public boolean isJavaImport() {
    return isJavaImport_;
  }

  public Import setStar(boolean isStar) {
    if (isStar_ == isStar) {
      return this;
    }
    isStar_ = isStar;
    return this;
  }

  public boolean isStar() {
    return isStar_;
  }

  public Import setAlias(final String alias) {
    if (Objects.equals(alias, alias_)) {
      return this;
    }
    alias_ = alias;
    return this;
  }

  public String getAlias() {
    return alias_;
  }

  public Import setFieldName(final String fieldName) {
    if (Objects.equals(fieldName, fieldName_)) {
      return this;
    }
    fieldName_ = fieldName;
    return this;
  }

  public String getFieldName() {
    return fieldName_;
  }

  @Override
  public String asString() {
    if (isStar_) {
      return name_.asString() + ".*";
    }
    if (alias_ != null && !alias_.isEmpty() && !alias_.equals(fieldName_)) {
      return name_.asString() + " as " + alias_;
    }
    if (alias_ == null || alias_.isEmpty()) {
      return name_.asString() + "::" + fieldName_;
    }
    return name_.asString();
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (node == name_) {
      setName((Name) replaceWith);
      return true;
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
