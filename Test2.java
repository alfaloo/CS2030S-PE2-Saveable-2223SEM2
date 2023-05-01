import cs2030s.fp.Saveable;

public class Test2 {
    public static void main(String[] args) {
        CS2030STest we = new CS2030STest();
        Saveable<Integer> u = Saveable.of(10);
        we.expect("u.equals(u)", u.equals(u), true);   
        we.expect("u.map(x -> x + 4).equals(u.map(x -> x + 4)", u.map(x -> x + 4).equals(u.map(x -> x + 4)), true);

    }
}
