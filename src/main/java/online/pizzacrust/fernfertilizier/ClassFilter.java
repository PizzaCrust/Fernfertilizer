package online.pizzacrust.fernfertilizier;

import javassist.CtClass;
import javassist.bytecode.ConstPool;

public interface ClassFilter {

    boolean onRequestedCheck(ClassFile classFile);

    class ConstantTest {
        public static boolean contains(CtClass ctClass, int tag) {
            ConstPool constantPool = ctClass.getClassFile().getConstPool();
            int index = 1;
            while (index < constantPool.getSize()) {
                if (constantPool.getTag(index) == tag) {
                    return true;
                }
            }
            return false;
        }
    }


}
