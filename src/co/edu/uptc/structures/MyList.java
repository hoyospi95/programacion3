package co.edu.uptc.structures;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean add(T e) { // implementacion Reina
        boolean added = false;

        if (isEmpty()) {
            this.head = new Node<T>(e);
            this.tail = head;
            added = true;
        } else {
            Node<T> previous = tail;
            tail = new Node<T>(e);
            previous.setNext(tail);
            tail.setPrevious(previous);
            added = true;

        }

        return added;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {// No requiere edición
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(o)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private Node<T> lastReturned = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                T value = current.getValue();
                current = current.getNext();
                return value;
            }

            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException("No se puede llamar a remove() antes de next()");
                }

                Node<T> prev = lastReturned.getPrevious();
                Node<T> next = lastReturned.getNext();

                if (prev == null) {
                    head = next;
                    if (head != null) {
                        head.setPrevious(null);
                    }
                } else {
                    prev.setNext(next);
                }

                if (next == null) {
                    tail = prev;
                } else {
                    next.setPrevious(prev);
                }

                lastReturned = null;
            }
        };
    }

    public Iterator<T> reverseIterator() {

        return new Iterator<T>() {
            private Node<T> current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.getValue();
                current = current.getPrevious();
                return value;
            }
        };
        // Funciona para lista doblemenete enlazada tambien
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.getValue();
            current.getNext();
        }
        return array;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public <E> E[] toArray(E[] a) {
        // Se modificó
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }

        if (a.length < size) {
            a = (E[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        current = head;
        int index = 0;
        while (current != null) {
            a[index++] = (E) current.getValue();
            current = current.getNext();
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("The element to remove cannot be null");
        }
        if (head != null && o != null && !o.getClass().isAssignableFrom(head.getValue().getClass())) {
            throw new ClassCastException("The element to remove is not compatible with the list type");
        }
        if (head == null) {
            throw new UnsupportedOperationException("Cannot remove from an empty list");
        }
        Node<T> current = head;
        while (current != null) {
            if (o.equals(current.getValue())) {
                if (current.getPrevious() == null) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    } else {
                        tail = null;
                    }
                } else if (current.getNext() == null) {
                    tail = current.getPrevious();
                    tail.setNext(null);
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("The specified collection is null");
        }

        for (Object searchElement : c) {
            boolean found = false;
            Node<T> current = head;

            while (current != null) {
                T listElement = current.getValue();
                if ((searchElement == null && listElement == null)
                        || (searchElement != null && searchElement.equals(listElement))) {
                    found = true;
                    break;
                }
                current = current.getNext();
            }

            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // El metodo addAll no requiere cambios, depende del metodo add(T)
        if (c == null) {
            throw new NullPointerException("La colección no puede ser null.");
        }
        if (c == this && head != null) {
            throw new IllegalArgumentException("No se puede añadir la lista a sí misma.");
        }
        if (c.isEmpty()) {
            return false;
        }

        boolean modified = false;
        for (T element : c) {
            if (element == null) {
                throw new NullPointerException("Elemento null no permitido.");
            }
            add(element);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean modified = false;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index " + index + " no está en el rango");
        }
        if (c != null && !c.isEmpty()) {
            Node<T> firstNew = null;
            Node<T> lastNew = null;
            for (T t : c) {
                if (t != null) {
                    Node<T> newNode = new Node<>(t);
                    if (firstNew == null) {
                        firstNew = newNode;
                    } else {
                        lastNew.setNext(newNode);
                        newNode.setPrevious(lastNew);
                    }
                    lastNew = newNode;
                } else {
                    throw new NullPointerException("Esta lista no admite valores nulos");
                }
            }
            if (index == 0) {
                if (head != null) {
                    lastNew.setNext(head);
                    head.setPrevious(lastNew);
                } else {
                    tail = lastNew;
                }
                head = firstNew;
            } else if (index == size()) {
                if (tail != null) {
                    tail.setNext(firstNew);
                    firstNew.setPrevious(tail);
                } else {
                    head = firstNew;
                }
                tail = lastNew;
            } else {
                Node<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                Node<T> prev = current.getPrevious();
                if (prev != null) {
                    prev.setNext(firstNew);
                    firstNew.setPrevious(prev);
                }
                lastNew.setNext(current);
                current.setPrevious(lastNew);
            }
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("La colección especificada es null");
        }
        if (c.contains(null)) {
            throw new NullPointerException(
                    "La colección contiene null y esta lista no admite elementos null");
        }

        boolean removed = false;

        while (head != null && c.contains(head.getValue())) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            } else {
                tail = null;
            }
            removed = true;
        }

        Node<T> current = head;
        while (current != null) {
            Node<T> nextNode = current.getNext();
            if (nextNode != null && c.contains(nextNode.getValue())) {
                current.setNext(nextNode.getNext());
                if (nextNode.getNext() != null) {
                    nextNode.getNext().setPrevious(current);
                } else {
                    tail = current;
                }
                removed = true;
            } else {
                current = current.getNext();
            }
        }

        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("La colección especificada es null");
        }
        if (c.contains(null)) {
            throw new NullPointerException("La colección contiene null y esta lista no admite elementos null");
        }

        boolean changed = false;
        Node<T> current = head;
        while (current != null) {

        Node<T> nextNode = current.getNext();
        if (!c.contains(current.getValue())) {
            Node<T> prev = current.getPrevious();
            Node<T> next = current.getNext();

            if (prev == null) {
                head = next;
            } else {
                prev.setNext(next);
            }

            if (next == null) {
                tail = prev;
            } else {
                next.setPrevious(prev);
            }

            changed = true;
        }
        current = nextNode;
    }
    return changed;
}

    @Override
    public T get(int index) {
        // El método get(int index) no necesita ser modificado.
        return getNodeAt(index).getValue();
    }

    public T set(int index, T element) {
        validateSetExceptions(index, element);
        Node<T> updatedNode = getNodeAt(index);
        T oldValue = updatedNode.getValue();
        updatedNode.setValue(element);
        return oldValue;
    }

    private void validateSetExceptions(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (element == null) {
            throw new NullPointerException("The Element is null");
        }
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex == index) {
                return current;
            }
            current = current.getNext();
            currentIndex++;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public void add(int index, T element) {
        if (element == null) {
            throw new NullPointerException("El elemento no puede ser null");
        }

        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        Node<T> newNode = new Node<T>(element);

        if (index == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrevious(newNode);
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else {
            Node<T> actual = head;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.getNext();
            }
            newNode.setNext(actual.getNext());
            newNode.setPrevious(actual);
            if (actual.getNext() != null) {
                actual.getNext().setPrevious(newNode);
            } else {
                tail = newNode;
            }
            actual.setNext(newNode);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            T removedElement = head.getValue();
            head = head.getNext();
            return removedElement;
        }
        Node<T> current = head.getNext();
        Node<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
            previous = previous.getNext();
        }
        T removedElement = current.getValue();
        previous.setNext(current.getNext());
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        // El metodo indexOf(Object) no necesita ser modificado
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (o == null) {
                if (current.getValue() == null) {
                    return index;
                }
            } else {
                if (o.equals(current.getValue())) {
                    return index;
                }
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size() - 1;
        Node<T> current = tail;
        while (current != null) {
            if (o == null) {
                if (current.getValue() == null) {
                    return index;
                }
            } else {
                if (o.equals(current.getValue())) {
                    return index;
                }
            }
            index--;
            current = current.getPrevious();
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);

        /*
         * no tiene nada porque no hace el trabajo el mismo,
         * Su única misión es delegar la operación en el método listIterator(int index)
         * arrancando desde la posición 0.
         */
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        return new ListIterator<T>() {
            private Node<T> cursor = getNodeAt(index);

            private int cursorIndex = index;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T value = cursor.getValue();
                cursor = cursor.getNext();
                cursorIndex++;
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return cursorIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new java.util.NoSuchElementException();
                }
                Node<T> temp = head;
                for (int i = 0; i < cursorIndex - 1; i++) {
                    temp = temp.getNext();
                }
                cursor = temp;
                cursorIndex--;
                return cursor.getValue();
            }

            @Override
            public int nextIndex() {
                return cursorIndex;
            }

            @Override
            public int previousIndex() {
                return cursorIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove no implementado");
            }

            @Override
            public void set(T e) {
                if (cursor == null) {
                    throw new IllegalStateException();
                }
                cursor.setValue(e);
            }

            @Override
            public void add(T e) {
                throw new UnsupportedOperationException("add no implementado");
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> subList = new MyList<T>();
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("illegal endpoint index value");
        }
        Node<T> current = head;
        int index = 0;
        while (current != null && index < fromIndex) {
            current = current.getNext();
            index++;
        }
        while (current != null && index <= toIndex) {
            subList.add(current.getValue());
            current = current.getNext();
            index++;
        }
        return subList;
    }

    @Override
    public String toString() {
        return "MyList [head=" + head + "]";
    }

}
