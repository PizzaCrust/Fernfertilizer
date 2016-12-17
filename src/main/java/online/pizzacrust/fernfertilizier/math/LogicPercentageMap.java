package online.pizzacrust.fernfertilizier.math;

import java.util.HashMap;

public class LogicPercentageMap<TYPE> {

    private final HashMap<Enum, LegacyOptional<TYPE>> internalMap = new HashMap<>();

    public boolean isApplicable(Enum value) {
        if (!internalMap.containsKey(value)) {
            return false;
        }
        if (internalMap.get(value).isPresent()) {
            return true;
        }
        return false;
    }

    public void setNotApplicable(Enum value) {
        internalMap.put(value, LegacyOptional.<TYPE>empty());
    }

    public void set(Enum value, TYPE percentage) {
        internalMap.put(value, new LegacyOptional<>(percentage));
    }

    public TYPE get(Enum value) {
        if (!isApplicable(value))
            throw new RuntimeException();
        return internalMap.get(value).get();
    }

}
