public class Pair<T, S> {
    private T first;
    private S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T getFst() {
        return this.first;
    }

    public S getSnd() {
        return this.second;
    }
}
