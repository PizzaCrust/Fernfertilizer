package online.pizzacrust.fernfertilizier;

import java.util.Arrays;
import java.util.List;

public interface ConstantProvider {

    String[] stringConstants();

    List<String> stringConstantsAsList();

    Integer[] integerConstants();

    List<Integer> integerConstantsAsList();

    abstract class BaseConstantProvider implements ConstantProvider {

        @Override
        public List<String> stringConstantsAsList() {
            return Arrays.asList(stringConstants());
        }

        @Override
        public List<Integer> integerConstantsAsList() {
            return Arrays.asList(integerConstants());
        }

    }

}
