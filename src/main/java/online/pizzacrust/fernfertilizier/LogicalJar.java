package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import online.pizzacrust.fernfertilizier.filter.DefaultDoubleFilter;
import online.pizzacrust.fernfertilizier.filter.DefaultFloatFilter;
import online.pizzacrust.fernfertilizier.filter.DefaultIntegerFilter;
import online.pizzacrust.fernfertilizier.filter.DefaultStringFilter;

public class LogicalJar {

    private final ClassFile[] classes;

    public ClassFile[] getClasses() {
        return classes;
    }

    public LogicalJar(ClassFile[] classes) {
        this.classes = classes;
    }

    public static LogicalJar process(File file) throws IOException {
        JarFile jarFile = new JarFile(file);
        ClassFile[] classes = ClassFile.processClasses(jarFile);
        return new LogicalJar(classes);
    }

    public ClassFile[] filter(ClassFilter filter) {
        List<ClassFile> classFileList = new ArrayList<>();
        for (ClassFile classFile : this.classes) {
            if (filter.onRequestedCheck(classFile)) {
                classFileList.add(classFile);
            }
        }
        return classFileList.toArray(new ClassFile[classFileList.size()]);
    }

    public enum StandardFilter {
        INTEGER(new DefaultIntegerFilter(), Integer.class),
        FLOAT(new DefaultFloatFilter(), Float.class),
        DOUBLE(new DefaultDoubleFilter(), Double.class),
        STRING(new DefaultStringFilter(), String.class);

        private final ClassFilter theClass;
        private final Class<?> typeClass;

        StandardFilter(ClassFilter theClass, Class<?> typeClass) {
            this.typeClass = typeClass; this.theClass =
                    theClass;
        }


        public Class<?> getTypeClass() { return typeClass; }

        public ClassFilter getFilterClass() {
            return theClass;
        }

        public TypeFilter<?> getTypeFilter() {
            return (TypeFilter<?>) this.getFilterClass();
        }

    }

    public ClassFile[] filter(StandardFilter filter) {
        return this.filter(filter.getFilterClass());
    }

}
