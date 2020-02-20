import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.BaseInfo;

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
            BaseInfo baseInfo = new BaseInfo();
            //read first line
            sCurrentLine = br.readLine();
            String [] line = sCurrentLine.split(SEPARATOR);
            baseInfo.books = Integer.parseInt(line[0]);
            baseInfo.libraries = Integer.parseInt(line[1]);
            baseInfo.days = Integer.parseInt(line[1]);
            while ((sCurrentLine = br.readLine()) != null) {

                line = sCurrentLine.split(SEPARATOR);



            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
