package online.pizzacrust.fernfertilizier.math;

public class LegacyOptional<TYPE> {

    private TYPE object = null;

    public LegacyOptional(TYPE object) {
        this.object = object;
    }

    public boolean isPresent() {
        return object != null;
    }

    public static <TYPE> LegacyOptional<TYPE> empty() {
        return new LegacyOptional<>(null);
    }

    public TYPE get() {
        if (!isPresent()) {
            throw new RuntimeException();
        }
        return this.object;
    }

}
