import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Day1 {

  public static void main(String[] args) throws IOException {
    partOne(fileAsIntStream("input.txt"));
    partTwo(fileAsIntStream("input.txt"));
  }

  private static void partOne(IntStream input) {
    final int sum = input.sum();
    System.out.println("Part One: " + sum);
  }

  private static void partTwo(IntStream input) {
    int[] adjustments = input.toArray();
    HashSet<Integer> seenFrequencies = new HashSet<>();
    int currentFrequency = 0;
    int idx = 0;

    while (true) {
      if (seenFrequencies.contains(currentFrequency)) {
        break;
      }
      seenFrequencies.add(currentFrequency);

      // can loop through list multiple times
      int adjustment = adjustments[idx % adjustments.length];
      currentFrequency += adjustment;
      idx++;
    }

    System.out.println("Part Two: " + currentFrequency);
  }

  private static IntStream fileAsIntStream(String filename) throws IOException {
    return Files
        .lines(Paths.get(filename))
        .mapToInt(Integer::parseInt);
  }
}