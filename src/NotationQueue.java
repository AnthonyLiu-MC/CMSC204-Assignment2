import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {


	private ArrayList<T> queue;
	private int capacity;

	NotationQueue() {
		queue = new ArrayList<>();
		capacity = 10;
	}

	NotationQueue(int capacity) {
		queue = new ArrayList<>(capacity);
		this.capacity = capacity;
	}

	@Override
	public boolean isEmpty() {
		if (queue.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {

		if (queue.size() == capacity) {
			return true;
		}
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T next = queue.get(0);
		queue.remove(0);
		queue.trimToSize();
		queue.ensureCapacity(capacity);

		return next;
	}

	@Override
	public int size() {

		return queue.size();
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {

		if (queue.size() == capacity) {
			throw new QueueOverflowException();
		}

		queue.add(e);
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(T item: queue) {
			s += item.toString(); 
		}
		return s;
	}

	@Override
	public String toString(String delimiter) {
		String s = "";
		
		for(T item: queue) {
			s += item.toString();
			s += delimiter;
		}
		
		s = s.substring(0,s.length()-1);
		
		return s;
	}

	@Override
	public void fill(ArrayList<T> list) {
		
		queue.clear();
		
		for (T item : list) {
			queue.add(item);
		}

	}

}
