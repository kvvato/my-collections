import java.util.Comparator;

public class MyLinkedList<E> implements MyList<E> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Node previous;
        Node next;
        E value;

        Node(E value) {
            this.value = value;
        }
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

        Node newNode = new Node(element);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        if (index == size) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            size++;
            return;
        }

        Node oldNode = getNode(index);
        if (oldNode == head) {
            head = newNode;
        } else {
            oldNode.previous.next = newNode;
        }
        if (oldNode == tail) {
            tail = newNode;
        }
        newNode.previous = oldNode.previous;
        newNode.next = oldNode;
        oldNode.previous = newNode;
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
        Node node = getNode(index);
        return node.value;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        Node node = getNode(index);

        if (node == head) {
            head = node.next;
        } else {
            node.previous.next = node.next;
        }

        if (node == tail) {
            tail = node.previous;
        } else {
            node.next.previous = node.previous;
        }

        node.previous = null;
        node.next = null;

        size--;
        return node.value;
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Sorts the elements according to the order induced by the specified comparator.
     *
     * @param comparator the comparator to determine the order of the elements
     */
    @Override
    public void sort(Comparator<E> comparator) {
        Node node = head;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (comparator.compare(node.value, node.next.value) > 0) {
                    swapNodes(node, node.next);
                } else {
                    node = node.next;
                }
            }
            node = head;
        }
    }

    /**
     * Returns a string representation of this collection.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                s.append(",");
            }
            s.append(node.value);
            node = node.next;
        }
        s.append("]");
        return s.toString();
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
        }
        return node;
    }

    private void swapNodes(Node n1, Node n2) {
        if (n1 == head) {
            head = n2;
        } else {
            n1.previous.next = n2;
        }

        if (n2 == tail) {
            tail = n1;
        } else {
            n2.next.previous = n1;
        }

        n1.next = n2.next;
        n2.previous = n1.previous;
        n1.previous = n2;
        n2.next = n1;
    }
}
