package ast.expr;

import ast.Name;
import ast.SourceAST;
import ast.Sequence;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

/**
 * Represents a record literal.
 *
 * @author Ben
 */
public class RecordLiteral extends Expression<RecordLiteral> {

  private Name name_;
  private Sequence<RecordMemberLiteral> members_;

  public RecordLiteral() {
    this(null);
  }

  public RecordLiteral(Name name) {
    this(name, null);
  }

  public RecordLiteral(Name name, Sequence<RecordMemberLiteral> members) {
    this(null, name, members);
  }

  public RecordLiteral(Token token, Name name, Sequence<RecordMemberLiteral> members) {
    super(token);
    setName(name);
    setMembers(members);
  }

  public RecordLiteral setName(Name name) {
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

  public RecordLiteral setMembers(Sequence<RecordMemberLiteral> members) {
    if (members == members_) {
      return this;
    }
    if (members_ != null) {
      members_.setParentNode(null);
    }
    members_ = members;
    setAsParentNodeOf(members);
    return this;
  }

  public Sequence<RecordMemberLiteral> getMembers() {
    return members_;
  }

  @Override
  public boolean isRecordLiteral() {
    return true;
  }

  @Override
  public RecordLiteral asRecordLiteral() {
    return this;
  }

  @Override
  public boolean remove(SourceAST node) {
    if (node == null) {
      return false;
    }
    if (members_ != null) {
      for (int i = 0; i < members_.size(); ++i) {
        if (node == members_.get(i)) {
          members_.remove(i);
          return true;
        }
      }
    }
    return false;
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
    if (members_ != null) {
      for (int i = 0; i < members_.size(); ++i) {
        if (node == members_.get(i)) {
          members_.set(i, (RecordMemberLiteral) replaceWith);
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
