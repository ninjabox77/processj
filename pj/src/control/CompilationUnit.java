package control;

import ast.CompileUnit;
import ast.Sequence;
import ast.ClassNode;
import org.antlr.v4.runtime.Token;
import visitor.DefaultVisitor;
import visitor.GenericVisitor;
import visitor.VoidVisitor;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * The compilation unit collects all compilation data as it is generated
 * by the compiler. We use an instance of this class to add additional
 * resources to the compile unit, and force the compilation to be run
 * again if needed.
 *
 * @author Ben
 */
public class CompilationUnit extends ProcessingUnit {

  private CompileUnit ast_;
  private Map<String, CompileUnit> otherSources_;
  private Queue<CompileUnit> queueSources_;
  private Sequence<ClassNode> generatedClasses_;

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
    otherSources_ = new HashMap<>();
    queueSources_ = new LinkedList<>();
    generatedClasses_ = Sequence.sequenceList();
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

  public CompilationUnit addSources(final String[] paths) {
    for (String p : paths) {
      addSource(new File(p));
    }
    return this;
  }

  public CompilationUnit addSources(final File[] files) {
    for (File f : files) {
      addSource(f);
    }
    return this;
  }

  public CompilationUnit addSource(final File file) {
    final String path = file.getAbsolutePath();
    // TODO:
    return this;
  }

  @Override
  public <T, A> T accept(GenericVisitor<T, A> v, A arg) {
    return null;
  }

  @Override
  public <A> void accept(VoidVisitor<A> v, A arg) {

  }

  @Override
  public <T> T accept(DefaultVisitor<T> v) {
    return null;
  }
}
