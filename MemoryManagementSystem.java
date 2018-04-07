/**
 * 
 * @author ADD YOUR NAME & ID
 */
import java.util.Arrays;

public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	public MainQueue mainMemory; 
	// YOU CAN ADD MORE FIELDS HERE 
	 
	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.secondaryMemory = new String[secondaryMemorySize];
		this.useLRU= useLRU; 
		for(int i=0; i<secondaryMemorySize; i = i+1) {
			this.secondaryMemory[i]= "";
		}
		if(useLRU)
			this.mainMemory = new LRUQueue(mainMemorySize, this.secondaryMemory);
		else
			this.mainMemory = new FIFOQueue(mainMemorySize, this.secondaryMemory);
	}

	public MainQueue m() {
		return this.mainMemory;
	}
	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	
	public String read(int index) {
		Page page = this.mainMemory.getPage(index);
		if(page==null) {
			//need to pull it from secondary memory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.enqueue(newPage);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.getPage(index);
		}
		else {
			this.mainMemory.usePage(page);
		}
		return page.getInfo();
	}

	public void write(int index, char c) {
		Page page = this.mainMemory.getPage(index);
		if(page==null) {
		//need to pull it from secondary memory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.enqueue(newPage);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.getPage(index);
		}
		else {
			this.mainMemory.usePage(page);
		}
		
		page.setInfo(c);	
	}
}
