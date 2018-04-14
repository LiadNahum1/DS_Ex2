/**
 * 
 * @author Liad Nahum 318841285
 * 		   Lishay Aben Sour 207912734
 */

/*The class FIFOQueue implements the interface MainQueue according to the FIFO approach*/
public class FIFOQueue implements MainQueue{
	private Page [] pages; 
	private int [] keys;  //each index indicates a page's key, and the value is the page index in pages array
	private int head;    //pointer to the head of the queue 
	
	
	public FIFOQueue(int mainMemorySize, String[]secondaryMemory)
	{
		this.pages = new Page[mainMemorySize];
		this.keys = new int[secondaryMemory.length];
		
		//initializes the array of pages with pages from secondary memory(according to the value of mainMemorySize)
		for(int i=0; i<pages.length; i = i+1) {
			this.pages[i] = new Page(i, secondaryMemory[i]);
		}
		
		//initializes the keys array. According to mainMemorySize, we fill the array with the value i 
		for(int i=0; i < mainMemorySize; i = i+1) {
			this.keys[i] = i;
		}
		
		/*From mainMemmorySize value till the end, fill the array with the value -1.
		 * means that from there on the pages with these keys don't exist in the main memory
		 */
		for(int i=mainMemorySize; i<keys.length; i = i+1) {
			this.keys[i] = -1;
		}

		//pointer to the head of the queue
		this.head = 0; 
	}
	public boolean isEmpty() {
		return this.pages[this.head]==null; 
	}
	/*The method removes the page that got first into the queue
	 *The methods update the pages array, the keys array and the head pointer*/
	public Page dequeue() {
		if(!isEmpty())
		{
			Page pToRemove = this.pages[this.head];
			this.pages[this.head] = null;
			this.keys[pToRemove.getKey()] = -1; 
			if(this.head + 1 == this.pages.length)
				this.head = 0;
			else
				this.head = this.head + 1;
			return pToRemove;
		}
		return null;
	}
	
	/*The method gets new page and adds it to the queue. 
	 * We assume that the queue is always full. Therefore, first, the method calls to dequeue()  
	 * which removes the page that at the head of the queue and update the head pointer to the next Page. 
	 * Then, the method adds the new page in pages array and in keys array.
	 * In pages array, the method puts the page instead of the page that was at the head of the queue
	 * In keys array, the methods puts in the index that is the page's key, the value of the index of the page
	 * in pages array*/
	public void enqueue(Page page) {
		int pointerH = this.head; 
		this.dequeue();
		this.pages[pointerH] = page;
		this.keys[page.getKey()] = pointerH;
	}
	
	/*no need in FIFO queue*/
	public void usePage(Page page) {
		
	}
	
	/*The method gets a key and returns the page that matches to this key
	 * If the page doesn't exist in the main memory queue, returns null*/ 
	public Page getPage(int key) {
		if(key < this.keys.length && this.keys[key]!=-1) {
			return this.pages[this.keys[key]];
		}
		
		return null; 
	}
	
	/*The methods returns the Page that is at the head of the queue*/
	public Page getPageToReplace() {
		return this.pages[this.head];
	}
	
	

	
}
