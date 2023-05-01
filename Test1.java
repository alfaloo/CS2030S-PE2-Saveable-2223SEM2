import cs2030s.fp.Saveable;
import java.util.NoSuchElementException;

public class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
  
    Saveable<String> u = Saveable.of("PE2");
    we.expect("u.map(x -> x + \"!\")", u.map(x -> x + "!").toString(), "Saveable[PE2!]");
    we.expect("u.map(x -> x + \"!\").undo()", u.map(x -> x + "!").undo().toString(), "Saveable[PE2]");
    we.expect("u.map(x -> x + \"!\").undo().map(x -> x + \"?\")", u.map(x -> x + "!").undo().map(x -> x + "?").toString(), "Saveable[PE2?]");
    we.expect("u.map(x -> x + \"!\").map(x -> x + \"?\").undo()", u.map(x -> x + "!").map(x -> x + "?").undo().toString(), "Saveable[PE2!]");
    we.expect("u.map(x -> x + \"!\").undo().map(x -> x + \"?\").undo()", u.map(x -> x + "!").undo().map(x -> x + "?").undo().toString(), "Saveable[PE2]");
    we.expect("u.map(x -> x + \"!\").map(x -> x + \"?\").undo().undo()", u.map(x -> x + "!").map(x -> x + "?").undo().undo().toString(), "Saveable[PE2]");
    we.expectException(
      "u.map(x -> x + \"!\").map(x -> x + \"?\").undo().undo().undo()", 
      () -> { u.map(x -> x + "!").map(x -> x + "?").undo().undo().undo(); }, 
      new NoSuchElementException());

    we.expect("u.map(x -> x + \"!\").undo()", u.map(x -> x + "!").undo().toString(), "Saveable[PE2]");
    we.expect("u.map(x -> x + \"!\").undo().redo()", u.map(x -> x + "!").undo().redo().toString(), "Saveable[PE2!]");
    we.expect("u.map(x -> x + \"!\").undo().redo().map(x -> x + \"?\")", u.map(x -> x + "!").undo().redo().map(x -> x + "?").toString(), "Saveable[PE2!?]");
    we.expectException(
      "u.map(x -> x + \"!\").undo().map(x -> x + \"?\").redo()",
      () -> { u.map(x -> x + "!").undo().map(x -> x + "?").redo(); }, 
      new NoSuchElementException());

    we.expect("u.map(x -> x + \"!\").map(x -> x + \"?\").undo().redo()", u.map(x -> x + "!").map(x -> x + "?").undo().redo().toString(), "Saveable[PE2!?]");
    we.expect("u.map(x -> x + \"!\").undo().map(x -> x + \"?\").undo().redo()", u.map(x -> x + "!").undo().map(x -> x + "?").undo().redo().toString(), "Saveable[PE2?]");
    we.expect("u.map(x -> x + \"!\").map(x -> x + \"?\").undo().undo().redo()", u.map(x -> x + "!").map(x -> x + "?").undo().undo().redo().toString(), "Saveable[PE2!]");
    we.expect("u.map(x -> x + \"!\").map(x -> x + \"?\").undo().undo().redo().redo()", u.map(x -> x + "!").map(x -> x + "?").undo().undo().redo().redo().toString(), "Saveable[PE2!?]");
    we.expectException("u.map(x -> x + \"!\").map(x -> x + \"?\").undo().redo().redo().redo()", 
    () -> { u.map(x -> x + "!").map(x -> x + "?").undo().redo().redo().redo(); }, 
    new NoSuchElementException());
  
    we.expectCompile(
      "Saveable<Number> x = Saveable.<Number>of(4).map(hash)", 
      "cs2030s.fp.Immutator<Integer, Object> hash = o -> o.hashCode();\n" +
      "cs2030s.fp.Saveable<Number> x = cs2030s.fp.Saveable.<Number>of(4).map(hash);", 
      true);
  }
}