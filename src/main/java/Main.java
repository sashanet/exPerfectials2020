import model.BaseInfo;

/**
 * @author Oleksandr Buryk
 */
public class Main {

    public static void main(String[] args) {
        String FILENAME = "a_example.txt";
        ReadFile readFile = new ReadFile();
        BaseInfo info = readFile.readFile(FILENAME);
        Result result = new Solver().solve(readFile);

        SaveFile.save(result);
    }
}
