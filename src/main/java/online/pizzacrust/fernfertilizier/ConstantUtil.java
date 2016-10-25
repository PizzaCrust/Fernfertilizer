package online.pizzacrust.fernfertilizier;

import javassist.CtClass;
import javassist.bytecode.ConstPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Constant pool utilities.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ConstantUtil
{

    /**
     * Retrieves constant pool strings. (LDC nodes)
     * @param ctClass
     * @return
     */
    public static String[] getStrings(CtClass ctClass) {
        int current = 1;
        List<String> stringList = new ArrayList<String>();
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        while (current < constPool.getSize()) {
            if (constPool.getTag(current) == ConstPool.CONST_String) {
                stringList.add(constPool.getStringInfo(current));
            }
            current++;
        }
        return stringList.toArray(new String[stringList.size()]);
    }

    /**
     * Retrieves constant pool integers. (BIPUSH, ETC)
     * @param ctClass
     * @return
     */
    public static Integer[] getIntegers(CtClass ctClass) {
        int current = 1;
        List<Integer> integers = new ArrayList<Integer>();
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        while (current < constPool.getSize()) {
            if (constPool.getTag(current) == ConstPool.CONST_Integer) {
                integers.add(constPool.getIntegerInfo(current));
            }
            current++;
        }
        return integers.toArray(new Integer[integers.size()]);
    }

    public static void main(String... args) throws Exception {
        if (ClassIndexer.getDebugClasses()[ClassIndexer.INT_TEST] == null) { System.out.println("Null!");
            return; }
        if (ClassIndexer.getDebugClasses()[ClassIndexer.STRING_TEST] == null) { System.out.println("Null!");
            return; }
        String[] strings = getStrings(ClassIndexer.getDebugClasses()[ClassIndexer.STRING_TEST]);
        for (String string : strings) {
            System.out.println(string);
        }
        Integer[] integers = getIntegers(ClassIndexer.getDebugClasses()[ClassIndexer.INT_TEST]);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

}
