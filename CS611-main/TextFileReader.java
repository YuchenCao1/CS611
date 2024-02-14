import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a high-level class to read lines from
 * any file and parse them into a readable format.
 */
public class TextFileReader {
    public static String[] readLines(final String filePath) {
        try (final BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().toArray(String[]::new);
        }
        catch (IOException exception){
            throw new IllegalArgumentException("File not found in system: "+exception);
        }
    }
}