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
    if (c == null || head == null) {
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
