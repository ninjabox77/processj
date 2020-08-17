/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysischecker;

import ast.AST;
import ast.BinaryExpr;
import ast.ExprStat;
import ast.Token;
import java.util.HashMap;
import java.util.Iterator;
import utilities.ANSICode;
import utilities.ErrorSeverity;
import utilities.PJUtil;
import utilities.Settings;
import utilities.Visitor;

/**
 *
 * @author ben
 */
public enum ASTStringCompiler {

    INSTANCE;

    int hashValue; // the absolute path of a source file.
    boolean alreadyParsed = false;

    private HashMap<Integer, ASTTree> compilation = new HashMap<>();

    public void setFile(String file) {
        hashValue = file.hashCode();
        if (!compilation.containsKey(hashValue)) {
            compilation.put(hashValue, new ASTTree());
            alreadyParsed = false;
        } else {
            alreadyParsed = true;
        }
    }

    public void add(ASTNode n) {
        if (!alreadyParsed) {
            compilation.get(hashValue).add(n);
        }
    }

    public ASTTree get(String file) {
        return compilation.get(file.hashCode());
    }

    public boolean contains(String file) {
        return compilation.containsKey(file.hashCode());
    }

    public void print() {
        Iterator<Integer> it = compilation.keySet().iterator();
        while (it.hasNext()) {
            compilation.get(it.next()).print();
        }
    }

    public String codeAnalysis(AST ast, String message, String link, ErrorSeverity severity) {
        StringBuilder sb1 = new StringBuilder();
        ASTTree tree = compilation.get(ast.myCompilation.absolutePath().hashCode());
        ASTNode node = tree.get(ast.token.line);
        if (node != null) {
            int padding = tree.getLineNumberWidth();
            String whitespace = PJUtil.addChar(' ', padding);
            sb1.append(String.format("%" + padding + "s", whitespace));
            sb1.append(" | \n");
            sb1.append(String.format("%" + padding + "s", node.getStartLine()));
            sb1.append(" | ");
            sb1.append(node);
            sb1.append('\n');
            sb1.append(String.format("%" + padding + "s", whitespace));
            sb1.append(" | ");
            // <-- Render the message
            StringBuilder sb2 = new StringBuilder();
            ReductionNode reduce = (ReductionNode) node;
            int i = 0;
            int size = reduce.children();
            for (; i < size; ++i) {
                if (reduce.get(i).equals(ast.token)) {
                    break;
                }
            }
            int start = i;
            int end = 0;
            for (int k = 0; k < i; ++k) {
                start += reduce.get(k).getText().length();
            }
            for (int k = size; k >= i; --k) {
                ASTNode n = reduce.get(k);
                end += n.getText().length() + 1;
            }
            sb2.append(PJUtil.addChar(' ', start));
            sb2.append(PJUtil.addChar('^', ast.token.lexeme.length() + 1));
            if (message != null && message.length() != 0) {
                sb2.append(' ');
                sb2.append(message);
            }
            String text = sb2.toString();
            if (Settings.showColor) {
                text = ANSICode.setColor(text, severity);
            }
            sb1.append(text);
            // -->
            sb1.append('\n');
            sb1.append(String.format("%" + padding + "s", whitespace));
            sb1.append(" | \n");
            sb1.append(String.format("%" + padding + "s", whitespace));
            sb1.append(" = help: ");
            if (link == null) {
                link = "messsage not yet implemented";
            }
            sb1.append(link);
            sb1.append('\n');
            sb1.append("...");
        }
        return sb1.toString();
    }
    
    // TODO
    public void printSyntaxError() {
        ASTTree tree = compilation.get(hashValue);
        Integer[] lines = tree.getLines();
        int padding = tree.getLineNumberWidth();
        System.out.println();
        for (int i = 0; i < lines.length; ++i) {
            ASTNode node = tree.get(lines[i]);
            System.out.print(String.format("%" + padding + "s", node.getStartLine()));
            System.out.print(" : ");
            node.print();
            System.out.println();
        }
        System.out.println();
    }
    
    protected static class ExprStatVisitor extends Visitor<ASTNode> {
        
        public ASTNode visitBinaryExpr(BinaryExpr be) {
            return super.visitBinaryExpr(be);
        }
        
        public ASTNode visitExprStat(ExprStat es) {
            return super.visitExprStat(es);
        }
    }
}
