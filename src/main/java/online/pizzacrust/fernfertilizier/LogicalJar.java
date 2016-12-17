package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class LogicalJar {

    private final ClassFile[] classes;

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

    public enum StandardFilter { //TODO after filters are completed, put them here
        INTEGER(null),
        FLOAT(null),
        DOUBLE(null),
        STRING(null);

        private final ClassFilter theClass;

        StandardFilter(ClassFilter theClass) {
            this.theClass = theClass;
        }

        public ClassFilter getFilterClass() {
            return theClass;
        }

    }

    public ClassFile[] filter(StandardFilter filter) {
        return this.filter(filter.getFilterClass());
    }

}
