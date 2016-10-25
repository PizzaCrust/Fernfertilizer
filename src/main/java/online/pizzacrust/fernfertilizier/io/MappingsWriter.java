package online.pizzacrust.fernfertilizier.io;

/**
 * Writes mappings to specified source.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public interface MappingsWriter
{

    /**
     * Write mappings with specified parameters given to the writer.
     * @param newName
     * @param oldName
     */
    void write(String newName, String oldName);

    /**
     * Flush the written mappings and flush the writer.
     */
    void flush();

}
