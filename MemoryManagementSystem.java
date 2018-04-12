/**
 * 
 * @author ADD YOUR NAME & ID
 */
import java.util.Arrays;



public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	private MemoryQueue queue;

	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.setUseLRU(useLRU);
		this.secondaryMemory = new String[secondaryMemorySize];
		for (int i = 0; i < secondaryMemorySize; i++)
		{
			secondaryMemory[i] = "";
		}

		if (useLRU)
			queue = new LRUQueue(mainMemorySize, secondaryMemory);
		else
			queue = new FIFOQueue(mainMemorySize, secondaryMemory);

	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}

	public String read(int index) {
		Page page = queue.getPage(index);
		if(page == null) {
			queue.inque(index);
			return queue.getPage(index).readPage();
		}
		else {
			queue.usePage(page);
			return page.readPage();
		}
	}

	public void write(int index, char c) {
		Page page = queue.getPage(index);
		if(page == null) {
			queue.inque(index);
			queue.getPage(index).writeToPage(c);
		}
		else
		{
			queue.usePage(page);
			page.writeToPage(c);
		}
	}

	public boolean isUseLRU() {
		return useLRU;
	}

	public void setUseLRU(boolean useLRU) {
		this.useLRU = useLRU;
	}
}

