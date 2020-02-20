import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Oleksandr Buryk
 */
public class ReadFile {
    private static final String SEPARATOR = " ";
    private String a;

    public void readFile(String fileName) {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String sCurrentLine = null;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
