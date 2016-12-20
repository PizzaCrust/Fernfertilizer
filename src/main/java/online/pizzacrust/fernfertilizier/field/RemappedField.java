package online.pizzacrust.fernfertilizier.field;

import javassist.CtClass;
import javassist.CtField;

import java.util.Map;

public class RemappedField {

    private final CtField field;
    private final String remappedDescriptor;

    public RemappedField(CtField field, Map<String, String> mappings) {
        String descriptor = field.getFieldInfo().getDescriptor();
        for (Map.Entry<String, String> mapping : mappings.entrySet()) {
            descriptor.replace(mapping.getKey(), mapping.getValue());
        }
        this.remappedDescriptor = descriptor;
        this.field = field;
    }

    public CtField getField() {
        return field;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CtField)) {
            return false;
        }
        CtField ctField = (CtField) object;
        if (ctField.getFieldInfo().getDescriptor().equals(this.remappedDescriptor)) {
            return true;
        }
        return false;
    }

    public String getRemappedDescriptor() {
        return remappedDescriptor;
    }
}
