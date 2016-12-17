package online.pizzacrust.fernfertilizier;

import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.ArrayList;

import online.pizzacrust.fernfertilizier.LogicalJar.StandardFilter;
import online.pizzacrust.fernfertilizier.math.ArrayPercentageCalculator;
import online.pizzacrust.fernfertilizier.math.LogicPercentage;
import online.pizzacrust.fernfertilizier.math.LogicalMap;

public class ClassComparator {

    public static int PASSING_PERCENTAGE = 60;

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
        if (newInts.length > 0) {
            diffMap.setNotApplicable(StandardFilter.INTEGER);
        } else if (oldInts.length > 0) {
            ArrayPercentageCalculator<Integer> intCalculator = new ArrayPercentageCalculator<>
                    (toArray(newInts), toArray(oldInts));
            diffMap.set(StandardFilter.INTEGER, intCalculator.calculate());
        } else {
            diffMap.setNotApplicable(StandardFilter.INTEGER);
        }
        Double[] newDoubles = newClass.filter(StandardFilter.DOUBLE);
        Double[] oldDoubles = oldClass.filter(StandardFilter.DOUBLE);
        if (newDoubles.length > 0) {
            diffMap.setNotApplicable(StandardFilter.DOUBLE);
        } else if (oldDoubles.length > 0) {
            ArrayPercentageCalculator<Double> doubleCalculator = new ArrayPercentageCalculator<>
                    (toArray(newDoubles), toArray(oldDoubles));
            diffMap.set(StandardFilter.DOUBLE, doubleCalculator.calculate());
        } else {
            diffMap.setNotApplicable(StandardFilter.DOUBLE);
        }
        Float[] newFloats = newClass.filter(StandardFilter.FLOAT);
        Float[] oldFloats = oldClass.filter(StandardFilter.FLOAT);
        if (newFloats.length > 0) {
            diffMap.setNotApplicable(StandardFilter.FLOAT);
        } else if (oldFloats.length > 0) {
            ArrayPercentageCalculator<Float> floatCalculator = new ArrayPercentageCalculator<>
                    (toArray(newFloats), toArray(oldFloats));
            diffMap.set(StandardFilter.FLOAT, floatCalculator.calculate());
        } else {
            diffMap.setNotApplicable(StandardFilter.FLOAT);
        }
        String[] newStrings = newClass.filter(StandardFilter.STRING);
        String[] oldStrings = oldClass.filter(StandardFilter.STRING);
        if (newStrings.length > 0) {
            diffMap.setNotApplicable(StandardFilter.STRING);
        } else if (oldStrings.length > 0){
            ArrayPercentageCalculator<String> stringCalculator = new ArrayPercentageCalculator<>
                    (toArray(newStrings), toArray(oldStrings));
            diffMap.set(StandardFilter.STRING, stringCalculator.calculate());
        } else {
            diffMap.setNotApplicable(StandardFilter.STRING);
        }

        int passes = 0;
        if (diffMap.isApplicable(StandardFilter.INTEGER)) {
            if (diffMap.get(StandardFilter.INTEGER).isHigherOrEqual(PASSING_PERCENTAGE)) {
                passes++;
            }
        }
        if (diffMap.isApplicable(StandardFilter.STRING)) {
            if (diffMap.get(StandardFilter.STRING).isHigherOrEqual(PASSING_PERCENTAGE)) {
                passes++;
            }
        }
        if (diffMap.isApplicable(StandardFilter.DOUBLE)) {
            if (diffMap.get(StandardFilter.DOUBLE).isHigherOrEqual(PASSING_PERCENTAGE)) {
                passes++;
            }
        }
        if (diffMap.isApplicable(StandardFilter.FLOAT)) {
            if (diffMap.get(StandardFilter.FLOAT).isHigherOrEqual(PASSING_PERCENTAGE)) {
                passes++;
            }
        }
        Double percentage = ((double) passes / (double) 10) * (double) 100;
        if (percentage.intValue() >= PASSING_PERCENTAGE) {
            return true;
        }
        return false;
    }

}
