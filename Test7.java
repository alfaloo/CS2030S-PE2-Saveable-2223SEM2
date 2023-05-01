import java.util.List;
import java.util.stream.Stream;

public class Test7 {
    public static void main(String[] args) {
        CS2030STest we = new CS2030STest();
        List<Integer> intList1 = List.of(1, 1, 1, 2, 2, 1, 1, 1, 1, 4, 3, 3);
        List<Integer> intList2 = List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        List<String> strList1 = List.of("A", "A", "A", "B", "B", "A", "A", "A", "A", "R", "Z", "Z");
        List<String> strList2 = List.of("A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A");

        List<Pair<Integer, Integer>> pairLst1 = List.of(
            new Pair<Integer, Integer>(3, 1),
            new Pair<Integer, Integer>(2, 2),
            new Pair<Integer, Integer>(4, 1),
            new Pair<Integer, Integer>(1, 4),
            new Pair<Integer, Integer>(2, 3)
        );

        List<Pair<Integer, Integer>> pairLst2 = List.of(
            new Pair<Integer, Integer>(12, 1)
        );

        List<Pair<Integer, String>> pairLst3 = List.of(
            new Pair<Integer, String>(3, "A"),
            new Pair<Integer, String>(2, "B"),
            new Pair<Integer, String>(4, "A"),
            new Pair<Integer, String>(1, "R"),
            new Pair<Integer, String>(2, "Z")
        );

        List<Pair<Integer, String>> pairLst4 = List.of(
            new Pair<Integer, String>(12, "A")
        );

        we.expect("Streaming.decode", Streaming.decode(pairLst1.stream().parallel()).equals(intList1), true);
        we.expect("Streaming.decode", Streaming.decode(pairLst2.stream().parallel()).equals(intList2), true);
        we.expect("Streaming.decode", Streaming.decode(pairLst3.stream().parallel()).equals(strList1), true);
        we.expect("Streaming.decode", Streaming.decode(pairLst4.stream().parallel()).equals(strList2), true);

        we.expect("Streaming.decode", Streaming.decode(pairLst1.stream().parallel()).equals(intList2), false);
        we.expect("Streaming.decode", Streaming.decode(pairLst2.stream().parallel()).equals(intList1), false);
        we.expect("Streaming.decode", Streaming.decode(pairLst3.stream().parallel()).equals(strList2), false);
        we.expect("Streaming.decode", Streaming.decode(pairLst4.stream().parallel()).equals(strList1), false);
    }
}
