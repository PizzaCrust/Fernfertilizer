package online.pizzacrust.fernfertilizier.filter;

import javassist.bytecode.ConstPool;

public class DefaultDoubleFilter extends DefaultFilterStrategy {
    @Override
    public ConstantTest.PrimitiveType getStrategyTestTag() {
        return ConstantTest.PrimitiveType.DOUBLE;
    }
}
