import java.nio.file.*;

public class LinkedListStack<Item> {
  private Node last;
  private int size;
  
  public void push(Item item) {
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.prev = oldlast;
    size++;
  }
  
  public Item pop() {
    Item popped = last.item;
    last = last.prev;
    size--;
    return popped;
  }
  
  public int size() { return size; }
  
  public boolean isEmpty() { return size == 0; }
  
  private class Node {
    Node prev;
    Item item;
  }
  
  public static void main(String[] args) {
    Path file = Paths.get(args[0]);
    LinkedListStack<String> stack = new LinkedListStack<String>();
    
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