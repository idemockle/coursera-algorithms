// import java.io.*;
// import java.nio.file.*;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   private Node first;
   private Node last;
   private int N;
   
   // public Deque() {
      // construct an empty deque
      // Do I even need this?
   // }
   
   public boolean isEmpty() { return N == 0; }                // is the deque empty?
   
   public int size() { return N; } // return the number of items on the deque
   
   public void addFirst(Item item) {
      // add the item to the front
      
      if (item == null) {
         throw new NullPointerException();
      }
      
      Node oldfirst = first;
      first = new Node();
      first.next = oldfirst;
      first.item = item;
      if (N == 0) {
         last = first;
      } else {
        oldfirst.prev = first;
      }
      N++;
   }
   
   public void addLast(Item item) {
      // add the item to the end
      
      if (item == null) {
         throw new NullPointerException();
      }
      
      Node oldlast = last;
      last = new Node();
      last.prev = oldlast;
      last.item = item;
      if (N == 0) {
         first = last;
      } else {
        oldlast.next = last;
      }
      N++;
   }
   
   public Item removeFirst() {
      // remove and return the item from the front
      
      if (N == 0) {
         throw new java.util.NoSuchElementException();
      }
      
      Node oldfirst = first;
      first = first.next;
      if (N == 1) {
         last = null;
      } else {
         first.prev = null;
      }
      N--;
      
      return oldfirst.item;
   }
   
   public Item removeLast() {
      // remove and return the item from the end
      
      if (N == 0) {
         throw new java.util.NoSuchElementException();
      }
      
      Node oldlast = last;
      last = last.prev;
      
      if (N == 1) {
         first = null;
      } else {
         last.next = null;
      }
      
      N--;
      return oldlast.item;
   }
   
   public Iterator<Item> iterator() {
      // return an iterator over items in order from front to end
      return new ForwardIterator();
   }
   
   private class ForwardIterator implements Iterator<Item> {
      private Node current = first;
      
      public boolean hasNext() {
         return current != null;
      }
      
      public Item next() {
         if (current == null) {
            throw new java.util.NoSuchElementException();
         }
         Node oldcurrent = current;
         current = current.next;
         return oldcurrent.item;
      }
      
      public void remove() {
         /* not implemented */
         throw new UnsupportedOperationException();
      }
      
   }
   
   private class Node {
      Node next;
      Node prev;
      Item item;
      
      // public String toString() {
         // String nextval;
         // String prevval;
         
         // if (next == null) { nextval = "No node."; }
         // else  { nextval = next.item.toString(); }
         
         // if (prev == null) { prevval = "No node."; }
         // else { prevval = prev.item.toString(); }
         
         // return ("\n     Value: " + item + "\n" +
                  // "Next Value: " + nextval + "\n" +
                  // "Prev Value: " + prevval);
      // }
   }
   
   // public static void main(String[] args) {
      // Deque<Integer> deque = new Deque<Integer>();
      
      // /* Path file = Paths.get(args[0]);
      // try (BufferedReader reader = Files.newBufferedReader(file)) {
         // String line = null;
         // while ((line = reader.readLine()) != null) {
            // String[] strInts = line.split("\\s");
            // for (String strNum : strInts) {
               // deque.addFirst(Integer.parseInt(strNum));
            // }
         // }
      // } catch (IOException x) {
         // System.err.format("IOException: %s%n", x);
      // } */
      
      // deque.addFirst(1);
      // deque.addLast(2);
      // deque.addFirst(3);
      // System.out.println(deque.removeFirst());
      // deque.removeFirst();
      // deque.removeFirst();
      
      // System.out.println("---First Node---" + deque.first);
      // System.out.println("---Last Node---" + deque.last);
      
   // }
}