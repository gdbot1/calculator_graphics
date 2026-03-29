package program.gui.element.elements.collection;

public interface ICollection<T> {
    void add(T t);
    void add(int i, T t);

    void set(int i, T t);

    void remove(T t);
    void remove(int i);

    int size();

    T get(int i);

    int indexOf(T t);
}
