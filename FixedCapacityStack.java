import java.nio.file.*;

public class FixedCapacityStack<Item> {
  private Item[] items;
  private int top;
  
  public FixedCapacityStack(int cap) {
    items = (Item[]) new Object[cap];
    top = -1;
  }
  
  public void push(Item item) {
    top++;
    items[top] = item;
  }
  
  public Item pop() {
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
  
  public static void main(String[] args) {
    Path file = Paths.get(args[0]);
    FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
    
    try (java.io.BufferedReader reader = Files.newBufferedReader(file)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        String[] words = line.split("\\s");
        for (String word : words) {
          if (word.equals("-")) System.out.print(stack.pop() + " ");
          else             stack.push(word);
        }
      }
    } catch (java.io.IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}
