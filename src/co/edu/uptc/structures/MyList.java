package co.edu.uptc.structures;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<T> implements List<T> {
	private Node<T> head;

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
		return head==null;
	}

	@Override
	public boolean contains(Object o) {
		Node<T> currentNode = head;
		while(currentNode != null){
			if(currentNode.getValue().equals(o)){
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iterator'");
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toArray'");
	}

	@Override
	//comentario de prueba git
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
                if (previous == null) head = actual.getNext();
                else previous.setNext((actual.getNext()));
                return true;
            }
            previous = actual;
            actual = actual.getNext();
        }
        return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
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

		Node<T> current = head;
		if (current == null) {
			Iterator<? extends T> it = c.iterator();
			T firstElement = it.next();
			if (firstElement == null) {
				throw new NullPointerException("Elemento null no permitido.");
			}
			head = new Node<>(firstElement);
			current = head;

			while (it.hasNext()) {
				T element = it.next();
				if (element == null) {
					throw new NullPointerException("Elemento null no permitido.");
				}
				current.setNext(new Node<>(element));
				current = current.getNext();
			}
			return true;
		} else {
			while (current.getNext() != null) {
				current = current.getNext();
			}
			for (T element : c) {
				if (element == null) {
					throw new NullPointerException("Elemento null no permitido.");
				}
				current.setNext(new Node<>(element));
				current = current.getNext();
			}
			return true;
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addAll'");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'get'");
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'set'");
	}

	@Override
	public void add(int index, T element) {

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
		if (index < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		if (index == 0) {
			T removedElement = head.getValue();
			head = head.getNext();
			return removedElement;
		}
		Node<T> current = head.getNext();
		Node<T> previous = head;
		for (int i = 0; i < index-1; i++) {
			current = current.getNext();
			previous = previous.getNext();
		}
		T removedElement = current.getValue();
		previous.setNext(current.getNext());
		return removedElement;
	}

	@Override
	public int indexOf(Object o){
		int index = 0;
		Node<T> current = head;
		while (current!=null) {
			if (o==null) {
				if (current.getValue()==null) {
					return index;
				}
			}else{
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'subList'");
	}

	@Override
	public String toString() {
		return "MyList [head=" + head + "]";
	}

}
