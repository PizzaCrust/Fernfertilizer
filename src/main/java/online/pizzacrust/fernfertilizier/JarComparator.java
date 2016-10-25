package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JarComparator
{

    private final ClassIndexer firstJarIndex;
    private final ClassIndexer secondJarIndex;

    public JarComparator(File firstJar, File secondJar) {
        firstJarIndex = new ClassIndexer(firstJar);
        secondJarIndex = new ClassIndexer(secondJar);
    }

    private CtClass[] asArray(List<CtClass> ctClassList) {
        return ctClassList.toArray(new CtClass[ctClassList.size()]);
    }

    public Map<CtClass, CtClass> generateMappings() {
        ClassGroupComparator comparator = new ClassGroupComparator(asArray(firstJarIndex.getCachedClasses
                ()), asArray(secondJarIndex.getCachedClasses()));
        return comparator.generateMappings();
    }

}
