package online.pizzacrust.fernfertilizier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogicJarComparator {

    private final LogicalJar originalJar;
    private final LogicalJar newJar;

    private final Map<String, String> mappings = new HashMap<>();

    public LogicJarComparator(LogicalJar originalJar, LogicalJar newJar) {
        this.originalJar = originalJar;
        this.newJar = newJar;
    }

    public void generateMappings() {
        mappings.clear();
        for (ClassFile newClass : newJar.getClasses()) {
            for (ClassFile oldClass : originalJar.getClasses()) {
                ClassComparator classComparator = new ClassComparator(newClass, oldClass);
                if (classComparator.compare()) {
                    mappings.put(newClass.getJvmName(), oldClass.getJvmName());
                    break;
                }
            }
        }
    }

    public interface MappingWriter {

        void onRequestedWrite(String newJvmName, String oldJvmName);

        void onRequestedClose() throws IOException;

    }

    public void writeTo(MappingWriter mappingWriter) throws IOException {
        generateMappings();
        for (Map.Entry<String, String> mappingEntry : this.mappings.entrySet()) {
            mappingWriter.onRequestedWrite(mappingEntry.getKey(), mappingEntry.getValue());
        }
        mappingWriter.onRequestedClose();
    }


}
