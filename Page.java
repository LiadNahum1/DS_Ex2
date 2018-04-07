/**
 * 
 * @author ADD YOUR NAME & ID
 */
public class Page {
	private int key;
	private String info;
	private Page prev;
	private Page next; //next page that got referenced according to time
	public Page(int key, String info)
	{
		this.key = key;
		this.info = info;
		this.prev = null; 
		this.next = null; 
	}
	
	public int getKey() {
		return this.key;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public Page getNext() {
		return this.next;
	}
	public Page getPrev() {
		return this.prev;
	}
	public void setInfo(char c) {
		this.info = this.info + c;
	}
	
	public void setNext(Page nextPage) {
		this.next = nextPage; 
	}
	
	public void setPrev(Page prevPage) {
		this.prev = prevPage; 
	}
	
	public String toString() {
		return "Key: " + getKey() + "Info: " + getInfo();
	}
	
	public boolean equals(Page other) {
		if(getKey() == other.getKey())
			return true;
		return false;
	}
}
	// ADD YOUR CODE HERE

