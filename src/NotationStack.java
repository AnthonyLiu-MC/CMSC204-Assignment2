/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 2
 Instructor: Professor Alexander
 Description: Generic Notation Stack Class
 Due: 09/30/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
import java.util.ArrayList;
/**
 * Generic notation stack class 
 * @author Anthony Liu
 *
 * @param <T>
 */
public class NotationStack<T> implements StackInterface<T>{

	private ArrayList<T> stack;
	private int capacity; 
	
	NotationStack() {

		stack = new ArrayList<>();
		capacity = 10;
	}
	
	NotationStack(int capacity) {

		stack = new ArrayList<>(capacity);
		this.capacity = capacity;

	}
	@Override
	public boolean isEmpty() {
		
		if (stack.isEmpty()) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isFull() {
		if (stack.size() == capacity) {
			return true;
		}
		return false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(stack.size() == 0) {
			throw new StackUnderflowException();
		}

		T top = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return top;
	}

	@Override
	public T top() throws StackUnderflowException{
		if (stack.size() == 0) {
			throw new StackUnderflowException();
		}
		return stack.get(stack.size()-1);
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean push(T item) throws StackOverflowException {
		if (stack.size() == capacity) {
			throw new StackOverflowException();
		}
		
		stack.add(item);
		
		return true;
	}
	
	@Override
	public String toString() {
	String s = "";
		
		for(T item: stack) {
			s += item.toString(); 
		}
		return s;
		
	}

	@Override
	public String toString(String delimiter) {
		String s = "";
		
		for(T item: stack) {
			s += item.toString();
			s += delimiter;
		}
		
		s = s.substring(0,s.length()-1);
		
		return s;
	}

	@Override
	public void fill(ArrayList<T> list) {
		
		stack.clear();

		for (T item : list) {
		
			stack.add(item);
		}

		
	}

}
