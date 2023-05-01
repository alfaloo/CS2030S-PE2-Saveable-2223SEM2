import java.lang.reflect.Array;

public class Test4 {
    public static void main(String[] args) {
        CS2030STest we = new CS2030STest();
        we.expect("Streaming.evenTriangular(5).count()", Streaming.evenTriangular(5).count(), Long.valueOf(5));  
        Streaming.evenTriangular(10).forEach(System.out::println);
    }
}
