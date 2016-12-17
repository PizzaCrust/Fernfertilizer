package online.pizzacrust.fernfertilizier.filter;

import javassist.bytecode.ConstPool;

public class DefaultDoubleFilter extends DefaultFilterStrategy {
    @Override
    public int getStrategyTestTag() {
        return ConstPool.CONST_Double;
    }
}
