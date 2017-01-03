package online.pizzacrust.fernfertilizier.filter;

import java.util.List;

import online.pizzacrust.fernfertilizier.ClassFile;
import online.pizzacrust.fernfertilizier.ClassFilter;
import online.pizzacrust.fernfertilizier.TypeFilter;

public abstract class DefaultFilterStrategy implements ClassFilter, TypeFilter {
    @Override
    public boolean onRequestedCheck(ClassFile classFile) {
        return ClassFilter.ConstantTest.contains(classFile.toCtClass(), getStrategyTestTag());
    }

    public abstract ConstantTest.PrimitiveType getStrategyTestTag();

    @Override
    public Object[] searchFiltered(ClassFile classFile) {
        List primitiveList = ClassFilter.ConstantTest.list(classFile.toCtClass(),
                getStrategyTestTag());
        return primitiveList.toArray(new Object[primitiveList.size()]);
    }


}
