package utilities;

import org.stringtemplate.v4.ST;

/**
 * This class is used to create generic messages for the ProcessJ compiler.
 * 
 * @author ben
 * @since 1.2
 */
public class PJMessage extends PJBugMessage {
    
    private boolean doTrace = false;
    
    public PJMessage(Builder builder) {
        super(builder);
        doTrace = builder.doTrace;
    }
    
    @Override
    public ST getST() {
        ST stFile = stGroup.getInstanceOf("File");
        ST stTag = stGroup.getInstanceOf("Tag");
        ST stStackInfo = stGroup.getInstanceOf("StackInfo");
        ST stMessage = stGroup.getInstanceOf("Message");
        
        if (ast != null) {
            stFile.add("fileName", fileName);
            stFile.add("lineNumber", ast.line);
        }
        
        if (errorNumber != null) {
            stTag.add("tag", errorNumber.getErrorSeverity());
            stTag.add("number", errorNumber.getNumber());
        }
        
        if (throwable != null) {
            stStackInfo.add("reason", throwable);
            stStackInfo.add("stack", throwable.getStackTrace());
        }
        
        // Apply color code if allowed on terminal
        String tag = stTag.render();
        if (Settings.showColor)
            tag = ANSICode.setColor(stTag.render(), errorNumber.getErrorSeverity());
        
        stMessage.add("tag", tag);
        stMessage.add("message", super.getST().render());
        stMessage.add("location", stFile.render());
        stMessage.add("stack", stStackInfo.render());
        
        return stMessage;
    }
    
    public String getRenderMessage() {
        ST stResult = getST();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stResult.render());
        if (doTrace && throwable != null)
            stringBuilder.append(throwable.toString());
        return stringBuilder.toString();
    }
    
    public boolean hasStackTrace() {
        return doTrace;
    }
    
    // *************************************************************************
    // ** BUILDER
    
    /**
     * Builder for this basic error message type.
     * 
     * @author ben
     * @version 10/20/2018
     * @since 1.2
     */
    public static final class Builder extends PJBugMessage.Builder<Builder> {
        
        protected boolean doTrace;
        
        public Builder() {
            doTrace = false;
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public <E extends PJBugMessage> E build() {
            @SuppressWarnings("unchecked")
            E error = (E) new PJMessage(this);
            return error;
        }
        
        public Builder addStackTrace(boolean doTrace) {
            this.doTrace = doTrace;
            return builder();
        }
    }
}
