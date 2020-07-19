package utilities;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import ast.AST;

/**
 * This class is used to create messages during a tree-traversal node
 * when processing the contents of a ProcessJ file, when processing
 * the syntax and/or semantics errors when compiling or generating
 * Java source code from a ProcessJ file, or when processing command
 * line options and/or arguments.
 * 
 * @author ben
 * @version 10/07/2018
 * @since 1.2
 */
public abstract class PJBugMessage {
    
    private static final Object[] EMPTY_ARGUMENTS = new Object[0];
    private static final String EMPTY_STRING = "";

    /* String template file locator */
    protected static final String ERROR_FILE = "resources/stringtemplates/messages/errorTemplate.stg";
    
    /* Template for error messages */
    protected static final STGroup stGroup = new STGroupFile(ERROR_FILE);
    
    /* Current running AST */
    protected final AST ast;
    
    /* Type of error message */
    protected final MessageNumber errorNumber;
    
    /* Attributes used in templates */
    protected final Object[] arguments;
    
    /* Reason for the error message */
    protected final Throwable throwable;
    
    /* Source of the message */
    protected final String fileName;
    
    /* Location of the input file */
    protected final String packageName;
    
    /* Line in file */
    protected int rowNum;
    
    /* Character that generated the error/warning */
    protected int colNum;
    
    public PJBugMessage(Builder<?> builder) {
        ast = builder.ast;
        arguments = builder.arguments;
        // This is not ideal!!
        errorNumber = builder.error == null? new MessageNumber() {
            @Override
            public String getMessage() {
                StringBuilder sb = new StringBuilder();
                Object[] args = builder.arguments;
                if (args != null) {
                    for (int i = 0; i < args.length; ++i) {
                        sb.append(args[i]);
                        sb.append("\n");
                    }
                    return sb.toString().trim();
                }
                return "EMPTY MESSAGE!"; }
            } : builder.error;
        throwable = builder.throwable;
        fileName = builder.fileName == null? new File(PJBugManager.INSTANCE.fileName).getAbsolutePath() :
                                             new File(builder.fileName).getAbsolutePath();
        packageName = builder.packageName == null? PJBugManager.INSTANCE.fileName : builder.packageName;
        rowNum = builder.rowNum;
        colNum = builder.colNum;
    }
    
    // *************************************************************************
    // ** GETTERS
    
    public AST ast() {
        return ast;
    }
    
    public MessageNumber getMessageNumber() {
        return errorNumber;
    }
    
    public Object[] getArguments() {
        return arguments;
    }
    
    public Throwable getThrowable() {
        return throwable;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public int getRowNumber() {
        return rowNum;
    }
    
    public int getColNumber() {
        return colNum;
    }
    
    public ST getST() {
        int argCount = 0;
        ST message = null;
        if (errorNumber != null)
            message = new ST(errorNumber.getMessage());
        else
            message = new ST(EMPTY_STRING);
        if (arguments != null && arguments.length > 0)
            argCount = arguments.length;
        for (int i = 0; i < argCount; ++i)
            message.add("arg" + i, arguments[i]);
        return message;
    }
    
    public abstract String getRenderMessage();
    
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "(filename="        + (fileName.isEmpty() ? "none" : fileName) +
                ", package="        + (packageName.isEmpty() ? "none" : packageName) +
                ", errorNumber="    + errorNumber.getNumber() +
                ", errorMessage="   + errorNumber.getMessage() +
                ", arguments="      + (arguments != null? "{" +
                                      Arrays.stream(arguments)
                                            .map(arg -> arg + "")
                                            .collect(Collectors.joining(",")) + "}"
                                            : "none") +
                ", reason="         + (throwable != null?
                                            throwable.getMessage()
                                            : "none") +
                ", row="            + rowNum +
                ", column="         + colNum +
                ")";
    }
    
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result + ast.hashCode();
        result = prime + result + errorNumber.hashCode();
        result = prime + result + Arrays.hashCode(arguments);
        result = prime + result + throwable.hashCode();
        result = prime + result + fileName.hashCode();
        result = prime + result + packageName.hashCode();
        result = prime + result + rowNum;
        result = prime + result + colNum;
        return result;
    }
    
    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        PJBugMessage other = (PJBugMessage) obj;
        if (this.rowNum != other.rowNum || this.colNum != other.colNum)
            return false;
        if (!this.fileName.equals(other.fileName) || !this.packageName.equals(other.packageName))
            return false;
        if (this.ast != other.ast) // This should be OK
            return false;
        if (!this.errorNumber.equals(other.errorNumber))
            return false;
        if (!Arrays.equals(this.arguments, other.arguments))
            return false;
        if (!this.throwable.equals(other.throwable))
            return false;
        
        return true;
    }
    
    // *************************************************************************
    // ** BUILDER
    
    /**
     * 
     * The class Builder uses descriptive methods to create error
     * messages with default or initial values.
     * 
     * @author ben
     * @version 10/20/2018
     * @since 1.2
     *
     * @param <B>
     *            The builder type.
     */
    public static abstract class Builder<B> {
        
        protected AST ast;
        protected MessageNumber error;
        protected Object[] arguments;
        protected Throwable throwable;
        protected String fileName;
        protected String packageName;
        protected int rowNum;
        protected int colNum;
        
        public Builder() {
            ast = null;
            error = null;
            arguments = EMPTY_ARGUMENTS;
            throwable = null;
            fileName = null;
            packageName = null;
        }
        
        protected abstract B builder();
        
        public abstract <E extends PJBugMessage> E build();
        
        public B addAST(AST ast) {
            this.ast = ast;
            return builder();
        }
        
        public B addError(MessageNumber error) {
            this.error = error;
            return builder();
        }
        
        public B addArguments(Object... arguments) {
            this.arguments = arguments;
            return builder();
        }
        
        public B addThrowable(Throwable throwable) {
            this.throwable = throwable;
            return builder();
        }
        
        public B addFileName(String fileName) {
            this.fileName = fileName;
            return builder();
        }
        
        public B addPackageName(String packageName) {
            this.packageName = packageName;
            return builder();
        }
        
        public B addRowNumber(int rowNum) {
            this.rowNum = rowNum;
            return builder();
        }
        
        public B addColNumber(int colNum) {
            this.colNum = colNum;
            return builder();
        }
    }
}
