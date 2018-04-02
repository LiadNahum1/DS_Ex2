/**
 * 
 * @author ADD YOUR NAME & ID
 */
public class Page {
	private int key;
	private int nextuse;
	private int beforeuse;
	private String pageContent;

  public Page(int key,int nextuse,int beforeuse)
  {
	  this.key = key;
	  this.nextuse = nextuse;
	  this.beforeuse = beforeuse;
	  this.pageContent = "";
  }
  public Page(int key) {
	  this.key = key;
	  this.nextuse = -1;
	  this.beforeuse = -1;
	  this.pageContent = "";
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
}
