import cs2030s.fp.Immutator;
import cs2030s.fp.Saveable;
import java.util.NoSuchElementException;

public class Test3 {
    public static void main(String[] args) {
        CS2030STest we = new CS2030STest();
        Immutator<Saveable<Integer>, Integer> f = x -> Saveable.of(x).map(y -> y + 1).map(y -> y + 10);
        Saveable<Integer> zero = Saveable.of(0);
        we.expect("zero.flatMap1(f)", zero.flatMap1(f).toString(), "Saveable[11]");   
        we.expect("zero.flatMap1(f).flatMap1(f)", zero.flatMap1(f).flatMap1(f).toString(), "Saveable[22]");   
        
        we.expect("zero.flatMap1(f).undo()", zero.flatMap1(f).undo().toString(), "Saveable[1]"); 
        we.expect("zero.flatMap1(f).undo().undo()", zero.flatMap1(f).undo().undo().toString(), "Saveable[0]"); 
        we.expect("zero.flatMap1(f).flatMap1(f).undo()", zero.flatMap1(f).flatMap1(f).undo().toString(), "Saveable[12]");
        we.expect("zero.flatMap1(f).flatMap1(f).undo().undo()", zero.flatMap1(f).flatMap1(f).undo().undo().toString(), "Saveable[11]");
        we.expectException(
            "zero.flatMap1(f).flatMap1(f).undo().undo().undo()", 
            () -> { zero.flatMap1(f).flatMap1(f).undo().undo().undo(); }, 
            new NoSuchElementException());

        we.expect("zero.flatMap2(f)", zero.flatMap2(f).toString(), "Saveable[11]"); 
        we.expect("zero.flatMap2(f).flatMap2(f)", zero.flatMap2(f).flatMap2(f).toString(), "Saveable[22]");  
        we.expect("zero.flatMap2(f).undo()", zero.flatMap2(f).undo().toString(), "Saveable[0]");
        we.expectException(
            "zero.flatMap2(f).undo().undo()", 
            () -> { zero.flatMap2(f).undo().undo(); }, 
            new NoSuchElementException());  
        we.expect("zero.flatMap2(f).flatMap2(f).undo()", zero.flatMap2(f).flatMap2(f).undo().toString(), "Saveable[11]");  
        we.expect("zero.flatMap2(f).flatMap2(f).undo().undo()", zero.flatMap2(f).flatMap2(f).undo().undo().toString(), "Saveable[0]");  
        we.expectException(
            "zero.flatMap2(f).flatMap2(f).undo().undo().undo()", 
            () -> { zero.flatMap2(f).flatMap2(f).undo().undo().undo(); }, 
            new NoSuchElementException()); 

        Immutator<Saveable<Integer>, Object> hash = o -> Saveable.<Integer>of(o.hashCode() + 10);
        we.expect("Saveable.<Number>of(4).flatMap1(hash)", Saveable.<Number>of(4).flatMap1(hash).toString(), "Saveable[14]");
        we.expect("Saveable.<Number>of(4).flatMap2(hash)", Saveable.<Number>of(4).flatMap2(hash).toString(), "Saveable[14]");
    }
}
