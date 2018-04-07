/**
 * 
 * @author Liad Nahum 318841285
 * 		   Lishay Aben Sour 207912734
 */
import java.util.Arrays;

public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	public MainQueue mainMemory; //assumes that mainMemory is always full

	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.useLRU= useLRU; 
		this.secondaryMemory = new String[secondaryMemorySize];
		
		//initializes the secondary memory array with empty strings
		for(int i=0; i<secondaryMemorySize; i = i+1) {
			this.secondaryMemory[i]= "";
		}
		
		/*if the program uses LRU method we define the mainMemory field to point on an instance of LRUQueue
		 * which implements MainQueue. otherwise, we define the mainMemory to be FIFOQueue which also 
		 * implements MainMemory interface
		 */
		if(useLRU)
			this.mainMemory = new LRUQueue(mainMemorySize, this.secondaryMemory);
		else
			this.mainMemory = new FIFOQueue(mainMemorySize, this.secondaryMemory);
	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	
	/*The method gets key of page to read from. 
	 * -If the page doesn't exist in mainMemory, the method pulls out a page from mainMemory 
	 *  according to the method that had chosen (URL or FIFO) and replaces it by the new page.
	 *  The method update the content of the replaced page is secondary memory
	 * -If the page already exists in mainMemory, the method calls to usePage method in order to update 
	 * the chronological order of using pages (important for URL method).
	 * Anyway, the method returns the content of the page. 
	 */
	public String read(int index) {
		Page page = this.mainMemory.getPage(index);
		if(page==null) {
			//need to add the page to mainMemory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.getPageToReplace();
			this.mainMemory.enqueue(newPage);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.getPage(index);
		}
		else {
			this.mainMemory.usePage(page);
		}
		return page.getInfo();
	}

	/*The method gets key of page to write to and a character to add to the page's content. 
	 * -If the page doesn't exist in mainMemory, the method pulls out a page from mainMemory 
	 *  according to the method that had chosen (URL or FIFO) and replaces it by the new page.
	 *  The method update the content of the replaced page is secondary memory
	 * -If the page already exists in mainMemory, the method calls to usePage method in order to update 
	 * the chronological order of using pages (important for URL method).
	 * Anyway, the method adds the character c to the page's content.  
	 */
	public void write(int index, char c) {
		Page page = this.mainMemory.getPage(index);
		if(page==null) {
			//need to add the page to mainMemory
			Page newPage = new Page(index, this.secondaryMemory[index]);
			Page noPlaceInMain = this.mainMemory.getPageToReplace();
			this.mainMemory.enqueue(newPage);
			this.secondaryMemory[noPlaceInMain.getKey()] = noPlaceInMain.getInfo();
			page = this.mainMemory.getPage(index);
		}
		else {
			this.mainMemory.usePage(page);
		}
		page.setInfo(c);	
	}
}
