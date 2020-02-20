import model.BaseInfo;

import java.util.stream.Stream;

/**
 * @author Oleksandr Buryk
 */
public class Main {

    public static void main(String[] args) {
        String FILENAME = "f.txt";
        String[] files =  new String[]{"a.txt", "b.txt", "c.txt", "d.txt", "e.txt", "f.txt"};
        for (String f : files) {
            ReadFile readFile = new ReadFile();
            BaseInfo info = readFile.readFile(f);
//        int a =0;
            Result result = new Solver().solve(info);

            SaveFile.save(f, result);
            System.out.println("Done: "  + f);
        }
    }
}
