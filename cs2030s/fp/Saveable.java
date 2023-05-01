package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class Saveable<T> {

  private final List<T> content;
  private final int pointer;

  private Saveable(List<T> t, int i) {
    this.content = t;
    this.pointer = i;
  }

  public Saveable<T> map(Immutator<? extends T, ? super T> transformer) {
    T edited = transformer.invoke(this.content.get(pointer));
    List<T> appended = new ArrayList<T>();
    appended.add(edited);
    List<T> removedHead = new ArrayList<T>();
    for (int i = pointer; i < this.content.size(); i++) {
      removedHead.add(this.content.get(i));
    }
    appended.addAll(removedHead);
    return Saveable.of(appended, 0);
  }

  public Saveable<T> flatMap1(Immutator<? extends Saveable<? extends T>, ? super T> transformer) {
    Saveable<? extends T> transformed = transformer.invoke(this.content.get(pointer));
    List<T> list = new ArrayList<>();
    for (int i = 0; i < transformed.content.size(); i++) {
      list.add(transformed.content.get(i));
    }
    return Saveable.of(list, transformed.pointer);
  }

  public Saveable<T> flatMap2(Immutator<? extends Saveable<? extends T>, ? super T> transformer) {
    Saveable<? extends T> transformed = transformer.invoke(this.content.get(pointer));
    return this.map(x -> transformed.get());
  }

  public Saveable<T> undo() throws NoSuchElementException {
    if (pointer == this.content.size() - 1) {
      throw new NoSuchElementException();
    } else {
      return Saveable.of(this.content, pointer + 1);
    }
  }

  public Saveable<T> redo() throws NoSuchElementException {
    if (pointer == 0) {
      throw new NoSuchElementException();
    } else {
      return Saveable.of(this.content, pointer - 1);
    }
  }

  protected T get() {
    return this.content.get(pointer);
  }

  @Override
  public String toString() {
    return "Saveable[" + this.content.get(pointer) + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;

    if (obj instanceof Saveable) {
      Saveable<?> saveable = (Saveable<?>) obj;
      return this.get().equals(saveable.get());
    } else return false;
  }

  public static <U> Saveable<U> of(U u) {
    List<U> list = new ArrayList<U>();
    list.add(u);
    return new Saveable<>(list, 0);
  }

  public static <U> Saveable<U> of(List<U> u, int i) {
    return new Saveable<>(u, i);
  }
}
