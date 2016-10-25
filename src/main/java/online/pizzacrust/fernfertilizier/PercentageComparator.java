package online.pizzacrust.fernfertilizier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Compares two sets of objects and generates a percentage of equal objects.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class PercentageComparator<SET_OBJECT>
{

    /**
     * Original values.
     */
    private final List<SET_OBJECT> original = new ArrayList<SET_OBJECT>();

    /**
     * New values to be checked against.
     */
    private final List<SET_OBJECT> newer = new ArrayList<SET_OBJECT>();

    /**
     * The standard, or the original values.
     * @param objects
     */
    public void setStandard(SET_OBJECT... objects) {
        original.clear();
        original.addAll(Arrays.asList(objects));
    }

    /**
     * The new values to be compared against from the 'standard' or original.
     * @param objects
     */
    public void setNewer(SET_OBJECT... objects) {
        newer.clear();
        newer.addAll(Arrays.asList(objects));
    }

    /**
     * Checks whether the specified object matches any from the 'original' set.
     * @param object
     */
    private boolean doesEntryMatchOriginal(SET_OBJECT object) {
        for (SET_OBJECT originalObj : original) {
            if (object.equals(originalObj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Computes the percentage against
     * @return
     */
    public int compute() {
        int matchedWithOld = 0;
        for (SET_OBJECT newObject : newer) {
            if (doesEntryMatchOriginal(newObject)) {
                matchedWithOld++;
            }
        }
        double matchedAsDecimal = matchedWithOld;
        double totalPossible = newer.size();
        return new Double((matchedAsDecimal/totalPossible)*100.0).intValue();
    }

    public static void main(String... args) {

        /**
         * 0% similar.
         */
        PercentageComparator<String> test1 = new PercentageComparator<String>();
        test1.setStandard();
        test1.setNewer("Cat");
        System.out.println(test1 + "% similar.");

        PercentageComparator<String> test2 = new PercentageComparator<String>();
        test2.setStandard("Cat");
        test2.setNewer("Test");
        System.out.println(test2 + "% similar.");

        /**
         * 50% similar.
         */
        PercentageComparator<Integer> test3 = new PercentageComparator<Integer>();
        test3.setStandard(50);
        test3.setNewer(50, 100);
        System.out.println(test3 + "% similar.");

        /**
         * 100% similar.
         */
        PercentageComparator<Double> test4 = new PercentageComparator<Double>();
        test4.setStandard(50.0);
        test4.setNewer(50.0);
        System.out.println(test4 + "% similar.");
    }

    /**
     * Combines a computation between both given comparators and generates one percentage.
     * @param comparator
     * @return
     */
    public int combineComputation(PercentageComparator comparator) {
        int thisPercentage = this.compute();
        int otherPercentage = comparator.compute();
        double total = 200.0;
        double combinedNumerator = thisPercentage + otherPercentage;
        return new Double((combinedNumerator / total) * 100).intValue();
    }

    @Override
    public String toString() {
        return "" + compute();
    }

}
