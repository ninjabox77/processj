package analysischecker;

import ast.Token;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author ben
 */
public class ASTTree {
    
    private static final int MAX_WIDTH = 4;
    
    /** The line number on which the tree is created */
    private int curLine = -1;
    /** The root node */
    private ASTNode curNode = null;
    /** The number of trees in a source file */
    private HashMap<Integer, ASTNode> elements = new HashMap<>();
    
    public void add(ASTNode n) {
        if (curLine != n.getStartLine()) {
            curLine = n.getStartLine();
            curNode = n.asReduction();
            elements.put(curLine, curNode);
        } else
            curNode.add(n);
    }
    
    public int size() {
        return elements.size();
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public ASTNode get(int index) {
        return (ASTNode) elements.get(index);
    }
    
    public ASTNode get(Token t) {
        Iterator<ASTNode> it = elements.values().iterator();
        while (it.hasNext()) {
            ASTNode n = (ASTNode) it.next();
            if (n.equals(t))
                return n;
        }
        return null;
    }
    
    public List<ASTNode> getNodes() {
        return (List<ASTNode>) elements.values();
    }
    
    public Integer[] getLines() {
        Set<Integer> lines = elements.keySet();
        return (Integer[]) lines.toArray(new Integer[0]);
    }
    
    public int getLineNumberWidth() {
        int max = String.valueOf(Collections.max(elements.keySet())).length();
        return max > MAX_WIDTH ? max : MAX_WIDTH;
    }
    
    public void print() {
        Iterator<Integer> it = elements.keySet().iterator();
        while (it.hasNext()) {
            elements.get(it.next()).print();
            System.out.println();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = elements.keySet().iterator();
        while (it.hasNext()) {
            sb.append(elements.get(it.next()));
            sb.append(' ');
        }
        return sb.toString();
    }
}
