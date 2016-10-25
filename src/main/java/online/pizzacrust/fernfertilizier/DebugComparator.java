package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.util.Map;

public class DebugComparator {

    public static Map<CtClass, CtClass> generateMappings() {
        ClassGroupComparator classGroupComparator = new ClassGroupComparator(ClassIndexer
                .getDebugClasses(), ClassIndexer.getDebugClasses());
        return classGroupComparator.generateMappings();
    }

    public static void main(String... args) {
        for (Map.Entry<CtClass, CtClass> mapping : generateMappings().entrySet()) {
            System.out.println("NEW: ".concat(mapping.getKey().getName()).concat(" to OLD: ")
                    .concat(mapping.getValue().getName()));
        }
    }

}
