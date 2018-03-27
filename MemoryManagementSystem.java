/**
 * 
 * @author ADD YOUR NAME & ID
 */
import java.util.Arrays;


public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	// YOU CAN ADD MORE FIELDS HERE 
	 
	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		// ADD YOUR CODE HERE
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
