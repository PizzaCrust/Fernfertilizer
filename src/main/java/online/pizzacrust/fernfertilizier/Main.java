package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Main {

    private static List<String> toList(String... strings) {
        return Arrays.asList(strings);
    }

    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();
        parser.acceptsAll(toList("original", "1", "o", "f"), "Sets the ORIGINAL (deobf) JAR.")
                .withRequiredArg().ofType(File.class).required();
        parser.acceptsAll(toList("new", "2", "n", "s"), "Sets the SECOND (obf) or updated JAR" +
                ".").withRequiredArg().ofType(File.class).required();
        parser.acceptsAll(toList("help", "?", "h"), "Launches the HELP menu.").forHelp();
        parser.acceptsAll(toList("passgrade", "percentage", "p", "pg", "g", "grade"), "Sets the" +
                " passing grade.").withRequiredArg().ofType(Integer.class).required();
        OptionSet arguments = parser.parse(args);
        if (arguments.has("help")) {
            parser.printHelpOn(System.out);
            return;
        }
        File originalJar = (File) arguments.valueOf("original");
        File newJar = (File) arguments.valueOf("new");
        if (arguments.has("passgrade")) {
            ClassComparator.PASSING_PERCENTAGE = (int) arguments.valueOf("passgrade");
            System.out.println("Passing percentage grade is set to: " + ClassComparator.PASSING_PERCENTAGE);
        }
        LogicalJar logicOriginal = LogicalJar.process(originalJar);
        LogicalJar logicalNew      = LogicalJar.process(newJar);
        LogicJarComparator comparator = new LogicJarComparator(logicOriginal, logicalNew);
        File srgFile = new File(System.getProperty("user.dir"), "target.srg");
        System.out.println("NOTICE: Writing output SRG to: " + srgFile.getAbsolutePath());
        comparator.writeTo(new LogicJarComparator.MappingWriter.SRGWriter(srgFile));
        System.out.println("SRG has been written with mappings!");
    }

}
