
public class FIFOQueue implements MemoryQueue {
	private String[] secondMemoryContent;
	private int [] secondMemory;
	private Page [] mainMemory;
	private int first;
	public FIFOQueue (int mainMemorySize , String[] secondMemoryContent)
	{
		this.first = 0;
		this.secondMemoryContent = secondMemoryContent;
		for(int i = 0; i < mainMemorySize; i++)
		{
			secondMemory[i] = i;
			mainMemory[i] = new Page(i);
		}
		for(int j = mainMemorySize; j < secondMemoryContent.length; j++)
		{
			secondMemory [j] = -1;
		}
	}
	@Override
	public Page getPage(int key) {
		if (secondMemory[key] == -1)
			return null;
		else
			return mainMemory [secondMemory[key]];
	}

	@Override
	public void deque() {
		secondMemoryContent[first] = mainMemory[first].readPage(); //Updates the second memory on the changes of the page
	}
	@Override
	public void inque(int pageKey) {
		if (secondMemory[pageKey] == -1) { //this will check if the page is already in and if not add it to queue of main memory
			Page page = new Page (pageKey,secondMemoryContent[pageKey]); 
			this.deque();
			mainMemory[first] = page;
			first = first++;
		}

	}
	@Override
	public void usePage(Page page) { //in fifo queue there is no impotence if a page has been used

	}



}
