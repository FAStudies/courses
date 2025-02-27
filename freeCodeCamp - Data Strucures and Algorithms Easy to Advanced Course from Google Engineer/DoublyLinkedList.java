public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;

        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Add Element to the tail of the linked list, O(1)
    public void add(T elem) {
        addLast(elem);
    }

    // Add Element to the beginning of the linked list, O(1)
    public void addFirst(T elem) {

        // The linked list is empty
        if (isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;

    }

    // Add Element to the beginning of the linked list, O(1)
    public void addLast(T elem) {
        // The linked list is empty
        if (isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // Check the value of first node if it exists, O(1)
    public T peekFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty List");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty())
            throw new RuntimeException("Empty List");
        return tail.data;
    }

    // Remove the first value at the start of the linked list, O(1)
    public T removeFirst() {
        // Can't remove data from an empty list -_-
        if (isEmpty())
            throw new RuntimeException("Empty List");

        // Extract the data at the head and move
        T data = head.data;
        head = head.next;
        --size;

        // If the list is empty set the tail to null as well
        if (isEmpty())
            tail = null;

        // Do a memory clean of the previous node
        else
            head.prev = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the first value at the end of the linked list, O(1)
    public T removeLast() {
        // Can't remove data from an empty list -_-
        if (isEmpty())
            throw new RuntimeException("Empty List");

        // Extract the data at the tail and move
        T data = head.data;
        tail = tail.prev;
        --size;

        // If the list is empty set the head to null as well
        if (isEmpty())
            head = null;

        // Do a memory clean of the next node
        else
            tail.next = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    //
    private T remove(Node<T> node) {

        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.prev == null)
            return removeFirst();
        if (node.next == null)
            return removeLast();

        // Make the pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // Temporary store the data we want to return
        T data = node.data;

        // Memory Cleanup
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    public T removeAt(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        // Search from the first of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++)
                trav = trav.next;
            // Search from the back of the list
        } else
            for (i = 0, trav = head; i != index; i++)
                trav = trav.prev;

        return remove(trav);
    }

    public boolean remove(Object obj) {
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj) {

        int index = 0;
        Node<T> trav = head;

        // Support searching fr null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next, index++)
                if (trav.data == null)
                    return index;
            // Search for non-null object
        } else
            for (trav = head; trav != null; trav = trav.next, index++)
                if (obj.equals(trav.data))
                    return index;

        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data.toString() + ',');
            trav = trav.next;
        }
        sb.append("]");
        return sb.toString();
    }

}