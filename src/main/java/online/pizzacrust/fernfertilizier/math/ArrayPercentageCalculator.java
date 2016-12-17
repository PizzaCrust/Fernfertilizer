package online.pizzacrust.fernfertilizier.math;

import java.util.ArrayList;
import java.util.List;

public class ArrayPercentageCalculator<ARRAY_TYPE> {

    private final List<ARRAY_TYPE> newList;
    private final List<ARRAY_TYPE> oldList;

    public ArrayPercentageCalculator(List<ARRAY_TYPE> newList, List<ARRAY_TYPE> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    public LogicPercentage calculate() {
        int matches = 0;
        for (ARRAY_TYPE type : newList) {
            for (ARRAY_TYPE original : oldList) {
                if (type.equals(original)) {
                    matches++;
                    break;
                }
            }
        }
        int total = oldList.size();
        double percentage = (((double) matches) / ((double) total)) * 100;
        return new LogicPercentage.BasicLogicPercentage(percentage);
    }


}
