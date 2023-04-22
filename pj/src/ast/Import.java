package ast;

import org.antlr.v4.runtime.Token;

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
  public boolean replace(Node node, Node replaceWith) {
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
  public String getText() {
    if (isStar_) {
      return "import " + name_.getText() + ".*";
    }
    if (fieldName_ != null && !fieldName_.isEmpty()) {
      if (alias_ != null && !alias_.isEmpty() && !alias_.equals(fieldName_)) {
        return "import static " + name_.getText() + " as " + alias_;
      }
      // This returns the field name as part of the static import.
      return "import static " + name_.getText();
    }
    if (alias_ == null || alias_.isEmpty()) {
      return "import " + name_.getText();
    }
    return "import " + name_.getText() + " as " + alias_;
  }
}
