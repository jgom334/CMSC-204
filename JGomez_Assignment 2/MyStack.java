import java.util.ArrayList;

/**
 * @author Jonathan Gomez
 *
 * @param <T>
 */
public class MyStack<T> implements StackInterface<T> {

	private T[] stack;
	int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 25;
	public static final int MAX_CAPACITY = 40;

	public MyStack() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public MyStack(int desiredCapacity) {

		if (desiredCapacity <= MAX_CAPACITY) {
			stack = (T[]) new Object[desiredCapacity];
			numberOfEntries = 0;
		} else {
			throw new IllegalStateException("Array number of entries has passed the maximum number of entries");
		}

	}

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return numberOfEntries >= stack.length;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();

		}
		T result = stack[numberOfEntries - 1];
		stack[numberOfEntries--] = null;
		return result;
	}

	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return stack[numberOfEntries - 1];
	}

	public int size() {
		return numberOfEntries;
	}

	public boolean push(T e) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		stack[numberOfEntries++] = e;
		return true;
	}

	public String toString() {
		String string = "";
		int index = 0;

		while (index < numberOfEntries) {
			string += stack[index];

			index++;
		}
		return string;

	}

	public String toString(String delimiter) {
		String str = "";
		for (int i = 0; i < numberOfEntries; i++) {

			str += stack[i] + delimiter;

		}
		return str.substring(0, str.length() - 1);
	}

	@SuppressWarnings("unchecked")
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = list;
		for (int i = 0; i < copyList.size(); i++) {
			stack[i] = copyList.get(i);
		}
		numberOfEntries = copyList.size();

	}

}

