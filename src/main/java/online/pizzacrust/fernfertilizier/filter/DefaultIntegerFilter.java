package online.pizzacrust.fernfertilizier.filter;

import javassist.bytecode.ConstPool;

public class DefaultIntegerFilter extends DefaultFilterStrategy {
    @Override
    public int getStrategyTestTag() {
        return ConstPool.CONST_Integer;
    }
}
