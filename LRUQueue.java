
public class LRUQueue implements MainQueue{
	private Page [] pages;
	private int [] keys;  //each index indicates the page's key, and the value is its index in pages array
	private Page oldest; //pointer to the head of the queue when use LRU  
	private Page newest;

	public LRUQueue(int mainMemorySize, String[]secondaryMemory)
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

		for(int i=1; i<pages.length; i = i+1){
			this.pages[i-1].setNext(this.pages[i]);
			this.pages[i].setPrev(this.pages[i-1]);
		}
		this.oldest =this.pages[0];
		this.newest = this.pages[mainMemorySize-1];	
	}
	
	public void dequeue() {
		Page temp = this.oldest;
		int keyOldest = this.oldest.getKey();
		int indexOldest = this.keys[keyOldest];
		this.keys[keyOldest] = -1;
		this.pages[indexOldest] = null; 
		this.oldest = temp.getNext();
		
	}
		
	public Page enqueue(Page page) {
		Page pToReplace = this.oldest;
		int index = this.keys[this.oldest.getKey()]; //index in pages array
		this.dequeue();
		this.pages[index] = page; 
		this.keys[page.getKey()] = index; 
			
		page.setPrev(this.newest);
		page.setNext(null);
		this.newest.setNext(page);
		this.newest = page;
		return pToReplace; 
	}

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
	
	public Page getPage(int key) {
		if(key < this.keys.length && this.keys[key]!=-1) {
			return this.pages[this.keys[key]];
		}
		return null; 
	}
	
	public String toString()
	{
		String s = "";
		Page p = this.oldest;
		while(p!=null)
		{
			s = s+p.getKey() + " ";
			p = p.getNext();
		}
		return s; 
	}
	
}


