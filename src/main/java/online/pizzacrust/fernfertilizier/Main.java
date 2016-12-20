package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import online.pizzacrust.fernfertilizier.field.FieldMatcher;
import online.pizzacrust.fernfertilizier.field.RemappedClass;
import online.pizzacrust.fernfertilizier.field.SRGFDWriter;

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
                " passing grade.").withRequiredArg().ofType(Integer.class).forHelp();
        parser.acceptsAll(toList("samemodifiers", "sm", "m"), "Sets whether same modifiers rule" +
                " apply to field comparison.").withRequiredArg().ofType(Boolean.class).forHelp();
        parser.acceptsAll(toList("field", "f"), "If field is added, field mapping will be " +
                "enabled.");
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
        if (arguments.has("samemodifiers")) {
            FieldMatcher.SAME_MODIFIERS = (boolean) arguments.valueOf("samemodifiers");
            System.out.println("Same modifiers rule has been set to: " + FieldMatcher.SAME_MODIFIERS);
        }
        boolean canField = false;
        if (arguments.has("field")) {
            canField = true;
            System.out.println("Field mapping is enabled!");
        }
        LogicalJar logicOriginal = LogicalJar.process(originalJar);
        LogicalJar logicalNew      = LogicalJar.process(newJar);
        LogicJarComparator comparator = new LogicJarComparator(logicOriginal, logicalNew);
        File srgFile = new File(System.getProperty("user.dir"), "target.srg");
        System.out.println("NOTICE: Writing output SRG to: " + srgFile.getAbsolutePath());
        comparator.writeTo(new LogicJarComparator.MappingWriter.SRGWriter(srgFile));
        System.out.println("SRG has been written with mappings!");
        if (canField) {
            System.out.println("Generating fields...");
            RemappedClass[] classes = RemappedClass.from(comparator.getMappings(), logicalNew);
            FieldMatcher matcher = new FieldMatcher(logicOriginal.getClasses(), classes);
            matcher.appendTo(new SRGFDWriter(srgFile));
            System.out.println("Finished!");
        }
    }

}
