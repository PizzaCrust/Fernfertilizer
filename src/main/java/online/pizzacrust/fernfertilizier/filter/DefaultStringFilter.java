package online.pizzacrust.fernfertilizier.filter;

public class DefaultStringFilter extends DefaultFilterStrategy {
    @Override
    public ConstantTest.PrimitiveType getStrategyTestTag() {
        return ConstantTest.PrimitiveType.STRING;
    }
}
