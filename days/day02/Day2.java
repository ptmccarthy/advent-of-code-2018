import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;

public class Day2 {

  public static void main(String[] args) throws IOException {
     partOne(fileAsStream("input.txt"));
     partTwo(fileAsStream("input.txt"));
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

  private static void partTwo(Stream<String> input) {
    String[] inputStrings = input.toArray(String[]::new);
    int length = inputStrings.length;

    String solution = "";

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        if (i == j) { continue; }

        ArrayList<Integer> diffIndices = getDiffIndices(inputStrings[i], inputStrings[j]);

        if (diffIndices.size() == 1) {
          solution = new StringBuilder(inputStrings[i])
              .deleteCharAt(diffIndices.get(0))
              .toString();
          break;
        }
      }
    }

    System.out.println("Part Two: " + solution);
  }

  private static ArrayList<Integer> getDiffIndices(String one, String two) {
    ArrayList<Integer> diffIndices = new ArrayList<>();

    for (int i = 0; i < one.length(); i++) {
      if (one.charAt(i) != two.charAt(i)) {
        diffIndices.add(i);
      }
    }

    return diffIndices;
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
