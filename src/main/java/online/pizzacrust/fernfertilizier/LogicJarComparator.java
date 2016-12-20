package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicJarComparator {

    private final LogicalJar originalJar;
    private final LogicalJar newJar;

    private final Map<String, String> mappings = new HashMap<>();

    public LogicJarComparator(LogicalJar originalJar, LogicalJar newJar) {
        this.originalJar = originalJar;
        this.newJar = newJar;
    }

    public Map<String, String> getMappings() {
        return mappings;
    }

    public static boolean keyContains(Map<String, String> map, String name) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void generateMappings() {
        mappings.clear();
        for (ClassFile newClass : newJar.getClasses()) {
            for (ClassFile oldClass : originalJar.getClasses()) {
                ClassComparator classComparator = new ClassComparator(newClass, oldClass);
                if (classComparator.compare()) {
                    if (!keyContains(mappings, oldClass.getJvmName())) {
                        mappings.put(newClass.getJvmName(), oldClass.getJvmName());
                    } else {
                        System.out.println("Conflict detected: " + newClass.getJvmName() + " is " +
                                "attempting to be a " + oldClass.getJvmName());
                    }
                    break;
                }
            }
        }
    }

    public interface MappingWriter {

        void onRequestedWrite(String newJvmName, String oldJvmName);

        void onRequestedClose() throws IOException;

        class SRGWriter implements MappingWriter {

            private final List<String> strings = new ArrayList<String>();
            private final File targetFile;

            public SRGWriter(File targetFile) {
                this.targetFile = targetFile;
            }

            @Override
            public void onRequestedWrite(String newJvmName, String oldJvmName) {
                strings.add("CL: ".concat(newJvmName).concat(" ").concat(oldJvmName) + "\n");
            }

            @Override
            public void onRequestedClose() throws IOException {
                Files.write(targetFile.toPath(), strings, Charset.defaultCharset(),
                        StandardOpenOption.TRUNCATE_EXISTING);
            }

        }

    }

    public void writeTo(MappingWriter mappingWriter) throws IOException {
        generateMappings();
        for (Map.Entry<String, String> mappingEntry : this.mappings.entrySet()) {
            mappingWriter.onRequestedWrite(mappingEntry.getKey(), mappingEntry.getValue());
        }
        mappingWriter.onRequestedClose();
    }


}
