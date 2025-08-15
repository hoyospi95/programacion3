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
    public boolean add(T e) {
        boolean added = false;
        if (head == null) {
            head = new Node<T>(e);
            added = true;
        } else {
            Node<T> actual = head;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(new Node<T>(e));
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
    public boolean contains(Object o) {
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
				current = current.getNext();
				return value;
			}
		};
        //Funciona para lista doblemenete enlazada tambien
	}

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.getValue();
            current = current.getNext();
        }
        return result;
    }

    @Override
    // comentario de prueba git
    public <T> T[] toArray(T[] a) {
        int size = 0;
        Node<T> current = (Node<T>) head;
        while (current != null) {
            size++;
            current = current.getNext();
        }

        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        current = (Node<T>) head;
        int index = 0;
        while (current != null) {
            a[index++] = (T) current.getValue();
            current = current.getNext();
        }

        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> actual = head, previous = null;
        while (actual != null) {
            if ((actual.getValue()).equals(o)) {
                if (previous == null) {
                    head = actual.getNext();
                } else {
                    previous.setNext((actual.getNext()));
                }
                return true;
            }
            previous = actual;
            actual = actual.getNext();
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

				if ((searchElement == null && listElement == null) ||
						(searchElement != null && searchElement.equals(listElement))) {
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
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index " + index + " is out of range");
        }
        if (c.isEmpty()) {
            return false;
        }
        for (T t : c) {
            if (t == null) {
                throw new NullPointerException("this list does not permit null elements");
            }
        }
        for (T t : c) {
            this.add(index++, t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("La colección especificada es null");
        }

        if (!permiteNulls() && c.contains(null)) {
            throw new NullPointerException(
                    "La colección contiene null y esta lista no admite elementos null");
        }

        try {
            for (Object obj : c) {
                contains(obj);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    "Un elemento de la colección es incompatible con el tipo de esta lista");
        }

        if (head == null) {
            return false;
        }

        boolean removed = false;

        while (head != null && c.contains(head.getValue())) {
            head = head.getNext();
            removed = true;
        }

        Node<T> current = head;
        while (current != null && current.getNext() != null) {
            if (c.contains(current.getNext().getValue())) {
                current.setNext(current.getNext().getNext());
                removed = true;
            } else {
                current = current.getNext();
            }
        }

        return removed;
    }

    private boolean permiteNulls() {
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            boolean found = false;
            for (Object o : c) {
                try {
                    if (current.getValue() == null && c.toArray().equals(null)) {
                        throw new NullPointerException("Este lista contiene null y la colección no permite null");
                    }
                } catch (NullPointerException e) {
                }
                if (current.getValue().equals(o)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (prev == null) {
                    head = current.getNext();
                    current = head;
                } else {
                    prev.setNext(current.getNext());
                    current = current.getNext();
                }
                changed = true;
            } else {
                prev = current;
                current = current.getNext();
            }
        }
        return changed;

    }

    @Override
    public T get(int index) {
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

        if (index == 0) {
            Node<T> newNode = new Node<T>(element);
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> actual = head;

            for (int i = 0; i < index - 1 && actual.getNext() != null; i++) {
                actual = actual.getNext();
            }

            Node<T> newNode = new Node<T>(element);
            newNode.setNext(actual.getNext());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
        /*
         * El metodo listIterador() tiene que utilizar el metodo listIterador(int index)
         * para seguir las normas
         * de codigo limpio es decir, no crear codigo duplicado pero en tal caso de que
         * el metodo con el index
         * no funcione este tampoco funcionaria ya que este metodo depende directamente
         * del otro por lo que
         * hice este comentario con lo que deberia contener en tal caso que es
         * implementarlo sin parametros.
         * Node<T> current = head;
         * int index = 0;
         * 
         * return new ListIterator<T>() {
         * Node<T> cursor = current;
         * int cursorIndex = index;
         * 
         * @Override
         * public boolean hasNext() {
         * return cursor != null;
         * }
         * 
         * @Override
         * public T next() {
         * if (!hasNext()) throw new NoSuchElementException();
         * T value = cursor.data;
         * cursor = cursor.next;
         * cursorIndex++;
         * return value;
         * }
         * 
         * @Override
         * public boolean hasPrevious() {
         * return cursorIndex > 0;
         * }
         * 
         * @Override
         * public T previous() {
         * throw new UnsupportedOperationException("Retroceso no implementado");
         * }
         * 
         * @Override
         * public int nextIndex() {
         * return cursorIndex;
         * }
         * 
         * @Override
         * public int previousIndex() {
         * return cursorIndex - 1;
         * }
         * 
         * @Override
         * public void remove() {
         * throw new UnsupportedOperationException("Remove no implementado");
         * }
         * 
         * @Override
         * public void set(T e) {
         * throw new UnsupportedOperationException("Set no implementado");
         * }
         * 
         * @Override
         * public void add(T e) {
         * throw new UnsupportedOperationException("Add no implementado");
         * }
         * };
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
