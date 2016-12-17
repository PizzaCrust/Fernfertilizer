package online.pizzacrust.fernfertilizier.filter;

import javassist.CtClass;
import javassist.bytecode.ConstPool;

import online.pizzacrust.fernfertilizier.ClassFile;
import online.pizzacrust.fernfertilizier.ClassFilter;

public class DefaultStringFilter extends DefaultFilterStrategy {
    @Override
    public int getStrategyTestTag() {
        return ConstPool.CONST_String;
    }
}
