// import java.io.*;
// import java.nio.file.*;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first;
   private int N;
   
   // public Deque() {
      // construct an empty deque
      // Do I even need this?
   // }
   
   public boolean isEmpty() { return N == 0; }                // is the deque empty?
   
   public int size() { return N; } // return the number of items on the deque
   
   public void enqueue(Item item) {
      // add the item to the front
      
      if (item == null) {
         throw new NullPointerException();
      }
      
      Node oldfirst = first;
      first = new Node();
      first.next = oldfirst;
      first.item = item;
      N++;
   }
   
   public Item dequeue() {
      // remove and return the item from the front
      Item removedVal;
      int idx;
      
      if (N == 0) {
         throw new java.util.NoSuchElementException();
      }
      
      idx = StdRandom.uniform(N);
      if (idx == 0) {
         removedVal = first.item;
         first = first.next;
      } else {
         Node toRemove = first;
         Node beforeToRemove = null;
         for (idx = idx; idx > 0; idx--) {
            beforeToRemove = toRemove;
            toRemove = toRemove.next;
         }
         removedVal = toRemove.item;
         beforeToRemove.next = toRemove.next;
      }
      N--;
      
      return removedVal;
   }
   
   public Item sample() {
      if (N == 0) {
         throw new java.util.NoSuchElementException();
      }
      return getItem(StdRandom.uniform(N));
   }
   
   public Iterator<Item> iterator() {
      // return an iterator over items in order from front to end
      return new RandomIterator();
   }
   
   private class RandomIterator implements Iterator<Item> {
      private int[] perm = StdRandom.permutation(N);
      private int idx = 0;
      
      public boolean hasNext() {
         return idx < N;
      }
      
      public Item next() {
         if (idx >= N) {
            throw new java.util.NoSuchElementException();
         }
         Item toReturn = getItem(perm[idx]);
         idx++;
         return toReturn;
      }
      
      public void remove() {
         /* not implemented */
         throw new UnsupportedOperationException();
      }
   }
   
   private class Node {
      Node next;
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
   
   private Item getItem(int idx) {
      Node toReturn = first;
      for (int i = idx; i > 0; i--) {
         toReturn = toReturn.next;
      }
      return toReturn.item;
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