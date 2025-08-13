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
		if(head == null){
			head = new Node<T>(e);
			added = true;
		}else{
			Node<T> actual = head;
			while(actual.getNext() != null){
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'size'");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'contains'");
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
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toArray'");
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addAll'");
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'add'");
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
	}

	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
		/*  El metodo listIterador() tiene que utilizar el metodo listIterador(int index) para seguir las normas
			de codigo limpio es decir, no crear codigo duplicado pero en tal caso de que el metodo con el index
			no funcione este tampoco funcionaria ya que este metodo depende directamente del otro por lo que
			hice este comentario con lo que deberia contener en tal caso que es implementarlo sin parametros.
		Node<T> current = head;
		int index = 0;

		return new ListIterator<T>() {
			Node<T> cursor = current;
			int cursorIndex = index;

			@Override
			public boolean hasNext() {
				return cursor != null;
			}

			@Override
			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				T value = cursor.data;
				cursor = cursor.next;
				cursorIndex++;
				return value;
			}

			@Override
			public boolean hasPrevious() {
				return cursorIndex > 0;
			}

			@Override
			public T previous() {
				throw new UnsupportedOperationException("Retroceso no implementado");
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
				throw new UnsupportedOperationException("Remove no implementado");
			}

			@Override
			public void set(T e) {
				throw new UnsupportedOperationException("Set no implementado");
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException("Add no implementado");
			}
		}; */
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
