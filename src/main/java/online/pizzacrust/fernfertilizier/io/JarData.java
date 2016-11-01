package online.pizzacrust.fernfertilizier.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class JarData
{

    public static class ClassConstantPool implements Serializable {
        public final String className;
        public final List<String> stringConstants;
        public final List<Integer> integerConstants;

        public ClassConstantPool(String className, List<String> strings, List<Integer> integers) {
            this.className = className;
            this.stringConstants = strings;
            this.integerConstants = integers;
        }

    }

    /**
     * Makes a new jar data formatted file
     * @param target
     * @param constants
     */
    public static void newJarData(File target, List<ClassConstantPool> constants) {
        if (!target.exists()) {
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(target);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(constants);
            objectOutputStream.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final File target;
    public List<ClassConstantPool> constants;

    public JarData(File target) {
        this.target = target;
        update();
    }

    /**
     * Updates the class.
     */
    public void update() {
        try {
            FileInputStream fis = new FileInputStream(target);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<ClassConstantPool> classConstants = (List<ClassConstantPool>) ois.readObject();
            ois.close();
            fis.close();
            constants = classConstants;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.constants);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
