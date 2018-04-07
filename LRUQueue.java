
public class LRUQueue  implements MemoryQueue {
	private int [] secondMemory;
	private String[] secondMemoryContent;
	private Page [] mainMemory;
	int mostUse;
	int leastUse;
	public LRUQueue (int mainMemorySize , String[] secondMemoryContent)
	{
		mostUse = mainMemorySize-1;
		leastUse = 0;
		for(int i = 0; i < mainMemorySize; i++)
		{
			secondMemory[i] = i;
			mainMemory[i] = new Page(i,i--,i++);
		}
		mainMemory[mainMemorySize-1].setNext(-1);//this is because no page is currently more used then him
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
	public void inque(int pageKey) {
		if (secondMemory[pageKey] == -1) { //this will check if the page is already in and if not add it to queue of main memory
			Page page = new Page (pageKey,mostUse,-1,secondMemoryContent[pageKey]);
			mainMemory[mostUse].setNext(leastUse);//because that is where the new page is going to be
			mostUse = leastUse;
			mainMemory[mainMemory[leastUse].getNext()].setBefore(-1);
			this.deque();
			leastUse = mainMemory[leastUse].getNext();
			mainMemory[mostUse] = page;
			secondMemory[pageKey] = mostUse;
		}
	}

	@Override
	public void deque() {
		secondMemoryContent[mainMemory[leastUse].getKey()] = mainMemory[leastUse].readPage();
		secondMemory[mainMemory[leastUse].getKey()] = -1;
		//Updates the second memory on the changes of the page
	}
	@Override
	public void usePage(Page page) {
		int mainMemoryIndex = secondMemory[page.getKey()];
		if(mainMemoryIndex != mostUse) {
			if(mainMemoryIndex == leastUse) {
				leastUse = page.getNext();
			}
			else{
				mainMemory[page.getBefore()].setNext(page.getNext());
			}
			mainMemory[page.getNext()].setBefore(page.getBefore());
			mainMemory[mostUse].setNext(mainMemoryIndex);//because that is where the new page is going to be
			page.setBefore(mostUse);
			page.setNext(-1);//because he is now mostUse
			mostUse = mainMemoryIndex;
		}
	}



}
