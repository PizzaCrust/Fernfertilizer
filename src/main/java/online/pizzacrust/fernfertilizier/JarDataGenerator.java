package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

    public static void translateLog(File binaryLogFile, File output) {
        StringBuilder stringBuilder = new StringBuilder();
        JarData jarData = new JarData(binaryLogFile);
        for (JarData.ClassConstantPool constantPool : jarData.constants) {
            stringBuilder.append(constantPool.className + System.lineSeparator());
            for (String string : constantPool.stringConstants) {
                stringBuilder.append(" - " + string + System.lineSeparator());
            }
            for (Integer integer : constantPool.integerConstants) {
                stringBuilder.append(" - " + integer + System.lineSeparator());
            }
        }
        try {
            Files.write(output.toPath(), stringBuilder.toString().getBytes(Charset.defaultCharset()),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
