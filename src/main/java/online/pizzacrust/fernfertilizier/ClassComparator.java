package online.pizzacrust.fernfertilizier;

import java.util.ArrayList;

import online.pizzacrust.fernfertilizier.LogicalJar.StandardFilter;
import online.pizzacrust.fernfertilizier.math.ArrayPercentageCalculator;
import online.pizzacrust.fernfertilizier.math.LogicPercentage;
import online.pizzacrust.fernfertilizier.math.LogicalMap;

public class ClassComparator {

    private final ClassFile newClass;
    private final ClassFile oldClass;

    private final LogicalMap<LogicPercentage> diffMap = new LogicalMap<>();

    public ClassComparator(ClassFile newClass, ClassFile oldClass) {
        this.newClass = newClass;
        this.oldClass = oldClass;
    }

    private <TYPE> ArrayList<TYPE> toArray(TYPE[] array) {
        ArrayList<TYPE> typeList = new ArrayList<>();
        for (TYPE type : array) {
            typeList.add(type);
        }
        return typeList;
    }

    public boolean compare() {
        Integer[] newInts = newClass.filter(StandardFilter.INTEGER);
        Integer[] oldInts = oldClass.filter(StandardFilter.INTEGER);
        if (newInts.length == 0) {
            diffMap.setNotApplicable(StandardFilter.INTEGER);
        } else {
            ArrayPercentageCalculator<Integer> intCalculator = new ArrayPercentageCalculator<>
                    (toArray(newInts), toArray(oldInts));
            diffMap.set(StandardFilter.INTEGER, intCalculator.calculate());
        }
        Double[] newDoubles = newClass.filter(StandardFilter.DOUBLE);
        Double[] oldDoubles = oldClass.filter(StandardFilter.DOUBLE);
        if (newDoubles.length == 0) {
            diffMap.setNotApplicable(StandardFilter.DOUBLE);
        } else {
            ArrayPercentageCalculator<Double> doubleCalculator = new ArrayPercentageCalculator<>
                    (toArray(newDoubles), toArray(oldDoubles));
            diffMap.set(StandardFilter.DOUBLE, doubleCalculator.calculate());
        }

    }

}
