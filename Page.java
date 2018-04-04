/**
 * 
 * @author ADD YOUR NAME & ID
 */
public class Page {
	private int key;
	private int nextuse;
	private int beforeuse;
	private String pageContent;

	public Page(int key) {
		this.key = key;
		this.nextuse = -1;
		this.beforeuse = -1;
		this.pageContent = "";
	}
	public Page(int key, int prevuse, int nextuse) {
		this.key = key;
		this.nextuse = nextuse;
		this.beforeuse = prevuse;
		this.pageContent = "";
	}
	public Page(int key, int prevuse, int nextuse, String pageContent) {
		super();
		this.pageContent = pageContent;
	}
	public Page(int key,String secoandMemory)
	{
		super();
		this.pageContent = secoandMemory;
	}

	public void setBefore (int beforeuse)
	{
		this.beforeuse = beforeuse;
	}
	public void setNext (int nextuse)
	{
		this.nextuse = nextuse;
	}
	public void writeToPage (char c) {
		this.pageContent = this.pageContent + c;
	}
	public int getNext() {
		return this.nextuse;
	}
	public int getBefore() {
		return this.beforeuse;
	}
	public String readPage() {
		return this.pageContent;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
