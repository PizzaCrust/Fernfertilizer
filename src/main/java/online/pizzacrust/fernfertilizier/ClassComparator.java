package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

/**
 * Compares classes.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ClassComparator
{

    /**
     * The newer ctclass.
     */
    private final CtClass newer;

    /**
     * The original ctclass.
     */
    private final CtClass original;

    public ClassComparator(CtClass newer, CtClass original) {
        this.newer = newer;
        this.original = original;
    }

    /**
     * Compares with the classes.
     * @return
     */
    public boolean compare(int passingPercentage) {
        String[] stringsNewer = ConstantUtil.getStrings(newer);
        Integer[] integersNewer = ConstantUtil.getIntegers(newer);
        String[] stringsOlder = ConstantUtil.getStrings(original);
        Integer[] integersOlder = ConstantUtil.getIntegers(original);
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
