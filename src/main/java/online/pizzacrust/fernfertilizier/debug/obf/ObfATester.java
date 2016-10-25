package online.pizzacrust.fernfertilizier.debug.obf;

import javassist.ClassPool;
import javassist.CtClass;

import java.util.Map;

import online.pizzacrust.fernfertilizier.ClassGroupComparator;

public class ObfATester {

    public static void main(String... args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass deobfA = classPool.get(DeobfA.class.getName());
        CtClass obfA = classPool.get(ObfA.class.getName());
        CtClass distraction = classPool.get(ExtraA.class.getName());
        ClassGroupComparator classGroupComparator = new ClassGroupComparator(new CtClass[] {
                deobfA, distraction}, new CtClass[] { obfA });
        System.out.println(classGroupComparator.generateMappings().size());
        for (Map.Entry<CtClass, CtClass> mapping : classGroupComparator.generateMappings().entrySet()) {
            System.out.println("NEW: ".concat(mapping.getKey().getName()) + " to OLD: ".concat
                    (mapping.getValue().getName()));
        }
    }

}
