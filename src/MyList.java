import java.util.Comparator;

public interface MyList<E> {
    int size();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E remove(int index);
    void clear();
    void sort(Comparator<E> comparator);
}
