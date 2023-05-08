import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A simple front-end for getting standard compilations done. Note that
 * a compilation will generate a java file (or java files) in the working
 * directory, which should have been specified during installation.
 *
 * @author Ben
 */
public class Compiler {
  // Types of arguments available for the compiler.
  public enum OptionType {
    STRING,
    BOOLEAN;
  }

  public static class Option {
    protected String field_;
    protected String option_;
    protected OptionType type_;
    protected String info_;

    public Option(String field, String option, OptionType type, String info) {
      field_ = field;
      option_ = option;
      type_ = type;
      info_ = info;
    }

    public Option(String field, String option, String info) {
      this(field, option, OptionType.BOOLEAN, info);
    }
  }

  // List of available options for the ProcessJ compiler
  public static final Option[] OPTIONS = {
      new Option("help", "-help", "Show this help message and exit"),
      new Option("errors", "-errors", "Show all error and warning messages when available"),
      new Option("version", "-version", "Print version information and exit"),
      new Option("debug", "-debug", "Generate all parse tree visitors"),
  };

  // Field used by the compiler.
  public boolean help;
  public boolean version;
  public boolean errors;
  public boolean debug;

  private List<String> inputFiles_ = new ArrayList<>();
  private Properties config_;

  public Compiler(String[] args) {
    parseArguments(args);
  }

  public void parseArguments(String[] args) {
    for (int pos = 0; pos < args.length; ) {
      String arg = args[pos++];
      if (arg.charAt(0) != '-') {
        // Found a 'Xxx.pj' file.
        if (!inputFiles_.contains(arg))
          inputFiles_.add(arg);
      } else {
        boolean foundOption = false;
        for (Option o : OPTIONS) {
          if (arg.equals(o.option_)) {
            foundOption = true;
            String optionValue = null;
            if (o.type_ != OptionType.BOOLEAN)
              optionValue = args[pos++];
            // Same as before with Java reflection.
            Class<? extends Compiler> c = this.getClass();
            try {
              Field f = c.getField(o.field_);
              if (optionValue != null) {
                if (f.getType().isEnum()) {
                  setEnumField(f, optionValue, String.class);
                } else {
                  f.set(this, optionValue);
                }
              } else {
                f.set(this, true);
              }
            } catch (Exception e) {
              System.err.println("Failed to access field '" + o.field_ + "'");
              System.exit(1);
            }
          }
        }
        if (!foundOption) {
          System.err.println("Invalid option '" + arg + "' found!");
          System.exit(1);
        }
      }
    }
  }

  // This method must be used to get the value from enum types.
  private void setEnumField(Field f, Object arg, Class<?> type) throws Exception {
    Method valueOf = f.getType().getMethod("getValueOf", type);
    Object value = valueOf.invoke(f.get(this), arg);
    f.set(this, value);
  }

  public void printUsageAndExit() {
    for (Option o : OPTIONS) {
      System.out.printf("%-20s %s%n\n", o.option_, o.info_);
    }
    System.exit(0);
  }

  public static void main(String[] args) {
    Compiler c = new Compiler(args);
    // Do we have any arguments?
    if (args.length == 2) {
      System.out.println();
    }
  }
}