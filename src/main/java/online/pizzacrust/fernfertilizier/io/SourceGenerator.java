package online.pizzacrust.fernfertilizier.io;

import javassist.CtClass;

import java.util.Map;

import online.pizzacrust.fernfertilizier.ClassGroupComparator;
import online.pizzacrust.fernfertilizier.DataFileComparator;
import online.pizzacrust.fernfertilizier.JarComparator;

/**
 * Represents source mapping generator.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class SourceGenerator
{

    private final Map<CtClass, CtClass> mappings;

    public SourceGenerator(Map<CtClass, CtClass> mappings) {
        this.mappings = mappings;
    }

    /**
     * Retrieves the mappings field.
     * @return
     */
    public Map<CtClass, CtClass> getMappings() {
        return mappings;
    }

    /**
     * Constructs a source generator from a comparator.
     * @param classGroupComparator
     * @return
     */
    public static SourceGenerator fromComparator(ClassGroupComparator classGroupComparator) {
        return new SourceGenerator(classGroupComparator.generateMappings());
    }

    /**
     * Generates a source generator from a jar comparator.
     * @param jarComparator
     * @return
     */
    public static SourceGenerator fromJarComparator(JarComparator jarComparator) {
        return new SourceGenerator(jarComparator.generateMappings());
    }

    /**
     * Writes the source to the specified writer.
     * @param writer
     */
    public void writeTo(MappingsWriter writer) {
        for (Map.Entry<CtClass, CtClass> entry : mappings.entrySet()) {
            writer.write(entry.getKey().getName(), entry.getValue().getName());
        }
        writer.flush();
    }

    public static class DataGenerator {

        private Map<JarData.ClassConstantPool, JarData.ClassConstantPool> mappings;

        public DataGenerator(Map<JarData.ClassConstantPool, JarData.ClassConstantPool> mappings) {
            this.mappings = mappings;
        }

        public static DataGenerator fromDataComparator(DataFileComparator dfc) {
            return new DataGenerator(dfc.generateMappings());
        }

        public void writeTo(MappingsWriter writer) {
            for (Map.Entry<JarData.ClassConstantPool, JarData.ClassConstantPool> entry : mappings.entrySet()) {
                writer.write(entry.getKey().className, entry.getValue().className);
            }
            writer.flush();
        }

    }

}
