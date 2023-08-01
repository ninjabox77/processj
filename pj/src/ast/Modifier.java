package ast;

import org.objectweb.asm.Opcodes;

public interface Modifier<T extends Modifier<?>> extends Opcodes {

  int getModifiers();

  T setModifiers(final int modifiers);

  default String modifierString() {
    if (getModifiers() != 0) {
      StringBuilder sb = new StringBuilder();
      if (isStatic()) {
        sb.append("static").append(" ");
      }
      if (isFinal()) {
        sb.append("final").append(" ");
      }
      if (isPublic()) {
        sb.append("public").append(" ");
      }
      if (isProtected()) {
        sb.append("protected").append(" ");
      }
      if (isPrivate()) {
        sb.append("private").append(" ");
      }
      if (isNative()) {
        sb.append("native").append(" ");
      }
      return sb.toString();
    }
    return "none";
  }

  default boolean isStatic() {
    return (getModifiers() & ACC_STATIC) != 0;
  }

  default boolean isFinal() {
    return (getModifiers() & ACC_FINAL) != 0;
  }

  default boolean isPublic() {
    return (getModifiers() & ACC_PUBLIC) != 0;
  }

  default boolean isProtected() {
    return (getModifiers() & ACC_PROTECTED) != 0;
  }

  default boolean isPrivate() {
    return (getModifiers() & ACC_PRIVATE) != 0;
  }

  default boolean isNative() {
    return (getModifiers() & ACC_NATIVE) != 0;
  }
}
