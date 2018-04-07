/**
 * 
 * @author Liad Nahum 318841285
 * 		   Lishay Aben Sour 207912734
 */
/*The class defines a page. 
 * Its attributes are: 
 * -key 
 * -info(the page's content) 
 * -next and prev which are necessary when using URL method. These fields are Page type.
 * next points on the page that has been used right after using the current page
 * prev points on the page that has been used right before using the current page */
public class Page {
	private int key;
	private String info;
	private Page prev;
	private Page next; 
	
	/*constructor*/
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
		return "Key: " + getKey() + " Info: " + getInfo();
	}
	
	/*The method compares the current page with other page according to their keys.
	 * Returns true if they have the same keys and false otherwise.
	 */
	public boolean equals(Page other) {
		if(getKey() == other.getKey())
			return true;
		return false;
	}
}


