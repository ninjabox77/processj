package control;

import ast.Compilation;
import ast.BytecodeAST;
import ast.java.ClassDeclaration;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.io.File;
import java.util.*;

/**
 * The compilation unit collects all compilation data as it is generated
 * by the compiler. We use an instance of this class to add additional
 * resources to the compile unit and force the compilation to be run
 * again if needed.
 *
 * @author Ben
 */
public class CompilationUnit extends ProcessingUnit {

  private Compilation ast_;
  private Map<String, Compilation> compilations_;
  private Queue<Compilation> queueSources_;
  private List<String> sources_;
  private List<ClassDeclaration> classes_;
  private List<BytecodeAST> bytecodeASTS_;

  public CompilationUnit() {
    this(null);
  }

  public CompilationUnit(Compilation ast) {
    this(null, ast);
  }

  public CompilationUnit(Token token, Compilation ast) {
    super(token);
    setCompileUnit(ast);
    customInitialization();
  }

  @Override
  public void customInitialization() {
    compilations_ = new HashMap<>();
    queueSources_ = new LinkedList<>();
    sources_ = new LinkedList<>();
    classes_ = new LinkedList<>();
    bytecodeASTS_ = new LinkedList<>();
  }

  public CompilationUnit setCompileUnit(Compilation ast) {
    if (ast == ast_) {
      return this;
    }
    if (ast_ != null) {
      ast_.setParentNode(null);
    }
    ast_ = ast;
    setAsParentNodeOf(ast);
    return this;
  }

  public Compilation getCompileUnit() {
    return ast_;
  }

  public void addSources(final String[] files) {
    for (String f : files) {
      addSource(f);
    }
  }

  public Compilation addSource(final String file) {
    return addSource(new Compilation(file));
  }

  public void addSources(final File[] files) {
    for (File f : files) {
      addSource(f);
    }
  }

  public Compilation addSource(final File file) {
    return addSource(new Compilation(file.getName()));
  }

  public Compilation addSource(Compilation source) {
    final String file = source.getFile();
    for (Compilation cu : queueSources_) {
      if (cu.getFile().equals(file)) {
        return cu;
      }
    }
    queueSources_.add(source);
    return source;
  }

  public Collection<String> getSources() {
    return Collections.unmodifiableList(sources_);
  }

  public Compilation getCompilation() {
    return ast_;
  }

  public void compile() {
    compile(Phases.ALL);
  }

  public void compile(int currentPhase) {
    nextPhase(Phases.INITIALIZATION);
    while (currentPhase >= phase_ && currentPhase <= Phases.ALL) {
      // process phase operations
      // dequeue to bring new sources into phase
      // completePhase
      // mark
      // nextPhase + 1
    }
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return v.visit(this, arg);
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {
    v.visit(this, arg);
  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return v.visit(this);
  }
}
