package online.pizzacrust.fernfertilizier;

import javassist.ClassPool;
import javassist.CtClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassFile {

    private final CtClass assistClass;
    private final String jvmName;

    public String getJvmName() {
        return jvmName;
    }

    @Override
    public String toString() {
        return getJvmName();
    }

    public ClassFile(String jvmName, InputStream inputStream) {
        try {
            this.assistClass = ClassPool.getDefault().makeClass(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        this.jvmName = jvmName;
    }

    public Object[] filter(TypeFilter filter) {
        return filter.searchFiltered(this);
    }

     public Object[] filter(LogicalJar.StandardFilter filter) {
        Object[] objects = filter(filter.getTypeFilter());
        if (objects.length == 0) {
            switch (filter) {
                case STRING:
                    objects = new String[0];
                    break;
                case FLOAT:
                    objects = new Float[0];
                    break;
                case DOUBLE:
                    objects = new Double[0];
                    break;
                case INTEGER:
                    objects = new Integer[0];
                    break;
            }
        }
        if (objects.getClass() == Object[].class) {
            switch (filter) {
                case STRING:
                    objects = new String[0];
                    break;
                case FLOAT:
                    objects = new Float[0];
                    break;
                case DOUBLE:
                    objects = new Double[0];
                    break;
                case INTEGER:
                    objects = new Integer[0];
                    break;
            }
        }
        return objects;
    }

    public static ClassFile[] processClasses(JarFile jarFile) {
        List<ClassFile> classFileList = new ArrayList<>();
        Enumeration<JarEntry> enumeration = jarFile.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry currentElement = enumeration.nextElement();
            if (currentElement.getName().endsWith(".class")) {
                try {
                    classFileList.add(new ClassFile(currentElement.getName().substring(0, currentElement
                            .getName()
                            .lastIndexOf('.')), jarFile.getInputStream(currentElement)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<ClassFile> toRemove = new ArrayList<>();
        for (ClassFile classFile : classFileList) {
            if (classFile.getJvmName().contains("$")) {
                System.out.println("Alert: " + classFile.getJvmName() + " is a subclass and " +
                        "subclasses cannot be processed due to similarity errors.");
                toRemove.add(classFile);
            }
        }
        classFileList.removeAll(toRemove);
        return classFileList.toArray(new ClassFile[classFileList.size()]);
    }

    public CtClass toCtClass() {
        return this.assistClass;
    }


}
