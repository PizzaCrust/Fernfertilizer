package online.pizzacrust.fernfertilizier.debug;

/**
 * Debug class for debug testing during runtime.
 * This test identifies the string in the class and should identify to the same class.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class StringIdentifier
{

    /**
     * To put off the constant searcher so it identifies via string.
     */
    public static final int MAGIC_NUMBER = 10;

    /**
     * Field test for searching constants.
     */
    public static final String CONSTANT_FIELD = "Constant test";

    /**
     * See {@link IntIdentifier#getMagicMethod()}
     * @return
     */
    public String getMagicMethod() {
        return "Test";
    }

}
