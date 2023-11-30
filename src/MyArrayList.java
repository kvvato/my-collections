import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<E> implements MyList<E> {
    private static final double GROW_RATE = 1.5;
    private static final int INITIAL_CAPACITY = 5;
    private Object[] array;
    private int size = 0;

    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Inserts the specified element at the specified position in this
     * list.
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (array.length == size) {
            grow();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts the elements according to the order induced by the specified comparator.
     *
     * @param comparator the comparator to determine the order of the elements
     */
    @Override
    public void sort(Comparator<E> comparator) {
        Arrays.sort((E[]) array, 0, size, comparator);
    }

    /**
     * Returns a string representation of this collection.
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    private void grow() {
        int newCapacity = (int) (array.length * GROW_RATE);
        array = Arrays.copyOf(array, newCapacity);
    }
}
