/**
 * 
 * @author Liad Nahum 318841285
 * 		   Lishay Aben Sour 207912734
 */

/*Interface MainQueue defines a queue with the following methods*/
public interface MainQueue {

	public void enqueue(Page page);
	public void dequeue();
	public void usePage(Page page);
	public Page getPage(int key);
	public Page getPageToReplace();
	
}
