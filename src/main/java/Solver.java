import model.BaseInfo;
import model.Library;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solver {

  private Set<Integer> processBooks = new HashSet<>();

  private BaseInfo baseInfo;

  public Result solve(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
    Result result = new Result();
    LinkedHashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
    result.setLibrariesToBooks(map);

    Set<Library> libraries = sort();
    int days = baseInfo.days;
    for (Library lib : libraries) {
      if (lib.signUpProcess < days) {
        addLibrary(map, lib);
        days -= lib.signUpProcess;
      }
    }
    return result;
  }

  private void addLibrary(LinkedHashMap<Integer, List<Integer>> result, Library lib) {

    List<Integer> books = Stream.of(lib.booksIds).collect(Collectors.toList());

    books.removeAll(processBooks);

    int bookProcessCount = Integer.min(books.size(), lib.booksPerDay * (Integer.min(baseInfo.days - lib.signUpProcess, 0)));

    processBooks.addAll(books.subList(0, bookProcessCount));

    result.put(lib.id, books);
  }

  private Set<Library> sort() {

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

  private int countBooksValue(Integer[] booksIds, int[] scores, int daysToProcess) {

    int sum = 0;
    daysToProcess--;
    for (int i = 0; i < Integer.min(daysToProcess, booksIds.length); i++) {

      sum += scores[booksIds[i]];
    }
    return sum;
  }

}
