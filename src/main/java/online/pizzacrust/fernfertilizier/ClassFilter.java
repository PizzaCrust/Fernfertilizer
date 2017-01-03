package online.pizzacrust.fernfertilizier;

import javassist.CtClass;
import javassist.bytecode.ConstPool;

import java.util.ArrayList;
import java.util.List;

public interface ClassFilter {

    boolean onRequestedCheck(ClassFile classFile);

    class ConstantTest {
        public static boolean contains(CtClass ctClass, PrimitiveType tag) {
            ConstPool constantPool = ctClass.getClassFile().getConstPool();
            int index = 1;
            while (index < constantPool.getSize()) {
                if (constantPool.getTag(index) == tag.getTag()) {
                    return true;
                }
                index++;
            }
            return false;
        }
        public static List list(CtClass ctClass, PrimitiveType tag) {
            ArrayList types = new ArrayList<>();
            ConstPool constantPool = ctClass.getClassFile().getConstPool();
            int index = 1;
            while (index < constantPool.getSize()) {
                if (constantPool.getTag(index) == tag.getTag()) {
                    switch (tag) {
                        case INTEGER:
                            types.add(constantPool.getIntegerInfo(index));
                            break;
                        case DOUBLE:
                            types.add(constantPool.getDoubleInfo(index));
                            break;
                        case FLOAT:
                            types.add(constantPool.getFloatInfo(index));
                            break;
                        case STRING:
                            types.add(constantPool.getStringInfo(index));
                            break;
                    }
                }
                index++;
            }
            return types;
        }
        public enum PrimitiveType {
            STRING(ConstPool.CONST_String),
            FLOAT(ConstPool.CONST_Float),
            DOUBLE(ConstPool.CONST_Double),
            INTEGER(ConstPool.CONST_Integer);

            private final int tag;

            PrimitiveType(int tag) {
                this.tag = tag;
            }

            public int getTag() {
                return tag;
            }
        }
    }


}
