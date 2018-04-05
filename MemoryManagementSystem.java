/**
 * 
 * @author ADD YOUR NAME & ID
 */
import java.util.Arrays;
import java.util.Queue;

public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	public MainMQueue mainMemory; 
	// YOU CAN ADD MORE FIELDS HERE 
	 
	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.secondaryMemory = new String[secondaryMemorySize];
		this.useLRU= useLRU; 
		for(int i=0; i<secondaryMemorySize; i = i+1) {
			this.secondaryMemory[i]= "";
		}
		this.mainMemory = new MainMQueue(mainMemorySize, secondaryMemorySize, this.secondaryMemory, useLRU);
	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	
	public String read(int index) {
		Page page = this.mainMemory.isExist(index);
		if(page==null) {
			//need to pull it from secondary memory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.enqueue(newPage, this.useLRU);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.isExist(index);
		}
		else {
			this.mainMemory.hadRef(page);
		}
		return page.getInfo();
		// ADD YOUR CODE HERE
	}

	public void write(int index, char c) {
		Page page = this.mainMemory.isExist(index);
		if(page==null) {
		//need to pull it from secondary memory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.enqueue(newPage, this.useLRU);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.isExist(index);
		}
		else {
			this.mainMemory.hadRef(page);
		}
		page.setInfo(c);
		
		// ADD YOUR CODE HERE		
	}
}
