/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxchecker;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ben
 */
public enum ASTStringCompiler {
    INSTANCE;
    
    private String curPJFile = null;
    private boolean alreadyParsed = false;
    private HashMap<String, ASTTree> hashMap = new HashMap<>();
    
    public void addPJFile(String file) {
        curPJFile = file;
        if (!hashMap.containsKey(file)) {
            hashMap.put(file, new ASTTree());
            alreadyParsed = false;
        } else
            alreadyParsed = true;
    }
    
    public void add(ASTNode n) {
        if (!alreadyParsed)
            hashMap.get(curPJFile).add(n);
    }
    
    public void print() {
        Iterator<String> it = hashMap.keySet().iterator();
        while (it.hasNext())
            hashMap.get(it.next()).print();
    }
    
    // TODO: Build error message
    public void printSyntaxError() {
        ASTTree tree = hashMap.get(curPJFile);
        Integer[] lines = tree.getLines();
        final int maxPadding = tree.getMaxLineNumber();
        System.out.println();
        for (int i = 0; i < lines.length; ++i) {
            ASTNode n = tree.get(lines[i]);
            System.out.print(String.format("%" + maxPadding + "s", n.getStartLine()));
            System.out.print(" : ");
            n.print();
            System.out.println();
        }
        System.out.println();
    }
}
