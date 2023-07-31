package control;

import ast.CompileUnit;
import ast.BytecodeAST;
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

  private CompileUnit ast_;
  private Map<String, CompileUnit> sources_;
  private Queue<CompileUnit> queueSources_;
  private List<BytecodeAST> generatedClasses_;

  public CompilationUnit() {
    this(null);
  }

  public CompilationUnit(CompileUnit ast) {
    this(null, ast);
  }

  public CompilationUnit(Token token, CompileUnit ast) {
    super(token);
    setCompileUnit(ast);
    customInitialization();
  }

  @Override
  public void customInitialization() {
    sources_ = new HashMap<>();
    queueSources_ = new LinkedList<>();
    generatedClasses_ = new LinkedList<>();
  }

  public CompilationUnit setCompileUnit(CompileUnit ast) {
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

  public CompileUnit getCompileUnit() {
    return ast_;
  }

  public void addSources(final String[] files) {
    for (String f : files) {
      addSource(new File(f));
    }
  }

  public void addSources(final File[] files) {
    for (File f : files) {
      addSource(f);
    }
  }

  public CompileUnit addSource(final File file) {
    final String f = file.getAbsolutePath();
    // TODO: ??
    return null;
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
