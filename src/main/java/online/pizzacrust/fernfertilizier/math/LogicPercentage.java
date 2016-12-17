package online.pizzacrust.fernfertilizier.math;

public interface LogicPercentage {

    boolean isHigherOrEqual(int number);

    int getPercentage();

    abstract class BaseLogicPercentage implements LogicPercentage {

        public boolean isHigherOrEqual(int number) {
            return getPercentage() <= number;
        }

    }

}
