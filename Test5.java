import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        CS2030STest we = new CS2030STest();
        we.expect("Streaming.isPerfect(4)", Streaming.isPerfect(4), false);
        we.expect("Streaming.isPerfect(5)", Streaming.isPerfect(5), false);
        we.expect("Streaming.isPerfect(6)", Streaming.isPerfect(6), true);
        we.expect("Streaming.isPerfect(7)", Streaming.isPerfect(7), false);
        we.expect("Streaming.isPerfect(8)", Streaming.isPerfect(8), false);

        we.expect("Streaming.isPerfect(26)", Streaming.isPerfect(26), false);
        we.expect("Streaming.isPerfect(27)", Streaming.isPerfect(27), false);
        we.expect("Streaming.isPerfect(28)", Streaming.isPerfect(28), true);
        we.expect("Streaming.isPerfect(29)", Streaming.isPerfect(29), false);
        we.expect("Streaming.isPerfect(30)", Streaming.isPerfect(30), false);

        we.expect("Streaming.allPerfect(1, 10)", Streaming.allPerfect(1, 10), List.of(6));
        we.expect("Streaming.allPerfect(1, 10)", Streaming.allPerfect(1, 100), List.of(6, 28));
    }
}
