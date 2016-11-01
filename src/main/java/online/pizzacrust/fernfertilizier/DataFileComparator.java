package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.util.List;
import java.util.Map;

import online.pizzacrust.fernfertilizier.io.JarData;

public class DataFileComparator {

    private final File firstIndex;
    private final File secondIndex;

    public DataFileComparator(File firstIndex, File secondIndex) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    private JarData.ClassConstantPool[] toArray(List<JarData.ClassConstantPool> list) {
        return list.toArray(new JarData.ClassConstantPool[list.size()]);
    }

    public Map<JarData.ClassConstantPool, JarData.ClassConstantPool> generateMappings() {
        JarData first = new JarData(firstIndex);
        JarData second = new JarData(secondIndex);
        DataGroupComparator dataGroupComparator = new DataGroupComparator(toArray(first
                .constants), toArray(second
                .constants));
        return dataGroupComparator.generateMappings();
    }


}
