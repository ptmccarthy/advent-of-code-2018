import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

  public static void main(String[] args) throws IOException {
    solve(fileAsStream("input.txt"));
  }

  private static void solve(Stream<String> rawClaims) {
    List<Claim> claims = rawClaims.map(Claim::new).collect(Collectors.toList());

    Fabric fabric = new Fabric(1000);

    for (Claim claim : claims) {
      fabric.processClaim(claim);
    }

    System.out.println("Part One: Overlaps: " + fabric.countOverlappingClaims());

    for (Claim claim: claims) {
      if (claim.isClaimUniqueInFabric(fabric)) {
        System.out.println("Part Two: Unique Claim: " + claim.id);
        break;
      }
    }
  }

  private static Stream<String> fileAsStream(String filename) throws IOException {
    return Files.lines(Paths.get(filename));
  }
}

class Fabric {
  public final int size;
  private final int[][] matrix;


  Fabric(int sizeSquare) {
    size = sizeSquare;
    matrix = new int[sizeSquare][sizeSquare];
  }

  private int setClaimAt(int x, int y) {
    return matrix[x][y]++;
  }

  public int getClaimsAt(int x, int y) {
    return matrix[x][y];
  }

  public void processClaim(Claim claim) {
    for (int x = claim.x; x < claim.x + claim.width; x++) {
      for (int y = claim.y; y < claim.y + claim.height; y++) {
        setClaimAt(x, y);
      }
    }
  }

  public int countOverlappingClaims() {
    int overlaps = 0;

    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        if (matrix[x][y] > 1) {
          overlaps++;
        }
      }
    }

    return overlaps;
  }
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

    width = Integer.parseInt(sizeSplit[0]);
    height = Integer.parseInt(sizeSplit[1]);
  }

  public boolean isClaimUniqueInFabric(Fabric fabric) {
    int count = 0;

    for (int i = x; i < (x + width); i++) {
      for (int j = y; j < (y + height); j++) {
        count += fabric.getClaimsAt(i, j);
      }
    }

    return count == height * width;
  }
}

