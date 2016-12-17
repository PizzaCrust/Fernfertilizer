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

    public ClassFile(String jvmName, InputStream inputStream) {
        try {
            this.assistClass = ClassPool.getDefault().makeClass(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        this.jvmName = jvmName;
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
        return classFileList.toArray(new ClassFile[classFileList.size()]);
    }

    public CtClass toCtClass() {
        return this.assistClass;
    }


}
