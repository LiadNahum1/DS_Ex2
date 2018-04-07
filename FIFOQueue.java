
public class FIFOQueue implements MainQueue{
	private Page [] pages;
	private int [] keys;  //each index indicates the page's key, and the value is its index in pages array
	private int head;    //pointer to the head of the queue 
	
	public FIFOQueue(int mainMemorySize, String[]secondaryMemory)
	{
		this.pages = new Page[mainMemorySize];
		this.keys = new int[secondaryMemory.length];
		
		//initialize array of pages with pages from secondary memory(according to the value of mainMemorySize)
		for(int i=0; i<pages.length; i = i+1) {
			this.pages[i] = new Page(i, secondaryMemory[i]);
		}
		
		//initialize the keys array. According to mainMemorySize, we fill the array with the value i 
		for(int i=0; i<mainMemorySize; i = i+1) {
			this.keys[i] = i;
		}
		
		/*from mainMemmorySize value till the end, fill the array with the value -1.
		 * means that from there on the pages with these keys don't exist in the main memory
		 */
		for(int i=mainMemorySize; i<keys.length; i = i+1) {
			this.keys[i] = -1;
		}

		//pointer to the head of the queue
		this.head = 0; 
	}
	
	public void dequeue() {
		Page pToRemove = this.pages[this.head];
		this.pages[this.head] = null;
		this.keys[pToRemove.getKey()] = -1; 
		if(this.head + 1 == this.pages.length)
			this.head = 0;
		else
			this.head = this.head + 1;
		
	}
	
	//assumes that queue always full
	public Page enqueue(Page page) {
		//need to replace pages
		Page pToReplace = this.pages[this.head];
		int pointerH = this.head; 
		this.dequeue();
		this.pages[pointerH] = page;
		this.keys[page.getKey()] = pointerH;
		return pToReplace;
	}
	
	//no need in FIFO queue
	public void usePage(Page page) {
		
	}
	public Page getPage(int key) {
		if(key < this.keys.length && this.keys[key]!=-1) {
			return this.pages[this.keys[key]];
		}
		return null; 
	}
	
	

	
}
