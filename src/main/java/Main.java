import lombok.extern.slf4j.Slf4j;

/**
 * @author Oleksandr Buryk
 */
public class Main {

    public static void main(String[] args) {
        String FILENAME = "a_example.in";
        ReadFile readFile = new ReadFile();
        readFile.readFile(FILENAME);


        SaveFile.save();
    }
}
