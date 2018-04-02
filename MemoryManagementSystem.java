/**
 * 
 * @author ADD YOUR NAME & ID
 */
import java.util.Arrays;



public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	private MemoryQueue queue;
	 
	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.useLRU = useLRU;
		this.secondaryMemory = new String[secondaryMemorySize];
		if (useLRU)
			queue = new LRUQueue(mainMemorySize, secondaryMemorySize);
		else
			queue = new FIFOQueue(mainMemorySize, secondaryMemorySize);
		
		for (int i = 0; i < secondaryMemorySize; i++)
		{
			secondaryMemory[i] = "";
		}
		for(int i = 0; i < mainMemorySize; i++)
		{
			
		}
	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	
	public String read(int index) {
		// ADD YOUR CODE HERE
		
		return null; 
	}

	public void write(int index, char c) {
		// ADD YOUR CODE HERE		
	}
}
