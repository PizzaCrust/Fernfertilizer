package online.pizzacrust.fernfertilizier.debug;

/**
 * Debug class for debug testing during runtime.
 * This test identifies the integer in the class and should identify to the same class.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class IntIdentifier
{

    public static final int MAGIC_NUMBER = 100;

    /**
     * Make sure this class is identified by number.
     * This string is not in other classes, thus 0% similarities via string.
     * @return
     */
    public String getMagicMethod() {
        return "Hello, world.";
    }

}
