/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namechecker;

import ast.AST;
import ast.AltCase;
import ast.AltStat;
import ast.Annotation;
import ast.Annotations;
import ast.ArrayAccessExpr;
import ast.ArrayLiteral;
import ast.ArrayType;
import ast.Assignment;
import ast.BinaryExpr;
import ast.Block;
import ast.BreakStat;
import ast.CastExpr;
import ast.ChannelEndExpr;
import ast.ChannelEndType;
import ast.ChannelReadExpr;
import ast.ChannelType;
import ast.ChannelWriteStat;
import ast.ClaimStat;
import ast.Compilation;
import ast.ConstantDecl;
import ast.ContinueStat;
import ast.DoStat;
import ast.ErrorType;
import ast.ExprStat;
import ast.Expression;
import ast.ExternType;
import ast.ForStat;
import ast.Guard;
import ast.IfStat;
import ast.ImplicitImport;
import ast.Import;
import ast.Invocation;
import ast.LocalDecl;
import ast.Modifier;
import ast.Name;
import ast.NameExpr;
import ast.NamedType;
import ast.NewArray;
import ast.NewMobile;
import ast.ParBlock;
import ast.ParamDecl;
import ast.Pragma;
import ast.PrimitiveLiteral;
import ast.PrimitiveType;
import ast.ProcTypeDecl;
import ast.ProtocolCase;
import ast.ProtocolLiteral;
import ast.ProtocolTypeDecl;
import ast.QualifiedName;
import ast.RecordAccess;
import ast.RecordLiteral;
import ast.RecordMember;
import ast.RecordMemberLiteral;
import ast.RecordTypeDecl;
import ast.ReturnStat;
import ast.Sequence;
import ast.SkipStat;
import ast.Statement;
import ast.StopStat;
import ast.SuspendStat;
import ast.SwitchGroup;
import ast.SwitchLabel;
import ast.SwitchStat;
import ast.SyncStat;
import ast.Ternary;
import ast.TimeoutStat;
import ast.UnaryPostExpr;
import ast.UnaryPreExpr;
import ast.Var;
import ast.WhileStat;
import utilities.Log;
import utilities.PJBugManager;
import utilities.Visitor;

/**
 *
 * @author ben
 */
public class ScopeResolution extends Visitor<AST> {
    
    // **********************************************************************
    // * Top-Level declarations are handled in TopLevelDecls.
    // **********************************************************************
    
    private Compilation currentCompilation;
    
    public AST visitAnnotation(Annotation at) {
        at.myCompilation = currentCompilation;
        return null;
    }

    public AST visitAnnotations(Annotations as) {
        return null;
    }

    public AST visitAltCase(AltCase ac) {
        ac.myCompilation = currentCompilation;
        if (ac.precondition() != null)
            ac.precondition().myCompilation = currentCompilation;
        ac.guard().guard().myCompilation = currentCompilation;
        if (ac.stat() != null) {
            ac.stat().myCompilation = currentCompilation;
            ac.stat().visit(this);
        }
        return null;
    }

    public AST visitAltStat(AltStat as) {
        as.myCompilation = currentCompilation;
        as.body().visit(this);
        return as.visitChildren(this);
    }

    public AST visitArrayAccessExpr(ArrayAccessExpr ae) {
        return null;
    }

    public AST visitArrayLiteral(ArrayLiteral al) {
        return null;
    }

    public AST visitArrayType(ArrayType at) {
        return null;
    }

    public AST visitAssignment(Assignment as) {
        as.myCompilation = currentCompilation;
        as.left().visit(this);
        as.right().visit(this);
        return null;
    }

    public AST visitBinaryExpr(BinaryExpr be) {
        be.myCompilation = currentCompilation;
        be.left().visit(this);
        be.right().visit(this);
        return null;
    }

    public AST visitBlock(Block bl) {
        bl.myCompilation = currentCompilation;
        Sequence<Statement> stats = bl.stats();
        for (int i = 0; i < stats.size(); ++i)
            stats.child(i).visit(this);
        return null;
    }

    public AST visitBreakStat(BreakStat bs) {
        bs.myCompilation = currentCompilation;
        if (bs.target() != null)
            bs.target().visit(this);
        return null;
    }

    public AST visitCastExpr(CastExpr ce) {
        ce.myCompilation = currentCompilation;
        ce.type().visit(this);
        ce.expr().visit(this);
        return null;
    }

    public AST visitChannelType(ChannelType ct) {
        return ct.visitChildren(this);
    }

    public AST visitChannelEndExpr(ChannelEndExpr ce) {
        return ce.visitChildren(this);
    }

    public AST visitChannelEndType(ChannelEndType ct) {
        return ct.visitChildren(this);
    }

    public AST visitChannelReadExpr(ChannelReadExpr cr) {
        return cr.visitChildren(this);
    }

    public AST visitChannelWriteStat(ChannelWriteStat cw) {
        return cw.visitChildren(this);
    }

    public AST visitClaimStat(ClaimStat cs) {
        return cs.visitChildren(this);
    }

    public AST visitCompilation(Compilation co) {
        Log.log(" Visiting type declarations for " + PJBugManager.INSTANCE.getFileName());
        currentCompilation = co;
        co.typeDecls().visit(this);
        return null;
    }

    public AST visitConstantDecl(ConstantDecl cd) {
        return cd.visitChildren(this);
    }

    public AST visitContinueStat(ContinueStat cs) {
        return null;
    }

    public AST visitDoStat(DoStat ds) {
        return null;
    }

    public AST visitErrorType(ErrorType et) {
        return null;
    }

    public AST visitExprStat(ExprStat es) {
        return null;
    }

    public AST visitExternType(ExternType et) {
        return null;
    }

