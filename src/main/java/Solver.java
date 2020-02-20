import model.BaseInfo;
import model.Library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    ArrayList<Library> libs = new ArrayList<>(baseInfo.libraryList);
    libs.sort(Comparator.comparingInt(l -> l.signUpProcess));
    for (Integer bookId : baseInfo.sortedBooks) {
      boolean found = false;
      for (Library l : libs) {
        if (l.booksSet.contains(bookId)){
          if (!found){
            found = true;
          } else {
            l.booksSet.remove(bookId);
          }
        }
//        l.booksSet.removeAll(processBooks);
//        processBooks.addAll(l.booksSet);
      }
    }
    System.out.println("Sorted and cleanup");
    processBooks = new HashSet<>();
    List<Library> libraries = sort(libs);
    int days = baseInfo.days;
    for (Library lib : libraries) {
      if (lib.signUpProcess <= days) {
        addLibrary(map, lib);
        days -= lib.signUpProcess;
      }
    }
    return result;
  }

  private void addLibrary(LinkedHashMap<Integer, List<Integer>> result, Library lib) {


    Set<Integer> books = lib.booksSet;

    if (books.isEmpty()) {
      return;
    }
//    books.removeAll(processBooks);

//    int bookProcessCount = Integer.min(books.size(), lib.booksPerDay * (Integer.min(baseInfo.days - lib.signUpProcess, 0)));

//    processBooks.addAll(books.subList(0, bookProcessCount));

    result.put(lib.id,  new ArrayList<>(books));
  }

  private List<Library> sort(ArrayList<Library> libraries) {

    libraries.sort(getComparator());
    return libraries;
  }

  private Comparator<Library> getComparator() {
    return Comparator.comparing((Library l) -> l.signUpProcess)
        .thenComparing((Library l) -> l.booksPerDay);
//        .thenComparing(l -> l.signUpProcess)
//        .thenComparing((Library l) -> getLibValue(baseInfo.days, baseInfo.scores, l));
  }

  private double getLibValue(int daysForScan, int[] scores, Library l) {

//    return -l.booksPerDay;
//
//    int deliveryDays =
//        Integer.min(l.booksSet.size() / l.booksPerDay, daysForScan - l.signUpProcess) - l.signUpProcess;
    int booksValue = countBooksValue(l.booksSet, scores, daysForScan - l.signUpProcess);
//    return -1.0 * booksValue / deliveryDays;
    return -booksValue;
  }

  private int countBooksValue(Set<Integer> booksIds, int[] scores, int daysToProcess) {

    int sum = 0;
    daysToProcess--;
//    int i = 0;
    Iterator<Integer> it = booksIds.iterator();
    for (int i = 0; i < Integer.min(daysToProcess, booksIds.size()); i++) {
      it.hasNext();
      sum += scores[it.next()];
    }
    return sum;
  }

}
