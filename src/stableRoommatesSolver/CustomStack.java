
package stableRoommatesSolver;
import java.util.*;

/**
 * Custom stack class for generic type T.
 */
public class CustomStack<T>  
{
	// List to represent the stack
	private List<T> stackList;

	/**
     * Constructor to initialize the stack as an ArrayList.
     */
	public CustomStack() 
	{
		stackList = new ArrayList<>();
	}

	/**
     * Pushes an item onto the stack.
     *
     * @param item The item to be pushed onto the stack.
     */
	public void push(T item) 
	{
		stackList.add(item);
	}

	/**
     * Pops an item from the stack.
     *
     * @return The item popped from the stack.
     * @throws EmptyStackException If the stack is empty.
     */
	public T pop() 
	{
		if (isEmpty()) 
		{
			throw new EmptyStackException();
		}
		
	int lastIndex = stackList.size() - 1;
	T item = stackList.get(lastIndex);
    stackList.remove(lastIndex);
    return item;
	}

	 /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
	public boolean isEmpty() 
	{
		return stackList.isEmpty();
	}
}
