
public interface MainQueue {

	public Page enqueue(Page page);
	public void dequeue();
	public void usePage(Page page);
	public Page getPage(int key);
	
}
