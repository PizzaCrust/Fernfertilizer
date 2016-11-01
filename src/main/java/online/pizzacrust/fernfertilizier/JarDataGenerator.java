package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import online.pizzacrust.fernfertilizier.io.JarData;

public class JarDataGenerator {

    private final File jar;

    public JarDataGenerator(File jarFile) {
        this.jar = jarFile;
    }

    public void write(File logFile) {
        ClassIndexer indexer = new ClassIndexer(jar);
        List<JarData.ClassConstantPool> constantPools = new ArrayList<>();
        for (CtClass ctClass : indexer.getCachedClasses()) {
            String name = ctClass.getName();
            List<String> strings = Arrays.asList(ConstantUtil.getStrings(ctClass));
            List<Integer> integers = Arrays.asList(ConstantUtil.getIntegers(ctClass));
            constantPools.add(new JarData.ClassConstantPool(name, strings, integers));
        }
        JarData.newJarData(logFile, constantPools);
    }
    
}
