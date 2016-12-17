package online.pizzacrust.fernfertilizier.filter;

public class DefaultIntegerFilter extends DefaultFilterStrategy {
    @Override
    public ConstantTest.PrimitiveType getStrategyTestTag() {
        return ConstantTest.PrimitiveType.INTEGER;
    }
}
