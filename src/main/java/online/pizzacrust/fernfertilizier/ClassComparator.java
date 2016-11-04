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
    private final ConstantProvider newer;

    /**
     * The original ctclass.
     */
    private final ConstantProvider original;

    public ClassComparator(ConstantProvider newer, ConstantProvider original) {
        this.newer = newer;
        this.original = original;
    }

    /**
     * Compares with the classes.
     * @return
     */
    public boolean compare(int passingPercentage) {
        String[] stringsNewer   = newer.stringConstants();
        Integer[] integersNewer = newer.integerConstants();
        String[] stringsOlder   = original.stringConstants();
        Integer[] integersOlder = original.integerConstants();
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
