package rewriters;

import ast.AST;
import ast.Assignment;
import ast.BinaryExpr;
import ast.Block;
import ast.CastExpr;
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
import ast.Type;
import ast.Var;
import printers.PrettyPrinter;
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
        System.out.println("****************************************");
        System.out.println("*C H A N N E L - E N D   R E W R I T E *");
        System.out.println("****************************************");
    }

    @Override
    public Pair<Sequence, Expression> visitCompilation(Compilation co) {
        System.out.println("Visiting Compilation");
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
        p.getFirst().visit(new PrettyPrinter());
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
//                    s.merge(p.getSecond());
                }
            }
        }
        return new Pair<Sequence, Expression>(s, null);
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
                if (p.getFirst().size() > 0) {
                    se.set(i, new Block(p.getFirst()));
                    System.out.println(">>>>>>>>>>>>>> ParBlock");
                    p.getFirst().visit(new PrettyPrinter());
                    System.out.println("<<<<<<<<<<<<<< ParBlock");
                } else
                    System.out.println("We have an expression in ParBlock!!!!!");
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
            Assignment ass = new Assignment(as.left(), t.getSecond(), as.op());
            p = new Pair<>(t.getFirst(), ass);
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
            BinaryExpr newExpr = new BinaryExpr(new NameExpr(new Name(name1)), new NameExpr(new Name(name2)), be.op());
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
            p = new Pair<>(se, new ChannelReadExpr(new NameExpr(new Name(name)), extRV));
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
            // TODO:
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
            se.append(new ChannelWriteStat(new NameExpr(new Name(name1)), new NameExpr(new Name(name2))));
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
            ce.children[1] = new NameExpr(new Name(name));
            p = new Pair<>(se, ce);
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
            System.out.println("---- Case #1 IfStat");
            String name = nextTemp();
            // T t; where T represents the type of e
            LocalDecl ld = createLocalDecl(name, is.expr().type);
            // Rewrite the expression to t = e;
            Pair<Sequence, Expression> t = new ExprStat(createAssignment(name, is.expr())).visit(this);
            se = new Sequence(ld);
            se.merge(t.getFirst());
            is.children[0] = new NameExpr(new Name(name));
        }
        // If we get here, then expr() does not yield! Now check the 'else'
        // part, which it is already a Block
        Pair<Sequence, Expression> elsepart = null; 
        if (is.elsepart() != null) {
            System.out.println("---- Case #1 IfStat: Subcase #1");
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
}
