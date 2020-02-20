/**
 * @author Oleksandr Buryk
 */
public class Main {

    public static void main(String[] args) {
        String FILENAME = "a_example.in";
        ReadFile readFile = new ReadFile();
        readFile.readFile(FILENAME);
        Result result = new Solver().solve(readFile);

        SaveFile.save(result);
    }
}
