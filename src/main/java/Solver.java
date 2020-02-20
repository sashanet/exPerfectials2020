import model.BaseInfo;
import model.Library;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Solver {

  public Result solve(BaseInfo baseInfo) {

    int daysForScan = baseInfo.days;
    Result result = new Result();

    Set<Library> libraries = sort(baseInfo);

    for (Library lib: libraries) {


    }
    return null;
  }

  private Set<Library> sort(BaseInfo baseInfo){
    TreeSet<Library> set = new TreeSet<>(Comparator.comparingDouble(l -> getLibValue(baseInfo.days, baseInfo.scores, l)));
    set.addAll(baseInfo.libraryList);
    return set;
  }

  private double getLibValue(int daysForScan, int[] scores, Library l) {
    int deliveryDays = Integer.min(l.booksIds.length * l.booksPerDay, daysForScan - l.signUpProcess) - l.signUpProcess;
    int booksValue = countBooksValue(l.booksIds, scores);
    return 1.0 * booksValue/deliveryDays;
  }

  private int countBooksValue(int[] booksIds, int[] scores) {
    int sum = 0;
    for (int id : booksIds) {
      sum += scores[id];
    }
    return sum;
  }
}
