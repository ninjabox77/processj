package syntaxchecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ast.Token;

/**
 * @author ben
 */
public class ReductionNode extends ASTNode {
    
    private List<ASTNode> nodes = null;
    
    public ReductionNode(Token root) {
        nodes = new ArrayList<>();
        set(0, root);
    }
    
    public ReductionNode() {
        nodes = Collections.emptyList();
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public ASTNode get(int index) {
        if (index < size())
            return (ASTNode) nodes.get(index);
        return (ASTNode) null;
    }

    @Override
    public Token getRoot() {
        if (size() > 0)
            return (Token) nodes.get(0);
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public ASTNode add(ASTNode n) {
        return set(size(), n);
    }
    
    @Override
    public ASTNode remove(int index) {
        if (index < 1)
            throw new AssertionError("Can't remove root from the list");
        return (ASTNode) nodes.remove(index);
    }
    
    @Override
    public ASTNode set(int index, ASTNode n) {
        if (nodes == null)
            throw new AssertionError("Can't insert a node into an EMPTY list");
        final int count = size();
        if (index >= count)
            for (int i = count; i <= index; ++i)
                nodes.add(null);
        nodes.set(index, n);
        return n;
    }

    @Override
    public ReductionNode asReduction() {
        return this;
    }
}
