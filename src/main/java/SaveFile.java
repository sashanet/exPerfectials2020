import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Buryk
 */
public class SaveFile {

    public static void save(Result result) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {

            writer.append(String.valueOf(result.getLibrariesToBooks().size())).append("\n");

            for(Map.Entry<Integer, List<Integer>>  entry: result.getLibrariesToBooks().entrySet()) {

                writer.append(String.valueOf(entry.getKey())).append(" ").append(String.valueOf())

                writer.append(String.valueOf(entry.getKey()) + " " + );

            }




        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
