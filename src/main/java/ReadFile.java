import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
            int libId = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.trim() == "") {
                    continue;
                }
                Library library = new Library();
                library.id = libId++;
                line = sCurrentLine.split(SEPARATOR);
                System.out.println(sCurrentLine);

                library.booksAmount = Integer.parseInt(line[0]);
                library.signUpProcess = Integer.parseInt(line[1]);
                library.booksPerDay = Integer.parseInt(line[2]);
                baseInfo.libraryList.add(library);

                sCurrentLine = br.readLine();
                line = sCurrentLine.split(SEPARATOR);
                int [] booksIds = new int [line.length];
                for(int i = 0; i < line.length; i++) {
                    booksIds[i] = Integer.parseInt(line[i]);
                }
                final Integer[] sorted = IntStream.range(0, booksIds.length)
                    .mapToObj(i -> new BoostString(booksIds[i], scores[booksIds[i]])) // Create the instance
                    .sorted(Comparator.comparingInt(b -> -b.score))         // Sort using a Comparator
                    .map(b -> b.bookId)                                     // Map it back to a string
                    .toArray(Integer[]::new);

                library.booksIds = sorted;
                library.booksSet = Arrays.stream(booksIds).boxed().collect(Collectors.toCollection(LinkedHashSet::new));
            }

            baseInfo.sortedBooks = IntStream.range(0, scores.length)
                .mapToObj(i -> new BoostString(i, scores[i])) // Create the instance
                .sorted(Comparator.comparingInt(b -> -b.score))         // Sort using a Comparator
                .map(b -> b.bookId)                                     // Map it back to a string
                .toArray(Integer[]::new);

            return baseInfo;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public class BoostString {
        int bookId;
        int score;

        public BoostString(int boost, int score) {
            this.bookId = boost;
            this.score = score;
        }
    }
}
