import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
      RandomizedQueue<String> rq = new RandomizedQueue<String>();
      int k = Integer.parseInt(args[0]);
      
      while (StdIn.hasNextLine()) {
         String[] words = StdIn.readLine().split("\\s");
         for (String word : words) {
            if (!word.equals("")) {
               rq.enqueue(word);
            }
         }
      }
      
      for (String str : rq) {
         if (k <= 0) {
            break;
         }
         System.out.println(str);
         k--;
      }
   }
}