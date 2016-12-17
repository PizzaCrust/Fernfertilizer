package online.pizzacrust.fernfertilizier.filter;

import online.pizzacrust.fernfertilizier.ClassFile;
import online.pizzacrust.fernfertilizier.ClassFilter;

public abstract class DefaultFilterStrategy implements ClassFilter {
    @Override
    public boolean onRequestedCheck(ClassFile classFile) {
        return ClassFilter.ConstantTest.contains(classFile.toCtClass(), getStrategyTestTag());
    }

    public abstract int getStrategyTestTag();

}
