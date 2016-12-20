package online.pizzacrust.fernfertilizier.field;

import javassist.CtField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import online.pizzacrust.fernfertilizier.ClassFile;

//TODO
public class RemappedClass {

    private final String remappedName;
    private final ClassFile classFile;
    private final Map<String, String> mappings;

    public RemappedClass(ClassFile classFile, Map<String, String> mappings) {
        this.remappedName = mappings.get(classFile.getJvmName());
        this.classFile = classFile;
        this.mappings = mappings;
    }

    public String getRemappedName() {
        return this.remappedName;
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public List<RemappedField> getRemappedFields() {
        ArrayList<RemappedField> arrayList = new ArrayList<>();
        for (CtField field : getClassFile().toCtClass().getDeclaredFields()) {
            arrayList.add(new RemappedField(field, this.mappings));
        }
        return arrayList;
    }

}
