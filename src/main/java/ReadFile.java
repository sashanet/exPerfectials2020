import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import model.BaseInfo;
import model.Library;

/**
 * @author Oleksandr Buryk
 */
public class ReadFile {
    private static final String SEPARATOR = " ";
    private String a;

    public BaseInfo readFile(String fileName) {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String sCurrentLine = null;
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.libraryList = new LinkedList<>();
            //read first line
            sCurrentLine = br.readLine();
            String [] line = sCurrentLine.split(SEPARATOR);
            baseInfo.books = Integer.parseInt(line[0]);
            baseInfo.libraries = Integer.parseInt(line[1]);
            baseInfo.days = Integer.parseInt(line[2]);

            sCurrentLine = br.readLine();
            line = sCurrentLine.split(SEPARATOR);
            int [] scores = new int [line.length];
            for(int i = 0; i<line.length-1; i++){
                scores[i] = Integer.parseInt(line[i]);
            }
            baseInfo.scores =  scores;

            while ((sCurrentLine = br.readLine()) != null) {
                Library library = new Library();

                line = sCurrentLine.split(SEPARATOR);

                library.booksAmount = Integer.parseInt(line[0]);
                library.signUpProcess = Integer.parseInt(line[1]);
                library.booksPerDay = Integer.parseInt(line[2]);
                baseInfo.libraryList.add(library);

                sCurrentLine = br.readLine();
                line = sCurrentLine.split(SEPARATOR);
                int [] booksIds = new int [line.length];
                for(int i = 0; i<line.length; i++) {
                    booksIds[i] = Integer.parseInt(line[i]);
                }
                library.booksIds = booksIds;


            }
            return baseInfo;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
