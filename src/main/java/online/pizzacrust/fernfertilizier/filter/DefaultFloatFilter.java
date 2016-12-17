package online.pizzacrust.fernfertilizier.filter;

import javassist.bytecode.ConstPool;

public class DefaultFloatFilter extends DefaultFilterStrategy {
    @Override
    public ConstantTest.PrimitiveType getStrategyTestTag() {
        return ConstantTest.PrimitiveType.FLOAT;
    }
}
