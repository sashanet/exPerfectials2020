import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Oleksandr Buryk
 */
public class SaveFile {

    public static void save() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            writer.append("line");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
