package visitor;

import ast.*;
import ast.Package;
import ast.comments.BlockComment;
import ast.comments.LineComment;
import ast.expr.*;
import ast.java.ClassDeclaration;
import ast.java.ConstructorDeclaration;
import ast.java.FieldDeclaration;
import ast.java.MethodDeclaration;
import ast.stmt.*;
import ast.toplevel.*;
import ast.types.*;
import control.CompilationUnit;
import typesystem.*;

import java.io.PrintWriter;

/**
 * Visitor to print the contents of the AST.
 *
 * @author Ben
 */
public class TreePrinter implements VoidVisitor<Visitor> {

  private int tabLevel_ = 0;
  private final PrintWriter out_;

  private String tabs(int line) {
    final String TAB = "  ";
    return line + ":" + TAB.repeat(Math.max(0, tabLevel_));
  }

  public TreePrinter(PrintWriter out) {
    out_ = out;
  }

  @Override
  public void visit(final AnnotationNode a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "AnnotationNode:");
  }

  @Override
  public void visit(final ArrayDimension a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "ArrayDimension:");
    tabLevel_++;
    a.getDimension().ifPresent(d -> d.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BytecodeAST b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BytecodeAST:");
  }

  @Override
  public void visit(final Compilation c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "CompileUnit:");
    tabLevel_++;
    if (c.getPackage() != null) {
      c.getPackage().accept(this, arg);
    }
    c.getImports().ifPresent(i -> i.accept(this, arg));
    c.getTypeDeclarations().ifPresent(t -> t.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final CompilationUnit c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "CompilationUnit:");
  }

  @Override
  public void visit(final Import i, Visitor arg) {
    out_.println(tabs(i.getLine()) + "Import: " + i.asString());
  }

  @Override
  public void visit(final Name n, Visitor arg) {
    out_.println(tabs(n.getLine()) + "Name: " + n.asString());
  }

  @Override
  public void visit(final Package p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "Package: " + p.asString());
  }

  @Override
  public void visit(final Parameter p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "Parameter:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    p.getASTType().accept(this, arg);
    out_.println(tabs(p.getLine()) + "Identifier: " + p.getIdentifier());
    tabLevel_--;
  }

  @Override
  public void visit(final Sequence<?> s, Visitor arg) {
    s.forEach(e -> e.accept(this, arg));
  }

  @Override
  public void visit(final VariableDeclarator v, Visitor arg) {
    out_.println(tabs(v.getLine()) + "VariableDeclarator: ");
    tabLevel_++;
    out_.println(tabs(v.getLine()) + "Modifier: " + v.modifierString());
    v.getASTType().accept(this, arg);
    out_.println(tabs(v.getLine()) + "Identifier: " + v.getIdentifier());
    if (v.getRightExpression() != null) {
      out_.println(tabs(v.getLine()) + "Initializer: ");
      tabLevel_++;
      v.getRightExpression().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final Label l, Visitor arg) {
    out_.println(tabs(l.getLine()) + "Label: " + l.getIdentifier());
  }

  @Override
  public void visit(final ArrayNode a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "ArrayNode:");
    tabLevel_++;
    a.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BarrierNode b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BarrierNode:");
    tabLevel_++;
    b.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelEndNode c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelEndNode:");
    tabLevel_++;
    c.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelNode c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelNode:");
    tabLevel_++;
    c.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalNode e, Visitor arg) {
  }

  @Override
  public void visit(final PrimitiveNode p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "PrimitiveNode:");
    tabLevel_++;
    p.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final TimerNode t, Visitor arg) {
    out_.println(tabs(t.getLine()) + "TimerNode:");
    tabLevel_++;
    t.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final VoidNode v, Visitor arg) {
    out_.print(tabs(v.getLine()) + "VoidNode:");
    tabLevel_++;
    v.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ConstructedNode c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ConstructedNode:");
    tabLevel_++;
    c.getType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final UnknownNode e, Visitor arg) {
  }

  @Override
  public void visit(final ConstantDeclaration c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ConstantDeclaration:");
    tabLevel_++;
    out_.println(tabs(c.getLine()) + "Modifier: " + c.modifierString());
    c.getASTType().accept(this, arg);
    out_.println(tabs(c.getLine()) + "Identifier: " + c.getIdentifier());
    c.getRightExpression().ifPresent(v -> v.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalDeclaration e, Visitor arg) {
  }

  @Override
  public void visit(final ProcedureDeclaration p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProcedureDeclaration:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    out_.println(tabs(p.getLine()) + "Identifier: " + p.getIdentifier());
    out_.println(tabs(p.getLine()) + "Yields: " + p.doesYield());
    p.getParameters().forEach(v -> v.accept(this, arg));
    if (p.getBody() != null) {
      p.getBody().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolCaseDeclaration p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProtocolCaseDeclaration:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Identifier: " + p.getIdentifier());
    p.getDeclaredFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolDeclaration p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProtocolDeclaration:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    out_.println(tabs(p.getLine()) + "Identifier: " + p.getIdentifier());
    p.getDeclaredTags().ifPresent(t -> t.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final RecordDeclaration r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "RecordDeclaration:");
    tabLevel_++;
    out_.println(tabs(r.getLine()) + "Modifier: " + r.modifierString());
    out_.println(tabs(r.getLine()) + "Identifier: " + r.getIdentifier());
    r.getDeclaredFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BlockStatement b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BlockStatement:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final SequentialBlock s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "SequentialBlock:");
    tabLevel_++;
    if (s.getStatements() != null) {
      s.getStatements().forEach(b -> b.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final BreakStatement b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "Statement: break");
  }

  @Override
  public void visit(final SwitchCaseStatement c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "SwitchCaseStatement");
    tabLevel_++;
    c.getExpressions().forEach(e -> e.accept(this, arg));
    c.getStatements().ifPresent(s -> s.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ContinueStatement c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ContinueStatement");
  }

  @Override
  public void visit(final SkipStatement s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "SkipStatement");
  }

  @Override
  public void visit(final StopStatement s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "StopStatement");
  }

  @Override
  public void visit(final DoWhileStatement d, Visitor arg) {
    out_.println(tabs(d.getLine()) + "DoWhileStatement:");
    tabLevel_++;
    d.getLoopBlock().ifPresent(l -> l.accept(this, arg));
    out_.println(tabs(d.getLine()) + "Conditional:");
    tabLevel_++;
    d.getCondition().accept(this, arg);
    tabLevel_--;
    tabLevel_--;
  }

  @Override
  public void visit(final EmptyStatement e, Visitor arg) {
    out_.println(tabs(e.getLine()) + "EmptyStatement");
  }

  @Override
  public void visit(final ExpressionStatement e, Visitor arg) {
    out_.println(tabs(e.getLine()) + "ExpressionStatement:");
    tabLevel_++;
    e.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ForEachStatement f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "ForEachStatement:");
    tabLevel_++;
    f.getVariable().accept(this, arg);
    f.getCollectionExpr().accept(this, arg);
    f.getLoopBlock().ifPresent(s -> s.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ForStatement f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "ForStatement:");
    tabLevel_++;
    f.getInitialization().forEach(i -> i.accept(this, arg));
    f.getConditional().accept(this, arg);
    f.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final Guard g, Visitor arg) {
    out_.println(tabs(g.getLine()) + "Guard:");
    tabLevel_++;
    g.getExpression().accept(this, arg);
    out_.println(tabs(g.getLine()) + "Skip: " + g.isSkip());
    tabLevel_--;
  }

  @Override
  public void visit(final IfStatement i, Visitor arg) {
    out_.println(tabs(i.getLine()) + "IfStatement:");
    tabLevel_++;
    out_.println(tabs(i.getLine()) + "Conditional:");
    tabLevel_++;
    i.getConditional().accept(this, arg);
    tabLevel_--;
    out_.println(tabs(i.getLine()) + "Then-part:");
    tabLevel_++;
    i.getIfPart().accept(this, arg);
    tabLevel_--;
    if (i.getElsePart().isPresent()) {
      out_.println(tabs(i.getLine()) + "Else-part:");
      tabLevel_++;
      i.getElsePart().get().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final AltBlock r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "AltBlock: ");
    tabLevel_++;
    r.getLoopBlock().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ReturnStatement r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "ReturnStatement: ");
    tabLevel_++;
    r.getExpression().ifPresent(e -> e.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final SwitchStatement s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "SwitchStatement: ");
    tabLevel_++;
    out_.println(tabs(s.getLine()) + "Selector: ");
    tabLevel_++;
    s.getSelector().accept(this, arg);
    tabLevel_--;
    s.getSwitchCases().accept(this, arg);
    tabLevel_--;

  }

  @Override
  public void visit(final WhileStatement w, Visitor arg) {
    out_.println(tabs(w.getLine()) + "WhileStatement:");
    tabLevel_++;
    out_.println(tabs(w.getLine()) + "Conditional:");
    tabLevel_++;
    w.getCondition().accept(this, arg);
    tabLevel_--;
    w.getLoopBlock().ifPresent(l -> l.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ReplicatedAltBlock r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "ReplicatedAltBlock:");
    tabLevel_++;
    r.getInitialization().forEach(i -> i.accept(this, arg));
    r.getConditional().accept(this, arg);
    r.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final AltCaseStatement a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "AltCaseStatement:");
    tabLevel_++;
    a.getExpression().ifPresent(e -> e.accept(this, arg));
    a.getGuard().accept(this, arg);
    a.getStatement().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ParBlock r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "ParBlock:");
    tabLevel_++;
    r.getBarriers().ifPresent(b -> b.accept(this, arg));
    r.getLoopBlock().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ParForBlock p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ParForBlock:");
    tabLevel_++;
    p.getInitialization().forEach(i -> i.accept(this, arg));
    p.getConditional().accept(this, arg);
    p.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final LabelStatement l, Visitor arg) {
    out_.println(tabs(l.getLine()) + "LabelStatement:");
    tabLevel_++;
    l.getStatementLabels().ifPresent(s -> s.accept(this, arg));
    l.getStatement().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ClassDeclaration c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ClassDeclaration:");
  }

  @Override
  public void visit(final ConstructorDeclaration c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ConstructorDeclaration:");
  }

  @Override
  public void visit(final FieldDeclaration f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "FieldDeclaration:");
    tabLevel_++;
    f.getVariable().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MethodDeclaration<?> m, Visitor arg) {
    out_.println(tabs(m.getLine()) + "MethodDeclaration:");
  }

  @Override
  public void visit(final ArrayInitializer a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "ArrayInitializer:");
    tabLevel_++;
    a.getValues().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final TupleExpression t, Visitor arg) {
    out_.println(tabs(t.getLine()) + "TupleExpression:");
    tabLevel_++;
    t.getValues().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final AssignmentExpression a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "AssignmentExpression:");
    tabLevel_++;
    out_.println(tabs(a.getLine()) + "Operator: " + a.opString());
    a.getLeftExpression().accept(this, arg);
    a.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BinaryExpression b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BinaryExpression:");
    tabLevel_++;
    out_.println(tabs(b.getLine()) + "Operator: " + b.opString());
    b.getLeftExpression().accept(this, arg);
    b.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanExpression b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BooleanExpression:");
    tabLevel_++;
    b.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanLiteral b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BooleanLiteral: " + b.getValue());
  }

  @Override
  public void visit(final Invocation c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "Invocation: " + c.getIdentifier());
    tabLevel_++;
    c.getScope().ifPresent(m -> m.accept(this, arg));
    c.getArguments().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CastExpression c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "CastExpression: ");
    tabLevel_++;
    c.getASTType().accept(this, arg);
    c.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CharacterLiteral c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "CharacterLiteral: " + c.getValue());
  }

  @Override
  public void visit(final ClassExpression c, Visitor arg) {

  }

  @Override
  public void visit(final ObjectCreationExpr c, Visitor arg) {

  }

  @Override
  public void visit(final DeclarationExpression d, Visitor arg) {
    out_.println(tabs(d.getLine()) + "DeclarationExpression:");
    tabLevel_++;
    d.getVariable().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final DoubleLiteral d, Visitor arg) {
    out_.println(tabs(d.getLine()) + "DoubleLiteral: " + d.getValue());
  }

  @Override
  public void visit(final EmptyExpression e, Visitor arg) {
    out_.println(tabs(e.getLine()) + "EmptyExpression: ");
  }

  @Override
  public void visit(final FieldExpression f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "FieldExpression: ");
    tabLevel_++;
    f.getScope().ifPresent(a -> a.accept(this, arg));
    out_.println(tabs(f.getLine()) + "Identifier: " + f.getIdentifier());
    tabLevel_--;
  }

  @Override
  public void visit(final IntegerLiteral i, Visitor arg) {
    out_.println(tabs(i.getLine()) + "IntegerLiteral: " + i.getValue());
  }

  @Override
  public void visit(final ListExpression<?> l, Visitor arg) {
    out_.println(tabs(l.getLine()) + "ListExpression: ");
    l.getValues().accept(this, arg);
  }

  @Override
  public void visit(final LongLiteral l, Visitor arg) {
    out_.println(tabs(l.getLine()) + "LongLiteral: " + l.getValue());
  }

  @Override
  public void visit(final MapEntryExpression m, Visitor arg) {
    out_.println(tabs(m.getLine()) + "MapEntryExpression:");
    tabLevel_++;
    m.getKeyExpression().accept(this, arg);
    m.getValueExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MapExpression m, Visitor arg) {
    out_.println(tabs(m.getLine()) + "MapExpression:");
    tabLevel_++;
    m.getMapEntry().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MethodCallExpression m, Visitor arg) {
    out_.println(tabs(m.getLine()) + "MethodCallExpression: " + m.getIdentifier());
    tabLevel_++;
    m.getScope().ifPresent(s -> s.accept(this, arg));
    m.getTypeArguments().ifPresent(t -> t.accept(this, arg));
    m.getArguments().accept(this, arg);
    out_.println(tabs(m.getLine()) + "This: " + m.isImplicitThis());
    tabLevel_--;
  }

  @Override
  public void visit(final NewArrayExpression n, Visitor arg) {
    out_.println(tabs(n.getLine()) + "NewArrayExpression:");
    tabLevel_++;
    n.getASTType().accept(this, arg);
    n.getLevels().ifPresent(s -> s.accept(this, arg));
    if (n.getArrayInitializer() != null) {
      n.getArrayInitializer().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final NotExpression n, Visitor arg) {
    out_.println(tabs(n.getLine()) + "NotExpression:");
    tabLevel_++;
    n.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final NullLiteral n, Visitor arg) {
    out_.println(tabs(n.getLine()) + "NullLiteral: " + n.getValue());
  }

  @Override
  public void visit(final PostfixExpression p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "PostfixExpression:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Operator = " + p.opString());
    p.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final PrefixExpression p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "PrefixExpression:");
    tabLevel_++;
    out_.println(tabs(p.getLine()) + "Operator = " + p.opString());
    p.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final SkipExpression s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "SkipExpression:");
  }

  @Override
  public void visit(final StopExpression s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "StopExpression:");
  }

  @Override
  public void visit(final TernaryExpression t, Visitor arg) {
    out_.println(tabs(t.getLine()) + "TernaryExpression:");
    tabLevel_++;
    t.getCondition().accept(this, arg);
    t.getTrueExpression().accept(this, arg);
    t.getFalseExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final UnaryMinusExpression u, Visitor arg) {
    out_.println(tabs(u.getLine()) + "UnaryMinusExpression:");
    tabLevel_++;
    u.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final UnaryPlusExpression u, Visitor arg) {
    out_.println(tabs(u.getLine()) + "UnaryPlusExpression:");
    tabLevel_++;
    u.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final VariableExpression v, Visitor arg) {
    out_.println(tabs(v.getLine()) + "VariableExpression: " + v.getIdentifier());
  }

  @Override
  public void visit(final FloatLiteral f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "FloatLiteral: " + f.getValue());
  }

  @Override
  public void visit(final StringLiteral s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "StringLiteral: " + s.getValue());
  }

  @Override
  public void visit(final BlockExpression b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BlockExpression:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelReadExpression c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelReadExpression:");
    tabLevel_++;
    c.getChannel().accept(this, arg);
    if (c.getArguments() != null) {
      c.getArguments().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelWriteExpression c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelWriteExpression:");
    tabLevel_++;
    c.getChannel().accept(this, arg);
    c.getArguments().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final GroupExpression g, Visitor arg) {
    out_.println(tabs(g.getLine()) + "GroupExpression:");
    tabLevel_++;
    g.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ArrayAccess a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "ArrayAccess:");
    tabLevel_++;
    a.getName().accept(this, arg);
    a.getIndex().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final RecordLiteral r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "RecordLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    if (r.getMembers() != null) {
      r.getMembers().forEach(m -> m.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final RecordMemberLiteral r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "RecordMemberLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    r.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolLiteral p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProtocolLiteral:");
    tabLevel_++;
    p.getName().accept(this, arg);
    p.getTag().accept(this, arg);
    if (p.getMembers() != null) {
      p.getMembers().forEach(m -> m.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final BlockComment b, Visitor arg) {
  }

  @Override
  public void visit(final LineComment l, Visitor arg) {
  }

  @Override
  public void visit(final ArrayType a, Visitor arg) {
    out_.println(tabs(a.getLine()) + "ArrayType: " + a.asString());
  }

  @Override
  public void visit(final BarrierType b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BarrierType: " + b.asString());
  }

  @Override
  public void visit(final ByteType b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "ByteType: " + b.asString());
  }

  @Override
  public void visit(final ChannelEndType c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelEndType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelType c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "ChannelType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CharType c, Visitor arg) {
    out_.println(tabs(c.getLine()) + "CharType: " + c.asString());
  }

  @Override
  public void visit(final DoubleType d, Visitor arg) {
    out_.println(tabs(d.getLine()) + "DoubleType: " + d.asString());
  }

  @Override
  public void visit(final ExternalType e, Visitor arg) {
  }

  @Override
  public void visit(final FloatType f, Visitor arg) {
    out_.println(tabs(f.getLine()) + "FloatType: " + f.asString());
  }

  @Override
  public void visit(final IntegerType i, Visitor arg) {
    out_.println(tabs(i.getLine()) + "IntegerType: " + i.asString());
  }

  @Override
  public void visit(final LongType l, Visitor arg) {
    out_.println(tabs(l.getLine()) + "LongType: " + l.asString());
  }

  @Override
  public void visit(final MobileType m, Visitor arg) {
  }

  @Override
  public void visit(final NullType n, Visitor arg) {
    out_.println(tabs(n.getLine()) + "NullType: " + n.asString());
  }

  @Override
  public void visit(final ProcedureType p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProcedureType: " + p.asString());
    tabLevel_++;
    p.getReturnType().accept(this, arg);
    p.getParameterTypes().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolTagType p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProtocolTagType: ");
    tabLevel_++;
    p.getTypeFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolType p, Visitor arg) {
    out_.println(tabs(p.getLine()) + "ProtocolType: " + p.getName());
    tabLevel_++;
    p.getFieldTypes().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final RecordType r, Visitor arg) {
    out_.println(tabs(r.getLine()) + "RecordType: " + r.getName());
  }

  @Override
  public void visit(final ShortType s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "ShortType: short");
  }

  @Override
  public void visit(final BooleanType b, Visitor arg) {
    out_.println(tabs(b.getLine()) + "BooleanType: boolean");
  }

  @Override
  public void visit(final StringType s, Visitor arg) {
    out_.println(tabs(s.getLine()) + "StringType: string");
  }

  @Override
  public void visit(final TimerType t, Visitor arg) {
    out_.println(tabs(t.getLine()) + "TimerType: timer");
  }

  @Override
  public void visit(final UnknownType u, Visitor arg) {
    out_.println(tabs(u.getLine()) + "UnknownType: ?");
  }

  @Override
  public void visit(final VoidType v, Visitor arg) {
    out_.println(tabs(v.getLine()) + "VoidType: void");
  }

  @Override
  public void visit(final NamedType t, Visitor arg) {
    out_.println(tabs(t.getLine()) + "TypeVariable:");
    tabLevel_++;
    t.getName().accept(this, arg);
    tabLevel_--;
  }
}
