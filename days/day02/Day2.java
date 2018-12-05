import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;

public class Day2 {

  public static void main(String[] args) throws IOException {
     partOne(fileAsStream("input.txt"));
  }

  private static void partOne(Stream<String> input) {
    int twoCount = 0;
    int threeCount = 0;

    ArrayList<HashMap<Character, Integer>> countMaps = new ArrayList<>();

    input.forEach(str -> {
      HashMap<Character, Integer> countMap = countStringChars(str);
      countMaps.add(countMap);
    });

    for (HashMap<Character, Integer> countMap : countMaps) {
      Collection<Integer> counts = countMap.values();

      if (counts.contains(2)) {
        twoCount++;
      }

      if (counts.contains(3)) {
        threeCount++;
      }
    }

    int checksum = twoCount * threeCount;
    System.out.println("Part One: " + checksum);
  }

  private static HashMap<Character, Integer> countStringChars(String inputString) {
    HashMap<Character, Integer> counter = new HashMap<>();

    char[] chars = inputString.toCharArray();
    for (char key : chars) {
      int count = 1;
      if (counter.containsKey(key)) {
        count += counter.get(key);
      }

      counter.put(key, count);
    }

    return counter;
  }

  private static Stream<String> fileAsStream(String filename) throws IOException {
    return Files.lines(Paths.get(filename));
  }
}
