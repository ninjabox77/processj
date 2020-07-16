package rewriters;

import ast.AST;
import ast.AltCase;
import ast.AltStat;
import ast.ArrayAccessExpr;
import ast.Assignment;
import ast.BinaryExpr;
import ast.Block;
import ast.CastExpr;
import ast.ChannelEndExpr;
import ast.ChannelReadExpr;
import ast.ChannelWriteStat;
import ast.Compilation;
import ast.ExprStat;
import ast.Expression;
import ast.IfStat;
import ast.Invocation;
import ast.LocalDecl;
import ast.Name;
import ast.NameExpr;
import ast.ParBlock;
import ast.ProcTypeDecl;
import ast.Sequence;
import ast.Statement;
import ast.SwitchGroup;
import ast.SwitchStat;
import ast.Ternary;
import ast.TimeoutStat;
import ast.Type;
import ast.UnaryPostExpr;
import ast.UnaryPreExpr;
import ast.Var;
import printers.PrettyPrinter;
import utilities.Log;
import utilities.Pair;
import utilities.Visitor;

/**
 * This rewriter returns a Pair of two elements: Sequence and Expression,
 * where the first element is the extra generated statements and the second
 * is the expression that the rewrite resulted in (if any). If the first
 * element is empty, then the second element is entry result. However, if
 * the first element is null, then no expression was returned.
 * 
 * Note, we implement a function ρ(.) which rewrites statements and expressions
 * as follow. ρ(.) = ([stmts], expr), where [...] is a sequence of statements.
 * If ρ is applied to a statement, the expr part will _always_ be null. The
 * [stmts] part will contain any necessary code in order to rewrite the
 * original statement or expression in such a way that all occurrences for
 * channel read expression (e.g., e.read()) _only_ ever appear on the RHS of
 * an assignment all by itself. For example, ρ(c.read() + d.read()) results
 * in ([T1 t1; t1 = c.read(); T2 t2; t2 = d.read();], t1 + t2). It should be
 * noted that when applying ρ to an expression, the second part of the
 * resulting pair will be the rewritten version of that expression. If the
 * above binary expression with channel reads were on the RHS of a simple
 * such as: x = c.read() + d.read(), the rewritten code would be:
 * 
 * T1 t1; t1 = c.read(); T2 t2; t2 = d.read(); c = t1 + t2;
 * 
 * @author ben
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ChannelRead extends Visitor<Pair<Sequence, Expression>> {

    int temp;

    private String nextTemp() {
        return "tmp" + temp++ + "$";
    }
    
    private LocalDecl createLocalDecl(String name, Type type) {
        return new LocalDecl(type, new Var(new Name(name), null), false);
    }
    
    private Assignment createAssignment(String name, Expression expr) {
        return new Assignment(new NameExpr(new Name(name)), expr, Assignment.EQ);
    }
    
    public ChannelRead() {
        System.out.println("*****************************************");
        System.out.println("* C H A N N E L - E N D   R E W R I T E *");
        System.out.println("*****************************************");
    }

    @Override
    public Pair<Sequence, Expression> visitCompilation(Compilation co) {
        System.out.println("Visiting Compilation");
        // TODO: Don't wee need to traverse everything else??
        for (AST decl : co.typeDecls()) {
            if (decl instanceof Type && decl != null) {
                Type t = ((Type) decl);
                if (t instanceof ProcTypeDecl)
                    t.visit(this);
            }
        }
        return (Pair<Sequence, Expression>) null;
    }

    @Override
    public Pair<Sequence, Expression> visitProcTypeDecl(ProcTypeDecl pd) {
        System.out.println("Visiting a ProcTypeDecl");
        Pair<Sequence, Expression> p = pd.body().visit(this);
        if (p != null)
            pd.children[6] = p.getFirst().child(0);
        pd.visit(new PrettyPrinter());
        return (Pair<Sequence, Expression>) null;
    }

    @Override
    public Pair<Sequence, Expression> visitSequence(Sequence se) {
        System.out.println("Visiting a Sequence");
        Sequence s = new Sequence();
        for (int i = 0; i < se.size(); ++i) {
            Pair<Sequence, Expression> p = se.child(i).visit(this);
            if (p != null) {
                if (p.getFirst().size() > 1) {
                    s.merge(new Block(p.getFirst()));
                    System.out.println(">>>>>>>>>>>>>> Sequence");
                    p.getFirst().child(0).visit(new PrettyPrinter());
                    System.out.println("<<<<<<<<<<<<<< Sequence");
                } else {
                    System.out.println("We have an expression in Sequence!!!!!");
                    s.merge(p.getFirst().child(0));
                }
            } else {
                // No rewrite needed for this section of code :-)
                if (se.child(i) instanceof Sequence)
                    s.merge((Sequence) se.child(i));
                else
                    s.append(se.child(i));
            }
        }
        return new Pair<>(s, null);
    }
    
    @Override
    public Pair<Sequence, Expression> visitBlock(Block bl) {
        System.out.println("Visiting a Block");
        Pair<Sequence, Expression> p = bl.stats().visit(this);
        Sequence se = new Sequence(new Block(p.getFirst()));
        return new Pair<Sequence, Expression>(se, null);
    }

    @Override
    public Pair<Sequence, Expression> visitParBlock(ParBlock pb) {
        System.out.println("Visiting a ParBlock");
        Sequence se = pb.stats();
        for (int i = 0; i < se.size(); ++i) {
            Pair<Sequence, Expression> p = se.child(i).visit(this);
            if (p != null) {
                if (p.getFirst().size() > 1) {
                    se.set(i, new Block(p.getFirst()));
                    System.out.println(">>>>>>>>>>>>>> ParBlock");
                    p.getFirst().visit(new PrettyPrinter());
                    System.out.println("<<<<<<<<<<<<<< ParBlock");
                } else {
                    System.out.println("We have an expression in ParBlock!!!!!");
                    se.set(i, p.getFirst().child(0));
                }
            }
        }
        se = new Sequence(new ParBlock(se, pb.barriers()));
        return new Pair<Sequence, Expression>(se, null);
    }

    @Override
    public Pair<Sequence, Expression> visitAssignment(Assignment as) {
        System.out.println("Visiting an Assignment");
        Pair<Sequence, Expression> p = null;
        if (as.right().doesYield()) {
            System.out.println("---- Case #1 Assignment");
            Pair<Sequence, Expression> t = as.right().visit(this);
            p = new Pair<>(t.getFirst(), new Assignment(as.left(), t.getSecond(), as.op()));
        } else {
            System.out.println("---- Case #2 Assignment");
            p = new Pair<>(new Sequence(), as);
        }
        return p;
    }

    @Override
    public Pair<Sequence, Expression> visitExprStat(ExprStat es) {
        System.out.println("Visiting an ExprStat");
        Pair<Sequence, Expression> p = es.expr().visit(this);
        Sequence se = p.getFirst();
        se.append((Statement) new ExprStat(p.getSecond()));
        System.out.println(">>>>>>>>>>>>>>>>>>> ExprStat");
        se.visit(new PrettyPrinter());
        System.out.println("------->");
        p.getSecond().visit(new PrettyPrinter());
        System.out.println("<<<<<<<<<<<<<<<<<<< ExprStat");
        return new Pair<Sequence, Expression>(se, null);
    }

    @Override
    public Pair<Sequence, Expression> visitBinaryExpr(BinaryExpr be) {
        System.out.println("Visiting a BinaryExpr");
        Pair<Sequence, Expression> p = null;
        if (be.left().doesYield() && !be.right().doesYield()) {
            System.out.println("---- Case #1 BinaryExpr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, be.left().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, be.left())).visit(this);
            // Make t <op> e
            BinaryExpr newExpr = new BinaryExpr(new NameExpr(new Name(name)), be.right(), be.op());
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            p = new Pair<>(se, newExpr);
        } else if (be.right().doesYield()) {
            System.out.println("---- Case #2 BinaryExpr");
            String name1 = nextTemp();
            String name2 = nextTemp();
            // T t1; where T represents the type of e1
            LocalDecl ld1 = createLocalDecl(name1, be.left().type);
            // T t2; where T represents the type of e2
            LocalDecl ld2 = createLocalDecl(name2, be.right().type);
            // Rewrite the expression to t1 = e1;
            Pair<Sequence, Expression> t1 = new ExprStat(createAssignment(name1, be.left())).visit(this);
            // Rewrite the expression to t2 = e2;
            Pair<Sequence, Expression> t2 = new ExprStat(createAssignment(name2, be.right())).visit(this);
            // Make t1 <op> t2
            NameExpr ne1 = new NameExpr(new Name(name1));
            ne1.type = ld1.type();
            NameExpr ne2 = new NameExpr(new Name(name2));
            ne2.type = ld2.type();
            BinaryExpr newExpr = new BinaryExpr(ne1, ne2, be.op());
            Sequence se = new Sequence(ld1);
            se.append(ld2);
            se.merge(t1.getFirst());
            se.merge(t2.getFirst());
            p = new Pair<>(se, newExpr);
        } else {
            System.out.println("---- Case #2 BinaryExpr");
            p = new Pair<>(new Sequence(), be);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitChannelReadExpr(ChannelReadExpr cr) {
        System.out.println("Visiting a ChannelReadExpr");
        Pair<Sequence, Expression> p = null;
        if (cr.channel().doesYield()) {
            System.out.println("---- Case #1 ChannelReadExpr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, cr.channel().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, cr.channel())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            // Have extended rendezvous?
            Block extRV = null;
            if (cr.extRV() != null) {
                Pair<Sequence, Expression> rv = cr.extRV().visit(this);
                extRV = (Block) rv.getFirst().child(0);
            }
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            p = new Pair<>(se, new ChannelReadExpr(ne, extRV));
        } else {
            System.out.println("---- Case #2 ChannelReadExpr");
            p = new Pair<>(new Sequence(), cr);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitChannelWriteStat(ChannelWriteStat cw) {
        System.out.println("Visiting a ChannelWriteStat");
        Pair<Sequence, Expression> p = null;
        if (cw.channel().doesYield() && !cw.expr().doesYield()) {
            System.out.println("---- Case #1 ChannelWriteStat");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, cw.channel().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, cw.channel())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            p = new Pair<>(se, null);
        } else if (cw.expr().doesYield()) {
            System.out.println("---- Case #2 ChannelWriteStat");
            String name1 = nextTemp();
            String name2 = nextTemp();
            // T t1; where T represents the type of e1
            LocalDecl ld1 = createLocalDecl(name1, cw.channel().type);
            // T t2; where T represents the type of e2
            LocalDecl ld2 = createLocalDecl(name2, cw.expr().type);
            // Rewrite the expression to t1 = e1;
            Pair<Sequence, Expression> t1 = new ExprStat(createAssignment(name1, cw.channel())).visit(this);
            // Rewrite the expression to t2 = e2;
            Pair<Sequence, Expression> t2 = new ExprStat(createAssignment(name2, cw.expr())).visit(this);
            Sequence se = new Sequence(ld1);
            se.merge(t1.getFirst());
            se.append(ld2);
            se.merge(t2.getFirst());
            // TODO: THIS BREAKS!!!!!!
            NameExpr ne1 = new NameExpr(new Name(name1));
            ne1.type = ld1.type();
            NameExpr ne2 = new NameExpr(new Name(name2));
            ne2.type = ld2.type();
            se.append(new ChannelWriteStat(ne1, ne2));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            se.visit(new PrettyPrinter());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            p = new Pair<>(se, null);
        } else {
            System.out.println("---- Case #3 ChannelWriteStat");
            p = new Pair<>(new Sequence(cw), null);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitLocalDecl(LocalDecl ld) {
        System.out.println("Visiting a LocalDecl");
        Pair<Sequence, Expression> p = null;
        if (ld.var().init() == null || (ld.var().init() != null && !ld.var().init().doesYield())) {
            System.out.println("---- Case #1 LocalDecl");
            p = new Pair<>(new Sequence(ld), null);
        } else {
            System.out.println("---- Case #2 LocalDecl");
            LocalDecl ld1 = createLocalDecl(ld.var().name().getname(), ld.type());
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(ld.var().name().getname(), ld.var().init())).visit(this);
            Sequence se = new Sequence(ld1);
            se.merge(t.getFirst());
            p = new Pair<>(se, null);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitCastExpr(CastExpr ce) {
        System.out.println("Visiting a CastExpr");
        Pair<Sequence, Expression> p = null;
        if (ce.expr().doesYield()) {
            System.out.println("---- Case #1 CastExpr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, ce.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, ce.expr())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            p = new Pair<>(se, new CastExpr(ce.type(), ne));
        } else {
            System.out.println("---- Case #2 CastExpr");
            p = new Pair<>(new Sequence(), ce);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitIfStat(IfStat is) {
        System.out.println("Visiting an IfStat");
        Pair<Sequence, Expression> p = null;
        Sequence se = new Sequence();
        if (is.expr().doesYield()) {
            System.out.println("---- Case #1 IfStat: then-part");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, is.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, is.expr())).visit(this);
            se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            is.children[0] = ne;
        }
        // If we skip the first part, then expr() does not yield!
        // Now check the else-part which it is already a Block
        Pair<Sequence, Expression> elsepart = null;
        if (is.elsepart() != null) {
            System.out.println("---- Case #2 IfStat: else-part");
            elsepart = is.elsepart().visit(this);
            Sequence stmt = elsepart.getFirst();
            System.out.println(">>>>>>>> IfState - else");
            stmt.visit(new PrettyPrinter());
            System.out.println("<<<<<<<< IfState - else");
            if (stmt.size() > 1)
                is.children[2] = new Block(stmt);
            else
                is.children[2] = stmt.child(0);
        }
        se.append(is);
        p = new Pair<>(se, null);
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitInvocation(Invocation in) {
        System.out.println("Visiting an Invocation");
        // TODO: remember to handle mobiles!!
        Pair<Sequence, Expression> p = null;
        Sequence<Expression> params = in.params();
        int yieldPos = -1;
        for (int i = params.size() - 1; i >= 0; --i)
            if (params.child(i).doesYield())
                yieldPos = Math.max(yieldPos, i);
        if (in.doesYield() && yieldPos >= 0){
            System.out.println("---- Case #2 Invocation");
            Sequence se = new Sequence();
            for (int i = 0; i <= yieldPos; ++i) {
                Expression e = params.child(i);
                String name = nextTemp();
                // T t; where T represents the type of e
                LocalDecl ld = createLocalDecl(name, e.type);
                // Rewrite the expression to t = e;
                Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, e)).visit(this);
                se.append(ld);
                se.merge(t.getFirst());
                NameExpr ne = new NameExpr(new Name(name));
                ne.type = ld.type();
                params.set(i, ne);
            }
            p = new Pair<>(se, in);
        } else {
            System.out.println("---- Case #3 Invocation");
            p = new Pair<>(new Sequence(), in);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitArrayAccessExpr(ArrayAccessExpr ae) {
        System.out.println("Visiting an ArrayAccessExpr");
        Pair<Sequence, Expression> p = null;
        if (ae.target().doesYield() && !ae.index().doesYield()) {
            System.out.println("---- Case #1 ArrayAccessExpr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, ae.target().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, ae.target())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            p = new Pair<>(se, new ArrayAccessExpr(new NameExpr(new Name(name)), ae.index()));
        } else if (ae.index().doesYield()) {
            System.out.println("---- Case #2 ArrayAccessExpr");
            String name1 = nextTemp();
            String name2 = nextTemp();
            // T t1; where T represents the type of e1
            LocalDecl ld1 = createLocalDecl(name1, ae.target().type);
            // Rewrite the expression to t1 = e1;
            Pair<Sequence, Expression> t1 = new ExprStat(createAssignment(name1, ae.target())).visit(this);
            // T t2; where T represents the type of e2
            LocalDecl ld2 = createLocalDecl(name2, ae.index().type);
            // Rewrite the expression to t2 = e2;
            Pair<Sequence, Expression> t2 = new ExprStat(createAssignment(name2, ae.index())).visit(this);
            Sequence se = new Sequence(ld1);
            se.merge(t1.getFirst());
            se.append(ld2);
            se.merge(t2.getFirst());
            NameExpr ne1 = new NameExpr(new Name(name1));
            ne1.type = ld1.type();
            NameExpr ne2 = new NameExpr(new Name(name2));
            ne2.type = ld2.type();
            p = new Pair<>(se, new ArrayAccessExpr(ne1, ne2));
        } else {
            System.out.println("---- Case #3 ArrayAccessExpr");
            p = new Pair<>(new Sequence(), ae);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitChannelEndExpr(ChannelEndExpr ce) {
        System.out.println("Visiting a ChannelEndExpr");
        Pair<Sequence, Expression> p = null;
        if (ce.channel().doesYield()) {
            System.out.println("---- Case #1");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, ce.channel().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, ce.channel())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            p = new Pair<>(se, new ChannelEndExpr(ne, ce.endType()));
        } else {
            System.out.println("---- Case #1");
            p = new Pair<>(new Sequence(), ce);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitSwitchStat(SwitchStat st) {
        System.out.println("Visiting a SwitchStat");
        Pair<Sequence, Expression> p = null;
        Sequence se = new Sequence();
        if (st.expr().doesYield()) {
            System.out.println("---- Case #1 SwitchStat: expr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, st.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, st.expr())).visit(this);
            se.append(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            st.children[0] = ne;
        }
        // If we skip the first part, then expr() does not yield!
        // Now check the else-part which it is already a Block
        if (st.switchBlocks().size() > 0) {
            System.out.println("---- Case #2 SwitchStat: switch-block");
            Sequence<SwitchGroup> sg = st.switchBlocks();
            for (int i = 0; i < sg.size(); ++i)
                sg.child(i).visit(this);
        }
        se.append(st);
        p = new Pair<>(se, null);
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitSwitchGroup(SwitchGroup sg) {
        System.out.println("Visiting a SwitchGroup");
        Sequence<Statement> se = sg.statements();
        for (int i = 0; i < se.size(); ++i) {
            Pair<Sequence, Expression> p = se.child(i).visit(this);
            if (p != null) {
                if (p.getFirst().size() > 1)
                    se.set(i, new Block(p.getFirst()));
                else
                    se.set(i, (Statement) p.getFirst().child(0));
            }
        }
        return (Pair<Sequence, Expression>) null;
    }
    
    @Override
    public Pair<Sequence, Expression> visitTernary(Ternary te) {
        System.out.println("Visiting a Ternary");
        if (te.trueBranch().doesYield() || te.falseBranch().doesYield())
            ; // TODO: Throw error message
        Pair<Sequence, Expression> p = null;
        if (te.expr().doesYield()) {
            System.out.println("---- Case #1 Ternary");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, te.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, te.expr())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            p = new Pair<>(se, new Ternary(ne, te.trueBranch(), te.falseBranch()));
        } else {
            System.out.println("---- Case #1 Ternary");
            p = new Pair<>(new Sequence(), te);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitTimeoutStat(TimeoutStat ts) {
        System.out.println("Visiting a TimeoutStat");
        Pair<Sequence, Expression> p = null;
        if (ts.timer().doesYield() && !ts.delay().doesYield()) {
            System.out.println("---- Case #1 TimeoutStat");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, ts.timer().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, ts.timer())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            se.append(new TimeoutStat(ne, ts.delay()));
            p = new Pair<>(se, null);
        } else if (ts.delay().doesYield()) {
            System.out.println("---- Case #2 TimeoutStat");
            String name1 = nextTemp();
            String name2 = nextTemp();
            // T t1; where T represents the type of e1
            LocalDecl ld1 = createLocalDecl(name1, ts.timer().type);
            // Rewrite the expression to t1 = e1;
            Pair<Sequence, Expression> t1 = new ExprStat(createAssignment(name1, ts.timer())).visit(this);
            // T t2; where T represents the type of e2
            LocalDecl ld2 = createLocalDecl(name2, ts.delay().type);
            // Rewrite the expression to t2 = e2;
            Pair<Sequence, Expression> t2 = new ExprStat(createAssignment(name2, ts.delay())).visit(this);
            Sequence se = new Sequence(ld1);
            se.merge(t1.getFirst());
            se.append(ld2);
            se.merge(t2.getFirst());
            NameExpr ne1 = new NameExpr(new Name(name1));
            ne1.type = ld1.type();
            NameExpr ne2 = new NameExpr(new Name(name2));
            ne2.type = ld2.type();
            se.append(new TimeoutStat(ne1, ne2));
            p = new Pair<>(se, null);
        } else {
            System.out.println("---- Case #3 TimeoutStat");
            p = new Pair<>(new Sequence(ts), null);
        }
        return p;
    }
    
    @Override
    public Pair<Sequence, Expression> visitUnaryPostExpr(UnaryPostExpr up) {
        System.out.println("Visiting a UnaryPostExpr");
        if (up.expr().doesYield())
            ; // TODO: Throw error message
        return new Pair<>(new Sequence(), up);
    }
    
    @Override
    public Pair<Sequence, Expression> visitUnaryPreExpr(UnaryPreExpr up) {
        System.out.println("Visitng a UnaryPreExpr");
        if (UnaryPreExpr.PLUSPLUS == up.op() || UnaryPreExpr.MINUSMINUS == up.op())
            ; // TODO: Throw error message
        Pair<Sequence, Expression> p = null;
        if (up.doesYield()) {
            System.out.println("---- Case #1 UnaryPreExpr");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, up.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, up.expr())).visit(this);
            Sequence se = new Sequence(ld);
            se.merge(t.getFirst());
            NameExpr ne = new NameExpr(new Name(name));
            ne.type = ld.type();
            p = new Pair<>(se, new UnaryPreExpr(ne, up.op()));
        } else {
            System.out.println("---- Case #2 UnaryPreExpr");
            p = new Pair<>(new Sequence(), up);
        }
        return p;
    }
    
//    @Override
//    public Pair<Sequence, Expression> visitAltStat(AltStat as) {
//        System.out.println("Visiting an AltStat");
//        Pair<Sequence, Expression> p = null;
//        // TODO:
//        return p;
//    }
//    
//    @Override
//    public Pair<Sequence, Expression> visitAltCase(AltCase ac) {
//        System.out.println("Visiting an AltCase");
//        Pair<Sequence, Expression> p = null;
//        return p;
//    }
    
    // TODO: Record
    // TODO: Protocol
    // TODO: ReturnStat -- Should processes return a value??
}
