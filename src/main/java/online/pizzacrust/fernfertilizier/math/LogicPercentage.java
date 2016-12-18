package online.pizzacrust.fernfertilizier.math;

public interface LogicPercentage {

    boolean isHigherOrEqual(int number);

    int getPercentage();

    abstract class BaseLogicPercentage implements LogicPercentage {

        public boolean isHigherOrEqual(int number) {
            return getPercentage() <= number;
        }

    }

    class BasicLogicPercentage extends BaseLogicPercentage {

        private final int percentage;

        public BasicLogicPercentage(Double primitive) {
            this.percentage = primitive.intValue();
        }

        @Override
        public int getPercentage() {
            return percentage;
        }

    }

}
