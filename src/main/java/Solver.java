import model.BaseInfo;
import model.Library;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solver {

    public Result solve(BaseInfo baseInfo) {

        int daysForScan = baseInfo.days;
        Result result = new Result();
    LinkedHashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
    result.setLibrariesToBooks(map);

        Set<Library> libraries = sort(baseInfo);
        for (Library lib : libraries) {
            if (lib.signUpProcess < daysForScan) {
                addLibrary(map, lib);
            }
        }
        return result;
    }

    private void addLibrary(LinkedHashMap<Integer, List<Integer>> result, Library lib) {
    List<Integer> ts = IntStream.of(lib.booksIds).boxed().collect(Collectors.toList());
    result.put(lib.id, ts);
    }

    private Set<Library> sort(BaseInfo baseInfo) {

        TreeSet<Library> set =
                new TreeSet<>(Comparator.comparingDouble(l -> getLibValue(baseInfo.days, baseInfo.scores, l)));
        set.addAll(baseInfo.libraryList);
        return set;
    }

    private double getLibValue(int daysForScan, int[] scores, Library l) {

        int deliveryDays =
                Integer.min(l.booksIds.length / l.booksPerDay, daysForScan - l.signUpProcess) - l.signUpProcess;
        int booksValue = countBooksValue(l.booksIds, scores, daysForScan - l.signUpProcess);
        return 1.0 * booksValue / deliveryDays;
    }

    private int countBooksValue(int[] booksIds, int[] scores, int daysToProcess) {

        int sum = 0;
        daysToProcess--;
        for (int i = 0; i < daysToProcess; i++) {

            sum += scores[booksIds[i]];
        }
        return sum;
    }

}
