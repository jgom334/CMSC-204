import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

	private final T[] queue;
	int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 25;
	public static final int MAX_CAPACITY = 40;

	public MyQueue() {
		this(DEFAULT_CAPACITY);
	}

	public MyQueue(int desiredCapacity) {

		if (desiredCapacity <= MAX_CAPACITY) {

			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[desiredCapacity];
			queue = tempQueue;
			numberOfEntries = 0;
		} else {
			throw new IllegalStateException("ILLEGAL STATE EXCEPTION WAS THROWN");
		}

	}

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		return numberOfEntries >= queue.length;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty())
			throw new QueueUnderflowException();
		T result = queue[0];
		for (int i = 0; i < queue.length; i++) {
			if (i != queue.length - 1)
				queue[i] = queue[i + 1];
		}
		numberOfEntries--;
		return result;

	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {

		return numberOfEntries;

	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {

		boolean result;

		if (isFull()) {
			result = false;
			throw new QueueOverflowException();
		} else {
			queue[numberOfEntries] = e;
			numberOfEntries++;
			result = true;
		}
		return result;

	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < numberOfEntries; i++) {
			str += queue[i];
		}
		return str;
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String string = "";
		String result = "";
		for (int i = 0; i < numberOfEntries; i++) {

			string += queue[i] + delimiter;

		}

		result = string.substring(0, string.length() - 1);

		return result;
	}

	/**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyOfList = list;
		for (int i = 0; i < copyOfList.size(); i++) {
			queue[i] = copyOfList.get(i);
		}
		numberOfEntries = copyOfList.size();

	}
}