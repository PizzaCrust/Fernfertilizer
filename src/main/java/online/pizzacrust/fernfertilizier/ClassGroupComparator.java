package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Compares class files in a more massive scale.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ClassGroupComparator
{

    /**
     * Minimum percentage to pass mapping grade.
     */
    public static int MINIMUM_PERCENTAGE = 60;

    public CtClass[] originalClasses;
    public CtClass[] newerClasses;

    public ClassGroupComparator(CtClass[] originalClasses, CtClass[] newerClasses) {
        this.originalClasses = originalClasses;
        this.newerClasses = newerClasses;
    }

    public Map<CtClass, CtClass> generateMappings() {
        HashMap<CtClass, CtClass> map = new HashMap<CtClass, CtClass>();
        ArrayList<CtClass> mappedAlready = new ArrayList<>();
        for (CtClass newClass : newerClasses) {
            for (CtClass oldClass : originalClasses) {
                ClassComparator classComparator = new ClassComparator(newClass, oldClass);
                if (!mappedAlready.contains(oldClass)) {
                    if (classComparator.compare(MINIMUM_PERCENTAGE)) {
                        System.out.println("MAPPING DETECTED: " + newClass.getName() + " to " +
                                oldClass.getName());
                        map.put(newClass, oldClass);
                        mappedAlready.add(oldClass);
                        break;
                    }
                } else if (mappedAlready.contains(oldClass)) {
                    System.out.println("CONFLICT DETECTED: " + newClass.getName() + " to " +
                            oldClass.getName());
                    break;
                }
            }
        }
        return map;
    }

}
