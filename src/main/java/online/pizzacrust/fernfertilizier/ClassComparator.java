package online.pizzacrust.fernfertilizier;

import java.util.ArrayList;

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
        Integer[] newInts = newClass.filter(LogicalJar.StandardFilter.INTEGER);
        Integer[] oldInts = oldClass.filter(LogicalJar.StandardFilter.INTEGER);
        if (newInts.length == 0) {
            diffMap.setNotApplicable(LogicalJar.StandardFilter.INTEGER);
        } else {
            ArrayPercentageCalculator<Integer> intCalculator = new ArrayPercentageCalculator<>
                    (toArray(newInts), toArray(oldInts));
            diffMap.set(LogicalJar.StandardFilter.INTEGER, intCalculator.calculate());
        }
    }

}
