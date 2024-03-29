package typesystem;

import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a record type.
 *
 * @author Ben
 */
public class RecordType extends Constructed {

  private String name_;
  private Sequence<Type> typeFields_;

  public RecordType() {
    this(null, null, null);
  }

  public RecordType(final String name) {
    this(null, name, null);
  }

  public RecordType(final String name, Sequence<Type> typeFields) {
    this(null, name, typeFields);
  }

  public RecordType(Token token, final String name, Sequence<Type> typeFields) {
    super(token);
    setName(name);
    setTypeFields(typeFields);
  }

  public RecordType setName(final String name) {
    if (Objects.equals(name, name_)) {
      return this;
    }
    name_ = name;
    return this;
  }

  public String getName() {
    return name_;
  }

  public RecordType setTypeFields(Sequence<Type> typeFields) {
    if (typeFields == typeFields_) {
      return this;
    }
    if (typeFields_ != null) {
      typeFields_.setParentNode(null);
    }
    typeFields_ = typeFields;
    return this;
  }

  public Optional<Sequence<Type>> getTypeFields() {
    return Optional.ofNullable(typeFields_);
  }

  @Override
  public String asString() {
    return "<R" + name_ + ";";
  }

  @Override
  public boolean isRecordType() {
    return true;
  }

  @Override
  public RecordType asRecordType() {
    return this;
  }

  // α =T β ⇔ Record?(α) ∧ Record?(β) ∧ (name1 = name2)
  // We implement NAME EQUALITY not structural equality
  @Override
  public boolean typeEqual(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    if (!other.asConstructedType().isRecordType()) {
      return false;
    }
    RecordType rt = other.asConstructedType().asRecordType();
    return name_.equals(rt.name_);
  }

  // α∼T β ⇔ α =T β
  @Override
  public boolean typeEquivalent(Type other) {
    return typeEqual(other);
  }

  // α :=T β ⇔ α ∼T β ⇔ α =T β
  @Override
  public boolean typeAssignmentCompatible(Type other) {
    if (!other.isConstructedType()) {
      return false;
    }
    if (!other.asConstructedType().isRecordType()) {
      return false;
    }
    RecordType rt = other.asConstructedType().asRecordType();
    return rt.extendsType(this);
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (typeFields_ == null) {
      return false;
    }
    for (int i = 0; i < typeFields_.size(); ++i) {
      if (node == typeFields_.get(i)) {
        typeFields_.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean replace(SourceAST node, SourceAST replaceWith) {
    if (node == null) {
      return false;
    }
    if (typeFields_ == null) {
      return false;
    }
    for (int i = 0; i < typeFields_.size(); ++i) {
      if (node == typeFields_.get(i)) {
        typeFields_.set(i, (Type) replaceWith);
        return true;
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
