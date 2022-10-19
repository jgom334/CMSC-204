import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ListIterator;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected int h;
	protected Node head;
	protected Node tail;

	protected class Node {
		protected T data;
		protected Node next;
		protected Node prev;

		protected Node(T data) {
			this.data = data;

		}
	}

	public BasicDoubleLinkedList() {
		this.h = 0;
		this.head = null;
		this.tail = null;
	}

	public boolean a() {
		return h == 0;
	}

	public int getSize() {
		return h;
	}

	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);

		if (a()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		h++;

		return this;
	}

	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);

		if (a()) {
			head = newNode;
			tail = newNode;
		} else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		h++;

		return this;
	}

	public T getFirst() {
		if (a()) {
			return null;
		}

		return head.data;
	}

	public T getLast() {
		if (a()) {
			return null;
		}

		return tail.data;
	}

	@Override
	public ListIterator<T> iterator() {

		return new ListIterator<T>() {

			Node current = head;
			Node lastMove = null;

			@Override
			public boolean hasNext() {

				return current != null;
			}

			@Override
			public T next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T currentData = current.data;
				lastMove = current;
				current = current.next;
				return currentData;
			}

			public boolean hasPrevious() {
				return lastMove != null;
			}

			public T previous() throws NoSuchElementException {
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				T previousData = lastMove.data;
				current = lastMove;
				lastMove = lastMove.prev;
				return previousData;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();

			}

			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();

			}

			@Override
			public void set(T e) {
				throw new UnsupportedOperationException();

			}

		};
	}

	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node current = head;
		while (current != null) {
			if (comparator.compare(targetData, current.data) == 0) {
				if (current == head) {
					head = head.next;
					h--;
					break;
				} else if (current == tail) {
					tail = tail.prev;
					tail.next = null;
					h--;
					break;
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					h--;
					break;
				}

			}
			current = current.next;

		}

		return this;
	}

	public T retrieveFirstElement() {
		if (a()) {
			return null;
		}
		T firstData = head.data;
		head = head.next;
		head.prev = null;
		h--;
		return firstData;
	}

	public T retrieveLastElement() {
		if (a()) {
			return null;
		}
		T lastData = tail.data;
		tail = tail.prev;
		tail.next = null;
		h--;
		return lastData;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(getSize());

		Node current = head;

		while (current != null) {
			list.add(current.data);
			current = current.next;
		}

		return list;
	}

}