
public class MainMQueue  {
	private Page [] pages;
	private int [] keys;  //each index indicates the page's key, and the value is its index in pages array
	private int head;    //pointer to the head of the queue when use FIFO
	private Page oldest; //pointer to the head of the queue when use LRU  
	private Page newest;
	private boolean useLRU;

	public MainMQueue(int mainMemorySize, String[]secondaryMemory, boolean useLRU)
	{
		this.useLRU = useLRU;
		this.pages = new Page[mainMemorySize];
		this.keys = new int[secondaryMemory.length];
		for(int i=0; i<pages.length; i = i+1) {
			this.pages[i] = new Page(i, secondaryMemory[i]);
		}
		for(int i=0; i<mainMemorySize; i = i+1) {
			this.keys[i] = i;
		}
		for(int i=mainMemorySize; i<keys.length; i = i+1) {
			this.keys[i] = -1;
		}

		//for using LRU 
		for(int i=1; i<pages.length; i = i+1){
			this.pages[i-1].setNext(this.pages[i]);
			this.pages[i].setPrev(this.pages[i-1]);
		}
		this.oldest =this.pages[0];
		this.newest = this.pages[mainMemorySize-1];

		//for using FIFO
		this.head = 0; 
		
		
	}
	public boolean isEmpty() {
		return (this.pages[head]==null) |(oldest== null);
	}
	public Page dequeue() {
		if(!isEmpty()) {
			if(this.useLRU) {
				Page temp = this.oldest;
				int keyOldest = this.oldest.getKey();
				int indexOldest = this.keys[keyOldest];
				this.keys[keyOldest] = -1;
				this.pages[indexOldest] = null; 
				this.oldest = temp.getNext();
				return temp;
			}
		
			else {
				Page p = this.pages[this.head];
				this.pages[this.head] = null;
				if(this.head + 1 == this.pages.length)
					this.head = 0;
				else
					this.head = this.head + 1;
				return p; 
			}
		}
		return null;
	}
	public Page enqueue(Page page, boolean useLru)
	{
		Page pToReplace = null;
		if(useLru) {
			
			//LRU
			pToReplace = this.oldest;
			int index = this.keys[this.oldest.getKey()]; //index in pages array
			this.keys[this.oldest.getKey()] = -1; 
			this.pages[index] = page; 
			this.keys[page.getKey()] = index; 
			this.oldest = this.oldest.getNext();
			
			page.setPrev(this.newest);
			page.setNext(null);
			this.newest.setNext(page);
			this.newest = page;
		}
		else {
			//FIFO
			//need to replace pages
			pToReplace = this.pages[this.head];
			this.pages[this.head] = page;
			this.keys[pToReplace.getKey()] = -1;
			this.keys[page.getKey()] = this.head;
			if(this.head + 1 == this.pages.length)
				this.head = 0;
			else
				this.head = this.head + 1;
		}
		return pToReplace;
	}
	
	public void hadRef(Page page) {
		if(this.useLRU) {
			if(page.equals(this.oldest)) {
				this.oldest = this.oldest.getNext();
			}
			else {
				page.getPrev().setNext(page.getNext());
			}
			this.newest.setNext(page);
			page.setNext(null);
			this.newest = page; 
		}
	}
	
	//return Page according to key if it exists and null otherwise
	public Page isExist(int key) {
		if(key < this.keys.length && this.keys[key]!=-1) {
			return this.pages[this.keys[key]];
		}
		return null; 
	}
	
	

}
