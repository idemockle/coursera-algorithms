public class Percolation {
   private int[] parents;
   private int[] treeSize;
   
   public Percolation(int n) {               // create n-by-n grid, with all sites blocked
      parents = new int[n*n+2];
      for (int i = 0, j = 0; i < n; i++, j++) {
         if (i <= n) 
            parents[i] = 0;
         else if (i >= n*(n-1)+1)
            parents[i] = n*n+1;
         else 
            parents[i] = -1;
         
         treeSize[i] = 1;
      }
      treeSize[0] = n+1;
      treeSize[n*n+1] = n+1;
   }
   public void open(int row, int col) { 
      // open site (row, col) if it is not open already
      
   }   
   // public boolean isOpen(int row, int col)  // is site (row, col) open?
   // public boolean isFull(int row, int col)  // is site (row, col) full?
   // public     int numberOfOpenSites()       // number of open sites
   // public boolean percolates()              // does the system percolate?
   
   private void union(int p, int q) {
      int child;
      int parent;
      
      if (treeSize[p] >= treeSize[q]) {
         child = findRoot(q);
         parent = findRoot(p);
      } else {
         child = findRoot(p);
         parent = findRoot(q);
      }
      
      parents[child] = parent;
      treeSize[parent] += treeSize[child];
   }
   
   private int findRoot(int nodeIn) {
      int root;
      int currNode = nodeIn;
      
      while (parents[currNode] != currNode)
         currNode = parents[currNode];
      
      root = currNode;
      
      currNode = nodeIn;
      while (parents[currNode] != currNode) {
         parents[currNode] = root;
         currNode = parents[currNode];
      }
      
      return root;
   }
   
   // public static void main(String[] args)   // test client (optional)
}