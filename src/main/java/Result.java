import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private LinkedHashMap<Integer, List<Integer>> librariesToBooks;

    public LinkedHashMap<Integer, List<Integer>> getLibrariesToBooks() {

        return librariesToBooks;
    }

    public void setLibrariesToBooks(LinkedHashMap<Integer, List<Integer>> librariesToBooks) {

        this.librariesToBooks = librariesToBooks;
    }

}
