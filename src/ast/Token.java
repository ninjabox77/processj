package ast;

import syntaxchecker.ASTNode;
import syntaxchecker.ReductionNode;
import syntaxchecker.Types;

/**
 * @author ben
 */
public class Token extends ASTNode {
    
    /** Gets its value from sym.java */
    public int kind = Types.INSTANCE.UNKNOWN;
    /** Some interpretation applied to the token */
    public int meaning = Types.INSTANCE.UNKNOWN;
    /** The actual text scanned for this token */
    public String lexeme = "";
    /** The line number on which this token appears */
    public int line = -1;
    /** The column number in which the token begins */
    public int charBegin = -1;
    /** The column number in which the token ends */
    public int charEnd = -1;

    public Token(int kind, String text, int line, int charBegin, int charEnd) {
        this.kind = kind;
        this.lexeme = text;
        this.line = line;
        this.charBegin = charBegin;
        this.charEnd = charEnd;
    }
    
    public Token(String text) {
    	this.lexeme = text;
    }
    
    public Token() {
    }
    
    @Override
    public String getText() {
        return lexeme;
    }

    @Override
    public int getStartLine() {
        return line;
    }

    @Override
    public int getStartColumn() {
        return charBegin;
    }
    
    public int getEndColumn() {
        return charEnd;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public ASTNode get(int index) {
        if (index > 0)
            throw new AssertionError("Can only access the root node");
        return this;
    }

    @Override
    public Token getRoot() {
        return this;
    }

    @Override
    public String toString() {
        return "Token '" + lexeme + "' @ line: " + line
            + "[" + charBegin + ".." + charEnd + "] (kind: " + kind + ")";
    }

    @Override
    public ReductionNode asReduction() {
        return new ReductionNode(this);
    }
}