
public interface MemoryQueue {
	public Page getPage(int key);
	public void inque(int pageKey);
	public void usePage(Page page);
	public void deque();
}
