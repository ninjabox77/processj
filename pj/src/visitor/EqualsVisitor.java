package visitor;

import ast.*;
import ast.Package;
import ast.comments.BlockComment;
import ast.comments.LineComment;
import ast.expr.*;
import ast.java.*;
import ast.stmt.*;
import ast.toplevel.*;
import ast.types.*;
import typesystem.*;

import java.util.List;

/**
 * @author Ben
 */
public class EqualsVisitor implements GenericVisitor<Boolean, CodeVisitor> {

  private static final EqualsVisitor INSTANCE = new EqualsVisitor();

  public static boolean equals(final Node n1, final Node n2) {
    return INSTANCE.objectEquals(n1, n2);
  }

  private <N extends Node> Boolean objectEquals(final Sequence<N> nodes1, final Sequence<N> nodes2) {
    if (nodes1 == null) {
      return nodes2 == null;
    } else if (nodes2 == null) {
      return Boolean.FALSE;
    }
    if (nodes1 == nodes2) {
      return Boolean.TRUE;
    }
    if (nodes1.size() != nodes2.size()) {
      return Boolean.FALSE;
    }
    for (int i = 0; i < nodes1.size(); ++i) {
      if (!objectEquals(nodes1.get(i), nodes2.get(i))) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  private <T> Boolean objectEquals(final List<T> n1, final List<T> n2) {
    if (n1 == null) {
      return n2 == null;
    } else if (n2 == null) {
      return Boolean.FALSE;
    }
    if (n1 == n2) {
      return Boolean.TRUE;
    }
    if (n1.size() != n2.size()) {
      return Boolean.FALSE;
    }
    for (int i = 0; i < n1.size(); ++i) {
      if (!objectEquals(n1.get(i), n2.get(i))) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  private <N extends Node> Boolean objectEquals(final N n1, final N n2) {
    if (n1 == null) {
      return n2 == null;
    } else if (n2 == null) {
      return Boolean.FALSE;
    }
    if (n1 == n2) {
      return Boolean.TRUE;
    }
    if (n1.getClass() != n2.getClass()) {
      return Boolean.FALSE;
    }
    return n1.accept(this, n2);
  }

  private Boolean objectEquals(final Object o1, final Object o2) {
    if (o1 == null) {
      return o2 == null;
    } else if (o2 == null) {
      return Boolean.FALSE;
    }
    if (o1 == o2) {
      return Boolean.TRUE;
    }
    return o1.equals(o2);
  }

  @Override
  public Boolean visit(AnnotationNode a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ArrayDimension a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ClassNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CompileUnit c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Import i, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Name n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Node n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Package p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Parameter p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(Sequence<?> s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ArrayNode a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BarrierNode b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ChannelEndNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ChannelNode c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ExternalNode e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(PrimitiveNode p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(TimerNode t, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ConstantTopLevel c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ExternalTopLevel e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MethodCallTopLevel<?> m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MobileDeclaration m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProcedureTopLevel p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProtocolTagDeclaration p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProtocolTopLevel p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(RecordTopLevel r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BlockStmt b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BreakStmt b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CaseStmt c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ContinueStmt c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(DoWhileStmt d, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(EmptyStmt e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ExpressionStmt e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ForEachStmt f, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ForStmt f, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(GuardStmt g, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(IfStmt i, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(RegularAltStmt r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ReturnStmt r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(SwitchStmt s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(WhileStmt w, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BodyDeclaration<?> b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ClassDeclaration c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ConstructorDeclaration c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(FieldDeclaration f, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MethodDeclaration<?> m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ArrayExpr a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BinaryExpr b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BooleanExpr b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CallableExpr c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CastExpr c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CharLiteral c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ClassExpr c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ConstructorCallExpr c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(DeclarationExpr d, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(DoubleLiteral d, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(EmptyExpr e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(FieldAccessExpr f, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean viit(IntegerLiteral i, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ListExpression<?> l, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(LongLiteral l, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MapEntryExpr m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MapExpr m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MethodCallExpr m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(NewArrayExpr n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(NotExpression n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(NullLiteral n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(PostfixExpr p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(PrefixExpr p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProtocolExpr p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(RecordExpr r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(SkipExpr s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(StopExpr s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(TernaryExpr t, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(UnaryMinusExpr u, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(UnaryPlusExpr u, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BlockComment b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(LineComment l, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ArrayType a, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(BarrierType b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ByteType b, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ChannelEndType c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ChannelType c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(CharType c, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(DoubleType d, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ExternalType e, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(FloatType f, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(IntegerType i, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(LongType l, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MethodCallType<?> m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(MobileType m, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(NullType n, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProcedureType p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProtocolTagType p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ProtocolType p, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(RecordType r, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(ShortType s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(StringType s, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(TimerType t, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(UnknownType u, CodeVisitor arg) {
    return null;
  }

  @Override
  public Boolean visit(VoidType v, CodeVisitor arg) {
    return null;
  }
}
