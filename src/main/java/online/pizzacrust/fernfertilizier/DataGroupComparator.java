package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import online.pizzacrust.fernfertilizier.io.JarData;

import static online.pizzacrust.fernfertilizier.ClassGroupComparator.MINIMUM_PERCENTAGE;

public class DataGroupComparator {

    public JarData.ClassConstantPool[] originalClasses;
    public JarData.ClassConstantPool[] newerClasses;

    public DataGroupComparator(JarData.ClassConstantPool[] originalClasses, JarData
            .ClassConstantPool[] newerClasses) {
        this.originalClasses = originalClasses;
        this.newerClasses = newerClasses;
    }

    public Map<JarData.ClassConstantPool, JarData.ClassConstantPool> generateMappings() {
        HashMap<JarData.ClassConstantPool, JarData.ClassConstantPool> map = new
                HashMap<JarData.ClassConstantPool, JarData.ClassConstantPool>();
        ArrayList<JarData.ClassConstantPool> mappedAlready = new ArrayList<>();
        for (JarData.ClassConstantPool newClass : newerClasses) {
            for (JarData.ClassConstantPool oldClass : originalClasses) {
                ClassComparator classComparator = new ClassComparator(newClass, oldClass);
                    if (classComparator.compare(MINIMUM_PERCENTAGE)) {
                        if (!mappedAlready.contains(oldClass)) {
                            System.out.println("MAPPING DETECTED: " + newClass.className + " to " +
                                    oldClass.className);
                            map.put(newClass, oldClass);
                            mappedAlready.add(oldClass);
                        } else if (mappedAlready.contains(oldClass)) {
                            System.out.println("CONFLICT DETECTED: " + newClass.className + " to " +
                                    oldClass.className);
                            break;
                        }
                        break;
                    }
                }
            }
        return map;
    }

}
