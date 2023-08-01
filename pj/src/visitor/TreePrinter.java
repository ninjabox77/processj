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
  private final PrintWriter printer_;

  private String tabs(int line) {
    final String TAB = "  ";
    return line + ":" + TAB.repeat(Math.max(0, tabLevel_));
  }

  public TreePrinter(PrintWriter printer) {
    printer_ = printer;
  }

  @Override
  public void visit(final AnnotationNode a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "AnnotationNode:");
  }

  @Override
  public void visit(final ArrayDimension a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "ArrayDimension:");
    tabLevel_++;
    a.getDimension().ifPresent(d -> d.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BytecodeAST b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BytecodeAST:");
  }

  @Override
  public void visit(final Compilation c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "CompileUnit:");
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
    printer_.println(tabs(c.getLine()) + "CompilationUnit:");
  }

  @Override
  public void visit(final Import i, Visitor arg) {
    printer_.println(tabs(i.getLine()) + "Import: " + i.asString());
  }

  @Override
  public void visit(final Name n, Visitor arg) {
    printer_.println(tabs(n.getLine()) + "Name: " + n.asString());
  }

  @Override
  public void visit(final Package p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "Package: " + p.asString());
  }

  @Override
  public void visit(final Parameter p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "Parameter:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    p.getNodeType().accept(this, arg);
    printer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    tabLevel_--;
  }

  @Override
  public void visit(final Sequence<?> s, Visitor arg) {
    s.forEach(e -> e.accept(this, arg));
  }

  @Override
  public void visit(final VariableDeclarator v, Visitor arg) {
    printer_.println(tabs(v.getLine()) + "VariableDeclarator: ");
    tabLevel_++;
    printer_.println(tabs(v.getLine()) + "Modifier: " + v.modifierString());
    v.getNodeType().accept(this, arg);
    printer_.println(tabs(v.getLine()) + "Identifier: " + v.getName());
    if (v.getRightExpression() != null) {
      printer_.println(tabs(v.getLine()) + "Initializer: ");
      tabLevel_++;
      v.getRightExpression().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final Label l, Visitor arg) {
    printer_.println(tabs(l.getLine()) + "Label: " + l.getIdentifier());
  }

  @Override
  public void visit(final ArrayNode a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "ArrayNode:");
    tabLevel_++;
    a.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BarrierNode b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BarrierNode:");
    tabLevel_++;
    b.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelEndNode c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelEndNode:");
    tabLevel_++;
    c.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelNode c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelNode:");
    tabLevel_++;
    c.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalNode e, Visitor arg) {
  }

  @Override
  public void visit(final PrimitiveNode p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "PrimitiveNode:");
    tabLevel_++;
    p.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final TimerNode t, Visitor arg) {
    printer_.println(tabs(t.getLine()) + "TimerNode:");
    tabLevel_++;
    t.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final VoidNode v, Visitor arg) {
    printer_.print(tabs(v.getLine()) + "VoidNode:");
    tabLevel_++;
    v.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ConstructedNode c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ConstructedNode:");
    tabLevel_++;
    c.getTSType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ErrorNode e, Visitor arg) {
  }

  @Override
  public void visit(final ConstantDeclaration c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ConstantDeclaration:");
    tabLevel_++;
    printer_.println(tabs(c.getLine()) + "Modifier: " + c.modifierString());
    c.getNodeType().accept(this, arg);
    printer_.println(tabs(c.getLine()) + "Identifier: " + c.getName());
    c.getRightExpression().ifPresent(v -> v.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ExternalDeclaration e, Visitor arg) {
  }

  @Override
  public void visit(final ProcedureDeclaration p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProcedureDeclaration:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    printer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    printer_.println(tabs(p.getLine()) + "Yields: " + p.doesYield());
    p.getParameters().forEach(v -> v.accept(this, arg));
    if (p.getBody() != null) {
      p.getBody().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolCaseDeclaration p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProtocolCaseDeclaration:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    p.getDeclaredFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolDeclaration p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProtocolDeclaration:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Modifier: " + p.modifierString());
    printer_.println(tabs(p.getLine()) + "Identifier: " + p.getName());
    p.getDeclaredTags().ifPresent(t -> t.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final RecordDeclaration r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "RecordDeclaration:");
    tabLevel_++;
    printer_.println(tabs(r.getLine()) + "Modifier: " + r.modifierString());
    printer_.println(tabs(r.getLine()) + "Identifier: " + r.getName());
    r.getDeclaredFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final BlockStatement b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BlockStatement:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final SequentialBlock s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "SequentialBlock:");
    tabLevel_++;
    if (s.getStatements() != null) {
      s.getStatements().forEach(b -> b.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final BreakStatement b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "Statement: break");
  }

  @Override
  public void visit(final SwitchCaseStatement c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "SwitchCaseStatement");
    tabLevel_++;
    c.getExpressions().forEach(e -> e.accept(this, arg));
    c.getStatements().ifPresent(s -> s.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ContinueStatement c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ContinueStatement");
  }

  @Override
  public void visit(final SkipStatement s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "SkipStatement");
  }

  @Override
  public void visit(final StopStatement s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "StopStatement");
  }

  @Override
  public void visit(final DoWhileStatement d, Visitor arg) {
    printer_.println(tabs(d.getLine()) + "DoWhileStatement:");
    tabLevel_++;
    d.getLoopBlock().ifPresent(l -> l.accept(this, arg));
    printer_.println(tabs(d.getLine()) + "Conditional:");
    tabLevel_++;
    d.getCondition().accept(this, arg);
    tabLevel_--;
    tabLevel_--;
  }

  @Override
  public void visit(final EmptyStatement e, Visitor arg) {
    printer_.println(tabs(e.getLine()) + "EmptyStatement");
  }

  @Override
  public void visit(final ExpressionStatement e, Visitor arg) {
    printer_.println(tabs(e.getLine()) + "ExpressionStatement:");
    tabLevel_++;
    e.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ForEachStatement f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "ForEachStatement:");
    tabLevel_++;
    f.getVariable().accept(this, arg);
    f.getCollectionExpr().accept(this, arg);
    f.getLoopBlock().ifPresent(s -> s.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ForStatement f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "ForStatement:");
    tabLevel_++;
    f.getInitialization().forEach(i -> i.accept(this, arg));
    f.getConditional().accept(this, arg);
    f.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final Guard g, Visitor arg) {
    printer_.println(tabs(g.getLine()) + "Guard:");
    tabLevel_++;
    g.getExpression().accept(this, arg);
    printer_.println(tabs(g.getLine()) + "Skip: " + g.isSkip());
    tabLevel_--;
  }

  @Override
  public void visit(final IfStatement i, Visitor arg) {
    printer_.println(tabs(i.getLine()) + "IfStatement:");
    tabLevel_++;
    printer_.println(tabs(i.getLine()) + "Conditional:");
    tabLevel_++;
    i.getConditional().accept(this, arg);
    tabLevel_--;
    printer_.println(tabs(i.getLine()) + "Then-part:");
    tabLevel_++;
    i.getIfPart().accept(this, arg);
    tabLevel_--;
    if (i.getElsePart().isPresent()) {
      printer_.println(tabs(i.getLine()) + "Else-part:");
      tabLevel_++;
      i.getElsePart().get().accept(this, arg);
      tabLevel_--;
    }
    tabLevel_--;
  }

  @Override
  public void visit(final AltBlock r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "AltBlock: ");
    tabLevel_++;
    r.getLoopBlock().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ReturnStatement r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "ReturnStatement: ");
    tabLevel_++;
    r.getExpression().ifPresent(e -> e.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final SwitchStatement s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "SwitchStatement: ");
    tabLevel_++;
    printer_.println(tabs(s.getLine()) + "Selector: ");
    tabLevel_++;
    s.getSelector().accept(this, arg);
    tabLevel_--;
    s.getSwitchCases().accept(this, arg);
    tabLevel_--;

  }

  @Override
  public void visit(final WhileStatement w, Visitor arg) {
    printer_.println(tabs(w.getLine()) + "WhileStatement:");
    tabLevel_++;
    printer_.println(tabs(w.getLine()) + "Conditional:");
    tabLevel_++;
    w.getCondition().accept(this, arg);
    tabLevel_--;
    w.getLoopBlock().ifPresent(l -> l.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ReplicatedAltBlock r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "ReplicatedAltBlock:");
    tabLevel_++;
    r.getInitialization().forEach(i -> i.accept(this, arg));
    r.getConditional().accept(this, arg);
    r.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final AltCaseStatement a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "AltCaseStatement:");
    tabLevel_++;
    a.getExpression().ifPresent(e -> e.accept(this, arg));
    a.getGuard().accept(this, arg);
    a.getStatement().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ParBlock r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "ParBlock:");
    tabLevel_++;
    r.getBarriers().ifPresent(b -> b.accept(this, arg));
    r.getLoopBlock().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ParForBlock p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ParForBlock:");
    tabLevel_++;
    p.getInitialization().forEach(i -> i.accept(this, arg));
    p.getConditional().accept(this, arg);
    p.getUpdate().forEach(u -> u.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final LabelStatement l, Visitor arg) {
    printer_.println(tabs(l.getLine()) + "LabelStatement:");
    tabLevel_++;
    l.getStatementLabels().ifPresent(s -> s.accept(this, arg));
    l.getStatement().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ClassDeclaration c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ClassDeclaration:");
  }

  @Override
  public void visit(final ConstructorDeclaration c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ConstructorDeclaration:");
  }

  @Override
  public void visit(final FieldDeclaration f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "FieldDeclaration:");
    tabLevel_++;
    f.getVariable().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MethodDeclaration<?> m, Visitor arg) {
    printer_.println(tabs(m.getLine()) + "MethodDeclaration:");
  }

  @Override
  public void visit(final ArrayInitializer a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "ArrayInitializer:");
    tabLevel_++;
    a.getValues().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final TupleExpression t, Visitor arg) {
    printer_.println(tabs(t.getLine()) + "TupleExpression:");
    tabLevel_++;
    t.getValues().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final AssignmentExpression a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "AssignmentExpression:");
    tabLevel_++;
    printer_.println(tabs(a.getLine()) + "Operator: " + a.opString());
    a.getLeftExpression().accept(this, arg);
    a.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BinaryExpression b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BinaryExpression:");
    tabLevel_++;
    printer_.println(tabs(b.getLine()) + "Operator: " + b.opString());
    b.getLeftExpression().accept(this, arg);
    b.getRightExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanExpression b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BooleanExpression:");
    tabLevel_++;
    b.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final BooleanLiteral b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BooleanLiteral: " + b.getValue());
  }

  @Override
  public void visit(final Invocation c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "Invocation: " + c.getIdentifier());
    tabLevel_++;
    c.getScope().ifPresent(m -> m.accept(this, arg));
    c.getArguments().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CastExpression c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "CastExpression: ");
    tabLevel_++;
    c.getNodeType().accept(this, arg);
    c.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CharacterLiteral c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "CharacterLiteral: " + c.getValue());
  }

  @Override
  public void visit(final ClassExpression c, Visitor arg) {

  }

  @Override
  public void visit(final ObjectCreationExpr c, Visitor arg) {

  }

  @Override
  public void visit(final DeclarationExpression d, Visitor arg) {
    printer_.println(tabs(d.getLine()) + "DeclarationExpression:");
    tabLevel_++;
    d.getVariable().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final DoubleLiteral d, Visitor arg) {
    printer_.println(tabs(d.getLine()) + "DoubleLiteral: " + d.getValue());
  }

  @Override
  public void visit(final EmptyExpression e, Visitor arg) {
    printer_.println(tabs(e.getLine()) + "EmptyExpression: ");
  }

  @Override
  public void visit(final FieldExpression f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "FieldExpression: ");
    tabLevel_++;
    f.getScope().ifPresent(a -> a.accept(this, arg));
    printer_.println(tabs(f.getLine()) + "Identifier: " + f.getIdentifier());
    tabLevel_--;
  }

  @Override
  public void visit(final IntegerLiteral i, Visitor arg) {
    printer_.println(tabs(i.getLine()) + "IntegerLiteral: " + i.getValue());
  }

  @Override
  public void visit(final ListExpression<?> l, Visitor arg) {
    printer_.println(tabs(l.getLine()) + "ListExpression: ");
    l.getValues().accept(this, arg);
  }

  @Override
  public void visit(final LongLiteral l, Visitor arg) {
    printer_.println(tabs(l.getLine()) + "LongLiteral: " + l.getValue());
  }

  @Override
  public void visit(final MapEntryExpression m, Visitor arg) {
    printer_.println(tabs(m.getLine()) + "MapEntryExpression:");
    tabLevel_++;
    m.getKeyExpression().accept(this, arg);
    m.getValueExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MapExpression m, Visitor arg) {
    printer_.println(tabs(m.getLine()) + "MapExpression:");
    tabLevel_++;
    m.getMapEntry().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final MethodCallExpression m, Visitor arg) {
    printer_.println(tabs(m.getLine()) + "MethodCallExpression: " + m.getIdentifier());
    tabLevel_++;
    if (m.getMethodExpression() != null) {
      m.getMethodExpression().accept(this, arg);
    }
    m.getTypeArguments().ifPresent(t -> t.accept(this, arg));
    m.getArguments().ifPresent(a -> a.accept(this, arg));
    printer_.println(tabs(m.getLine()) + "This: " + m.getImplicitThis());
    tabLevel_--;
  }

  @Override
  public void visit(final NewArrayExpression n, Visitor arg) {
    printer_.println(tabs(n.getLine()) + "NewArrayExpression:");
    tabLevel_++;
    n.getNodeType().accept(this, arg);
    n.getLevels().ifPresent(s -> s.accept(this, arg));
    if (n.getArrayInitializer() != null) {
      n.getArrayInitializer().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final NotExpression n, Visitor arg) {
    printer_.println(tabs(n.getLine()) + "NotExpression:");
    tabLevel_++;
    n.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final NullLiteral n, Visitor arg) {
    printer_.println(tabs(n.getLine()) + "NullLiteral: " + n.getValue());
  }

  @Override
  public void visit(final PostfixExpression p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "PostfixExpression:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Operator = " + p.opString());
    p.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final PrefixExpression p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "PrefixExpression:");
    tabLevel_++;
    printer_.println(tabs(p.getLine()) + "Operator = " + p.opString());
    p.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final SkipExpression s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "SkipExpression:");
  }

  @Override
  public void visit(final StopExpression s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "StopExpression:");
  }

  @Override
  public void visit(final TernaryExpression t, Visitor arg) {
    printer_.println(tabs(t.getLine()) + "TernaryExpression:");
    tabLevel_++;
    t.getCondition().accept(this, arg);
    t.getTrueExpression().accept(this, arg);
    t.getFalseExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final UnaryMinusExpression u, Visitor arg) {
    printer_.println(tabs(u.getLine()) + "UnaryMinusExpression:");
    tabLevel_++;
    u.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final UnaryPlusExpression u, Visitor arg) {
    printer_.println(tabs(u.getLine()) + "UnaryPlusExpression:");
    tabLevel_++;
    u.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final VariableExpression v, Visitor arg) {
    printer_.println(tabs(v.getLine()) + "VariableExpression: " + v.getName());
  }

  @Override
  public void visit(final FloatLiteral f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "FloatLiteral: " + f.getValue());
  }

  @Override
  public void visit(final StringLiteral s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "StringLiteral: " + s.getValue());
  }

  @Override
  public void visit(final BlockExpression b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BlockExpression:");
    tabLevel_++;
    if (b.getStatements() != null) {
      b.getStatements().forEach(s -> s.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelReadExpression c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelReadExpression:");
    tabLevel_++;
    c.getChannel().accept(this, arg);
    if (c.getArguments() != null) {
      c.getArguments().accept(this, arg);
    }
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelWriteExpression c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelWriteExpression:");
    tabLevel_++;
    c.getChannel().accept(this, arg);
    c.getArguments().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final GroupExpression g, Visitor arg) {
    printer_.println(tabs(g.getLine()) + "GroupExpression:");
    tabLevel_++;
    g.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ArrayAccess a, Visitor arg) {
    printer_.println(tabs(a.getLine()) + "ArrayAccess:");
    tabLevel_++;
    a.getName().accept(this, arg);
    a.getIndex().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final RecordLiteral r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "RecordLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    if (r.getMembers() != null) {
      r.getMembers().forEach(m -> m.accept(this, arg));
    }
    tabLevel_--;
  }

  @Override
  public void visit(final RecordMemberLiteral r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "RecordMemberLiteral:");
    tabLevel_++;
    r.getName().accept(this, arg);
    r.getExpression().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolLiteral p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProtocolLiteral:");
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
    printer_.println(tabs(a.getLine()) + "ArrayType: " + a.asString());
  }

  @Override
  public void visit(final BarrierType b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BarrierType: " + b.asString());
  }

  @Override
  public void visit(final ByteType b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "ByteType: " + b.asString());
  }

  @Override
  public void visit(final ChannelEndType c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelEndType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ChannelType c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "ChannelType: " + c.getChannelEnd());
    tabLevel_++;
    c.getComponentType().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final CharType c, Visitor arg) {
    printer_.println(tabs(c.getLine()) + "CharType: " + c.asString());
  }

  @Override
  public void visit(final DoubleType d, Visitor arg) {
    printer_.println(tabs(d.getLine()) + "DoubleType: " + d.asString());
  }

  @Override
  public void visit(final ExternalType e, Visitor arg) {
  }

  @Override
  public void visit(final FloatType f, Visitor arg) {
    printer_.println(tabs(f.getLine()) + "FloatType: " + f.asString());
  }

  @Override
  public void visit(final IntegerType i, Visitor arg) {
    printer_.println(tabs(i.getLine()) + "IntegerType: " + i.asString());
  }

  @Override
  public void visit(final LongType l, Visitor arg) {
    printer_.println(tabs(l.getLine()) + "LongType: " + l.asString());
  }

  @Override
  public void visit(final MobileType m, Visitor arg) {
  }

  @Override
  public void visit(final NullType n, Visitor arg) {
    printer_.println(tabs(n.getLine()) + "NullType: " + n.asString());
  }

  @Override
  public void visit(final ProcedureType p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProcedureType: " + p.asString());
    tabLevel_++;
    p.getReturnType().accept(this, arg);
    p.getParameterTypes().accept(this, arg);
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolTagType p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProtocolTagType: ");
    tabLevel_++;
    p.getTypeFields().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final ProtocolType p, Visitor arg) {
    printer_.println(tabs(p.getLine()) + "ProtocolType: " + p.getName());
    tabLevel_++;
    p.getFieldTypes().ifPresent(f -> f.accept(this, arg));
    tabLevel_--;
  }

  @Override
  public void visit(final RecordType r, Visitor arg) {
    printer_.println(tabs(r.getLine()) + "RecordType: " + r.getName());
  }

  @Override
  public void visit(final ShortType s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "ShortType: short");
  }

  @Override
  public void visit(final BooleanType b, Visitor arg) {
    printer_.println(tabs(b.getLine()) + "BooleanType: boolean");
  }

  @Override
  public void visit(final StringType s, Visitor arg) {
    printer_.println(tabs(s.getLine()) + "StringType: string");
  }

  @Override
  public void visit(final TimerType t, Visitor arg) {
    printer_.println(tabs(t.getLine()) + "TimerType: timer");
  }

  @Override
  public void visit(final UnknownType u, Visitor arg) {
    printer_.println(tabs(u.getLine()) + "UnknownType: ?");
  }

  @Override
  public void visit(final VoidType v, Visitor arg) {
    printer_.println(tabs(v.getLine()) + "VoidType: void");
  }

  @Override
  public void visit(final NamedType t, Visitor arg) {
    printer_.println(tabs(t.getLine()) + "TypeVariable:");
    tabLevel_++;
    t.getName().accept(this, arg);
    tabLevel_--;
  }
}
