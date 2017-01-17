public class FixedStringStack {
  private String[] items;
  private int top;
  
  public FixedStringStack(int cap) {
    items = new String[cap];
    top = -1;
  }
  
  public void push(String item) {
    top++;
    items[top] = item;
  }
  
  public String pop() {
    top--;
    return items[top+1];
  }
  
  public boolean isEmpty() {
    if (top < 0) return true;
    else         return false;
  }
  
  public int size() {
    return top+1;
  }
}