/**
 * 
 * @author Liad Nahum 318841285
 * 		   Lishay Aben Sour 207912734
 */

/*The class LRUQueue implements the interface MainQueue according to the LRU approach*/
public class LRUQueue implements MainQueue{
	private Page [] pages;
	private int [] keys;  //each index indicates a page's key, and the value is the page index in pages array
	private Page oldest; //pointer to the head of the queue, the least recently used page 
	private Page newest; //pointer to the most recently used page

	public LRUQueue(int mainMemorySize, String[]secondaryMemory)
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

		/*In the beginning the order of use is according to the pages' place in the pages array.
		 * which means that from page at index 1 on, the "next" field of page at index i-1 points
		 * on the page in index i. And the "prev" field of page at index i points on page at i-1.
		 */
		for(int i=1; i<pages.length; i = i+1){
			this.pages[i-1].setNext(this.pages[i]);
			this.pages[i].setPrev(this.pages[i-1]);
		}
		
		this.oldest =this.pages[0];
		this.newest = this.pages[mainMemorySize-1];	
	}
	
	public boolean isEmpty() {
		return this.oldest==null;
	}
	
	/*The method removes the page that is the least recently used
	 *The methods update the pages array, the keys array and the oldest pointer*/
	public Page dequeue() {
		if(!isEmpty()) {
			Page pToRemove = this.oldest;
			int keyOldest = this.oldest.getKey();
			int indexOldest = this.keys[keyOldest];
			this.keys[keyOldest] = -1;
			this.pages[indexOldest] = null; 
			this.oldest = pToRemove.getNext();
			return pToRemove;
		}
		return null;
	}
		
	/*The method gets new page and adds it to the queue. 
	 * We assume that the queue is always full. Therefore, first, the method calls to dequeue()  
	 * which removes the oldest page. 
	 * Then, the method adds the new page in pages array and in keys array.
	 * In pages array, the method puts the page instead of the oldest page
	 * In keys array, the methods puts in the index that is the page's key, the value of the index of the page
	 * in pages array
	 * Then, the methods update the next and prev fields of the new page and of newest*/
	public void enqueue(Page page) {
		int oldestInd = this.keys[this.oldest.getKey()]; 
		this.dequeue();
		this.pages[oldestInd] = page; 
		this.keys[page.getKey()] = oldestInd; 
			
		page.setPrev(this.newest);
		page.setNext(null);
		this.newest.setNext(page);
		this.newest = page;

	}

	/*The method is responsible for the order of the pages using.
	 * When a page that already exists in the queue has been used again the method is called.
	 *- If the page is already the newest there is nothing to change.
	 *- If the page is the oldest, the method update the oldest pointer.
	 *- Otherwise, the prev of the page needs to point on the next of the page and the next needs to point on the prev
	 *Anyway, the method updates the page to be the newest*/
	public void usePage(Page page) {
		if(!page.equals(this.newest)) {
			if(page.equals(this.oldest)) {
				this.oldest = this.oldest.getNext();
				this.oldest.setPrev(null);
			}
			else {
				page.getPrev().setNext(page.getNext());
				page.getNext().setPrev(page.getPrev());
			}
			this.newest.setNext(page);
			page.setNext(null);
			page.setPrev(newest);
			this.newest = page;
		}
		
	}
	
	/*The method gets a key and returns the page that matches to this key
	 * If the page doesn't exist in the main memory queue, returns null*/ 
	public Page getPage(int key) {
		if(key < this.keys.length && this.keys[key]!=-1) {
			return this.pages[this.keys[key]];
		}
		return null; 
	}
	
	/*The methods returns the Page that is least recently used*/
	public Page getPageToReplace() {
		return this.oldest;
	}
	
	
	
}


