package online.pizzacrust.fernfertilizier;

import java.util.List;

import online.pizzacrust.fernfertilizier.io.JarData.ClassConstantPool;

public class DataComparator {

    private final ClassConstantPool newer;

    private final ClassConstantPool original;

    public DataComparator(ClassConstantPool newer, ClassConstantPool original) {
        this.newer = newer;
        this.original = original;
    }

    private String[] toStringArray(List<String> stringList) {
        return stringList.toArray(new String[stringList.size()]);
    }

    private Integer[] toIntArray(List<Integer> integers) {
        return integers.toArray(new Integer[integers.size()]);
    }

    public boolean compare(int passingPercentage) {
        String[] stringsNewer   = toStringArray(newer.stringConstants);
        Integer[] integersNewer = toIntArray(newer.integerConstants);
        String[] stringsOlder   = toStringArray(original.stringConstants);
        Integer[] integersOlder = toIntArray(original.integerConstants);
        PercentageComparator<String> stringComparator = new PercentageComparator<String>();
        stringComparator.setStandard(stringsOlder);
        stringComparator.setNewer(stringsNewer);
        PercentageComparator<Integer> integerComparator = new PercentageComparator<Integer>();
        integerComparator.setStandard(integersOlder);
        integerComparator.setNewer(integersNewer);
        int percentage = stringComparator.combineComputation(integerComparator);
        if (percentage >= passingPercentage) {
            return true;
        }
        return false;
    }


}
