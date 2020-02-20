import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Buryk
 */
public class SaveFile {

    public static void save(String FILENAME, Result result) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("out_"+FILENAME))) {

            writer.append(String.valueOf(result.getLibrariesToBooks().size()));
            writer.newLine();

            for (Map.Entry<Integer, List<Integer>> entry : result.getLibrariesToBooks().entrySet()) {

                writer.append(String.valueOf(entry.getKey()))
                      .append(" ")
                      .append(String.valueOf(entry.getValue().size()))
                      .append("\n");

                writer.append(entry.getValue().stream().map(String::valueOf).collect(Collectors.joining(" ")))
                      .append("\n");

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
