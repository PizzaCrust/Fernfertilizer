package online.pizzacrust.fernfertilizier;

import java.io.File;
import java.io.IOException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import online.pizzacrust.fernfertilizier.io.SRGWriter;
import online.pizzacrust.fernfertilizier.io.SourceGenerator;

import static java.util.Arrays.asList;
import static online.pizzacrust.fernfertilizier.ClassIndexer.polishName;

public class Main {

    public static void main(String... args) {
        OptionParser parser = new OptionParser();
        parser.acceptsAll(asList("log", "l"), "Logs statistics of a JAR.").withRequiredArg()
                .ofType(File.class).forHelp();
        parser.acceptsAll(asList("debug", "d"), "Launches the DebugComparator.").forHelp();
        parser.acceptsAll(asList("help", "?", "h"), "Launches the help menu.").forHelp();
        parser.acceptsAll(asList("passgrade", "pg"), "Set the passing grade for mappings.")
                .withRequiredArg().ofType(Integer.class);
        parser.acceptsAll(asList("original", "o", "first", "f", "1"), "Sets the ORIGINAL (usually" +
                " deobf) JAR.").withRequiredArg().ofType(File.class).required();
        parser.acceptsAll(asList("new", "n", "second", "s", "2"), "Sets the SECONDARY (usually " +
                "obf updated) JAR.").withRequiredArg().ofType(File.class).required();
        OptionSet arguments = parser.parse(args);
        if (arguments.has("log")) {
            File jarFile = (File) arguments.valueOf("log");
            File logFile = new File(System.getProperty("user.dir"), jarFile.getName().concat("" +
                    ".binarylog"));
            System.out.println("Logging data...");
            JarDataGenerator dataGen = new JarDataGenerator(jarFile);
            dataGen.write(logFile);
            System.out.println("Data logged to " + logFile.getName() + "!");
            System.out.println("Translating data to UTF log...");
            File utfLog = new File(System.getProperty("user.dir"), jarFile.getName().concat("" +
                    ".utf8log"));
            JarDataGenerator.translateLog(logFile, utfLog);
            System.out.println("Translated data to " + utfLog.getName() + "!");
            return;
        }
        if (arguments.has("debug")) {
            System.out.println("Debug mode enabled.");
            DebugComparator.main();
            return;
        }
        if (arguments.has("help")) {
            try {
                parser.printHelpOn(System.out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (arguments.has("passgrade")) {
            System.out.println("Passing grade has been set to: " + arguments.valueOf("passgrade"));
            ClassGroupComparator.MINIMUM_PERCENTAGE = (int) arguments.valueOf("passgrade");
        }
        File original = (File) arguments.valueOf("original");
        if (!original.exists()) {
            System.out.println("Original file set doesn't exist.");
            return;
        }
        boolean isLog1 = false;
        if (!original.getName().endsWith(".jar")) {
            if (!original.getName().endsWith(".binarylog")) {
                System.out.println("Original file doesn't have supported file type.");
                return;
            } else if (original.getName().endsWith(".binarylog")) {
                isLog1 = true;
            }
        }
        File secondary = (File) arguments.valueOf("new");
        if (!secondary.exists()) {
            System.out.println("Secondary file set doesn't exist.");
            return;
        }
        boolean isLog2 = false;
        if (!original.getName().endsWith(".jar")) {
            if (!original.getName().endsWith(".binarylog")) {
                System.out.println("Secondary file doesn't have supported file type.");
                return;
            } else if (original.getName().endsWith(".binarylog")) {
                isLog2 = true;
            }
        }
        System.out.println("WARN: This version of Fernfertilizer only supports SRG mappings.");
        File srgFile = new File(polishName(secondary.getName()) + ".csrg");
        System.out.println("NOTE: SRG mappings will be written to " + srgFile.getName() +
                "!");
        long startingMappingTime = System.currentTimeMillis();
        if (!isLog1 && !isLog2) {
            JarComparator comparator = new JarComparator(original, secondary);
            SourceGenerator generator = SourceGenerator.fromJarComparator(comparator);
            generator.writeTo(new SRGWriter(srgFile));
            System.out.println("Mappings have been written to: " + srgFile.getName());
            long finishedTime = System.currentTimeMillis() - startingMappingTime;
            long seconds = finishedTime / 1000;
            System.out.println("Finished in " + finishedTime + " milliseconds. (" + seconds + " " +
                    "seconds)");
            return;
        }
        if (isLog1 && isLog2) {
            DataFileComparator fileComparator = new DataFileComparator(original, secondary);
            SourceGenerator.DataGenerator sourceGenerator = SourceGenerator.DataGenerator.fromDataComparator
                    (fileComparator);
            sourceGenerator.writeTo(new SRGWriter(srgFile));
            System.out.println("Mappings have been written to: " + srgFile.getName());
            long finishedTime = System.currentTimeMillis() - startingMappingTime;
            long seconds = finishedTime / 1000;
            System.out.println("Finished in " + finishedTime + " milliseconds. (" + seconds + " " +
                    "seconds)");
            return;
        }
        System.out.println("Specified file types doesn't have a handler.");
    }

}
