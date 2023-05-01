/**
 * CS2030S PE2 
 * AY2022/23 Semester 1.
 * 
 * @author A0253177B
 **/

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Streaming {
  public static Stream<Integer> evenTriangular(int n) {
    return Stream.iterate(1, x -> x + 1).map(x -> x*(x+1)/2).filter(x -> x % 2 ==0).limit(n);  
  }

  public static boolean isPerfect(int n) {
    return Stream.iterate(1, x -> x + 1).limit(n - 1).filter(x -> n % x == 0).reduce(0, (x, y) -> x + y) == n;    
  }

  public static List<Integer> allPerfect(int start, int end) {
    return Stream.iterate(start, x -> x + 1).takeWhile(x -> x <= end).filter(x -> isPerfect(x)).collect(Collectors.toList());
  }

  public static <T> List<T> decode(Stream<Pair<Integer, T>> stream) {
    return stream.flatMap(x -> Stream.generate(() -> x.getSnd()).limit(x.getFst())).collect(Collectors.toList());
  }
}
