package online.pizzacrust.fernfertilizier.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class SRGWriter implements MappingsWriter {

    public static final String DEBUG_PACKAGE = "online.pizzacrust.fernfertilizier.debug.";

    private StringBuilder cachedText = new StringBuilder();
    private final File file;

    public SRGWriter(File file) {
        this.file = file;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void clearBuffer() {
        cachedText = new StringBuilder();
    }

    public void write(String newName, String oldName) {
        if (!newName.startsWith(DEBUG_PACKAGE)) {
            if (!oldName.startsWith(DEBUG_PACKAGE)) {
                cachedText.append("CL: ".concat(newName.replace('.', '/')).concat(" ").concat
                        (oldName.replace('.', '/') + "\n"));
            }
        }
    }

    public void flush() {
        try {
            Files.write(this.file.toPath(), cachedText.toString().getBytes(Charset.defaultCharset()), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clearBuffer();
    }

}