    public AST visitForStat(ForStat fs) {
        return null;
    }

    public AST visitGuard(Guard gu) {
        return null;
    }

    public AST visitIfStat(IfStat is) {
        return null;
    }

    public AST visitImplicitImport(ImplicitImport ii) {
        return null;
    }

    public AST visitImport(Import im) {
        return null;
    }

    public AST visitInvocation(Invocation in) {
        return null;
    }

    public AST visitLocalDecl(LocalDecl ld) {
        ld.type().visit(this);
        ld.var().visit(this);
        return null;
    }

    public AST visitModifier(Modifier mo) {
        mo.myCompilation = currentCompilation;
        return null;
    }

    public AST visitName(Name na) {
        if (na.c != null) // Otherwise it was resolved elsewhere
            na.myCompilation = na.c;
        else
            na.myCompilation = currentCompilation;
        return null;
    }

    public AST visitNamedType(NamedType nt) {
        return nt.visitChildren(this);
    }

    public AST visitNameExpr(NameExpr ne) {
        ne.myCompilation = currentCompilation;
        ne.name().visit(this);
        return null;
    }

    public AST visitNewArray(NewArray ne) {
        return ne.visitChildren(this);
    }

    public AST visitNewMobile(NewMobile nm) {
        return null;
    }

    public AST visitParamDecl(ParamDecl pd) {
        pd.myCompilation = currentCompilation;
        pd.type().visit(this);
        pd.paramName().visit(this);
        return null;
    }
    
    public AST visitParBlock(ParBlock pb) {
        pb.myCompilation = currentCompilation;
        Sequence<Statement> stats = pb.stats();
        for (int i = 0; i < stats.size(); ++i)
            stats.child(i).visit(this);
        Sequence<Expression> expr = pb.barriers();
        for (int i = 0; i < expr.size(); ++i)
            expr.child(i).visit(this);
        return null;
    }

    public AST visitPragma(Pragma pr) {
        pr.pname().visit(this);
        return null;
    }

    public AST visitPrimitiveLiteral(PrimitiveLiteral li) {
        li.myCompilation = currentCompilation;
        return null;
    }

    public AST visitPrimitiveType(PrimitiveType py) {
        py.myCompilation = currentCompilation;
        return null;
    }

    public AST visitProcTypeDecl(ProcTypeDecl pd) {
        return super.visitProcTypeDecl(pd);
    }

    public AST visitProtocolLiteral(ProtocolLiteral pl) {
        return pl.visitChildren(this);
    }

    public AST visitProtocolCase(ProtocolCase pc) {
        return pc.visitChildren(this);
    }

    public AST visitProtocolTypeDecl(ProtocolTypeDecl pd) {
        return pd.visitChildren(this);
    }
    
    public AST visitQualifiedName(QualifiedName qn) {
        return null;
    }

    public AST visitRecordAccess(RecordAccess ra) {
        return null;
    }
    
    public AST visitRecordMemberLiteral(RecordMemberLiteral rm) {
        return null;
    }

    public AST visitRecordLiteral(RecordLiteral rl) {
        return null;
    }

    public AST visitRecordMember(RecordMember rm) {
        return null;
    }

    public AST visitRecordTypeDecl(RecordTypeDecl rt) {
        return null;
    }

    public AST visitReturnStat(ReturnStat rs) {
        if (rs.expr() != null)
            rs.expr().visit(this);
        return null;
    }

    public AST visitSequence(Sequence se) {
        for (int i = 0; i < se.size(); i++)
            if (se.child(i) != null)
                se.child(i).visit(this);
        return null;
    }

    public AST visitSkipStat(SkipStat ss) {
        return null;
    }

    public AST visitStopStat(StopStat ss) {
        return null;
    }

    public AST visitSuspendStat(SuspendStat ss) {
        return null;
    }

    public AST visitSwitchGroup(SwitchGroup sg) {
        sg.myCompilation = currentCompilation;
        sg.labels().visit(this);
        sg.statements().visit(this);
        return null;
    }

    public AST visitSwitchLabel(SwitchLabel sl) {
        sl.myCompilation = currentCompilation;
        sl.expr().visit(this);
        return null;
    }

    public AST visitSwitchStat(SwitchStat st) {
        st.myCompilation = currentCompilation;
        st.expr().visit(this);
        if (st.switchBlocks() != null)
            st.switchBlocks().visit(this);
        return null;
    }

    public AST visitSyncStat(SyncStat st) {
        return null;
    }

    public AST visitTernary(Ternary te) {
        te.myCompilation = currentCompilation;
        te.expr().visit(this);
        if (te.trueBranch() != null)
            te.trueBranch().visit(this);
        if (te.falseBranch() != null)
            te.falseBranch().visit(this);
        return null;
    }

    public AST visitTimeoutStat(TimeoutStat ts) {
        ts.myCompilation = currentCompilation;
        ts.timer().visit(this);
        ts.delay().visit(this);
        return null;
    }

    public AST visitUnaryPostExpr(UnaryPostExpr up) {
        up.myCompilation = currentCompilation;
        if (up.expr() != null)
            up.expr().visit(this);
        return null;
    }

    public AST visitUnaryPreExpr(UnaryPreExpr up) {
        up.myCompilation = currentCompilation;
        if (up.expr() != null)
            up.expr().visit(this);
        return null;
    }

    public AST visitVar(Var va) {
        va.myCompilation = currentCompilation;
        va.name().visit(this);
        if (va.init() != null)
            va.init().visit(this);
        return null;
    }

    public AST visitWhileStat(WhileStat ws) {
        ws.myCompilation = currentCompilation;
        ws.expr().visit(this);
        if (ws.stat() != null)
            ws.stat().visit(this);
        return null;
    }
}
