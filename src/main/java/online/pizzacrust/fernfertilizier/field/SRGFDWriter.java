package online.pizzacrust.fernfertilizier.field;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import online.pizzacrust.fernfertilizier.LogicJarComparator;

public class SRGFDWriter implements LogicJarComparator.MappingWriter {

    private final StringBuilder builder = new StringBuilder();
    private final File srg;

    public SRGFDWriter(File srg) {
        this.srg = srg;
    }

    @Override
    public void onRequestedWrite(String newJvmName, String oldJvmName) {
        builder.append("FD: " + newJvmName + " " + oldJvmName + "\n");
    }

    @Override
    public void onRequestedClose() throws IOException {
        Files.write(srg.toPath(), builder.toString().getBytes(), StandardOpenOption.APPEND);
    }

}
