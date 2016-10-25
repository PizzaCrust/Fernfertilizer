package online.pizzacrust.fernfertilizier;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import online.pizzacrust.fernfertilizier.debug.IntIdentifier;
import online.pizzacrust.fernfertilizier.debug.StringIdentifier;

/**
 * Indexes classes.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ClassIndexer
{

    /**
     * Converts a reflect class to a JavaAssist class.
     * @param reflectObj
     * @return
     */
    private static CtClass fromClass(Class<?> reflectObj) {
        try {
            return ClassPool.getDefault().get(reflectObj.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Test oridinals.
     */
    public static final int INT_TEST = 0;
    public static final int STRING_TEST = 1;

    /**
     * Retrieves the debug classes used for debugging test during runtime.
     * @return
     */
    public static CtClass[] getDebugClasses() {
        return new CtClass[] { fromClass(IntIdentifier.class), fromClass(StringIdentifier.class) };
    }

    /**
     * The cached class internal list. Should use {@link #getCachedClasses()} instead.
     */
    private final List<CtClass> cachedClasses = new ArrayList<CtClass>();

    public ClassIndexer(File file) {
        this.cache(file);
    }

    /**
     * Polishes the specified name to remove file extension.
     * @return
     */
    public static String polishName(String str) {
        return str.substring(0, str.lastIndexOf('.'));
    }

    /**
     * Caches the elements in the specified JAR file.
     * @param file
     */
    public void cache(File file) {
        cachedClasses.clear();
        try {
            ClassPool.getDefault().appendClassPath(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JarFile jarFile = new JarFile(file);
            Enumeration<JarEntry> entryEnumeration = jarFile.entries();
            while (entryEnumeration.hasMoreElements()) {
                String elementName = entryEnumeration.nextElement().getName();
                if (elementName.endsWith(".class")) {
                    String className = polishName(elementName).replace
                            ('/', '.');
                    cachedClasses.add(ClassPool.getDefault().get(className));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the cached classes.
     * @return
     */
    public List<CtClass> getCachedClasses() {
        return cachedClasses;
    }

}
