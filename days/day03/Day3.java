import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

  public static void main(String[] args) throws IOException {
    partOne(fileAsStream("input.txt"));
  }

  private static void partOne(Stream<String> rawClaims) {
    List<Claim> claims = rawClaims.map(Claim::new).collect(Collectors.toList());
    System.out.println("processed claims: " + claims.size());
  }

  private static Stream<String> fileAsStream(String filename) throws IOException {
    return Files.lines(Paths.get(filename));
  }
}

class Fabric {
  private final int sizeSquare = 1000;
}

class Claim {
  public final int id;
  public final int x;
  public final int y;
  public final int height;
  public final int width;

  Claim(String rawClaim) {
    String[] rawSplit = rawClaim.split(" ");
    String[] coordsSplit = rawSplit[2].split(",");
    String[] sizeSplit = rawSplit[3].split("x");

    id = Integer.parseInt(rawSplit[0].substring(1));

    x = Integer.parseInt(coordsSplit[0]);
    y = Integer.parseInt(coordsSplit[1].substring(0, coordsSplit[1].length() - 1));

    height = Integer.parseInt(sizeSplit[0]);
    width = Integer.parseInt(sizeSplit[1]);
  }
}

