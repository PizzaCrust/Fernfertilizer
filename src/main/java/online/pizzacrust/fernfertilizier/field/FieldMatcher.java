package online.pizzacrust.fernfertilizier.field;

import javassist.CtField;

import java.util.HashMap;

import online.pizzacrust.fernfertilizier.ClassFile;

public class FieldMatcher {

    public static boolean SAME_MODIFIERS = false;

    private final HashMap<String, String> fieldMappings = new HashMap<>();

    private final ClassFile[] originalClasses;
    private final RemappedClass[] newClasses;

    public FieldMatcher(ClassFile[] original, RemappedClass[] classes) {
        this.originalClasses = original;
        this.newClasses = classes;
    }

    public void generateMappings() {
        fieldMappings.clear();
        for (ClassFile classFile : originalClasses) {
            for (RemappedClass remappedClass : newClasses) {
                if (classFile.getJvmName().equals(remappedClass.getRemappedName())) {
                    for (CtField field : classFile.toCtClass().getDeclaredFields()) {
                        for (RemappedField field1 : remappedClass.getRemappedFields()) {
                            if (field.getFieldInfo().getDescriptor().equals
                                    (field1.getRemappedDescriptor())) {
                                if (SAME_MODIFIERS) {
                                    if (field.getModifiers() != field1.getField().getModifiers()) {
                                        break;
                                    }
                                }
                                fieldMappings.put(field1.getField().getDeclaringClass().getName()
                                        .replace('.', '/') + "/" + field1.getField().getName(),
                                        field.getDeclaringClass().getName().replace('.', '/') +
                                                "/" + field.getName());
                            }
                        }
                    }
                }
            }
        }
    }

}
