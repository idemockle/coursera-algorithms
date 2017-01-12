public class Percolation {
   private int[] parents;
   private int[] treeSize;
   private int n;
   
   public Percolation(int n) {               
      // create n-by-n grid, with all sites blocked
      
      if (n<=0) {
         throw new java.lang.IllegalArgumentException();
      }
      
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
      this.n = n;
   }
   public void open(int row, int col) { 
      // open site (row, col) if it is not open already
      int[][] neighbors;
      int nodeToOpen = get1D(row,col);
      int nbRow;
      int nbCol;
      
      parents[nodeToOpen] = nodeToOpen;
      
      neighbors = getNeighbors(row,col);
      
      for (int i=0; i<4; i++) {
         nbRow = neighbors[i][0];
         nbCol = neighbors[i][1];
         if (inBounds(nbRow,nbCol) && isOpen(nbRow,nbCol)) {
            union(nodeToOpen, get1D(nbRow,nbCol));
         }
      }
   }   
   
   public boolean isOpen(int row, int col) {
      // is site (row, col) open?
      return parents[get1D(row,col)] != -1;
   }  
   
   public boolean isFull(int row, int col) { 
      // is site (row, col) full?
      return (findRoot(0) == findRoot(get1D(row,col)));
   }
   
   public int numberOfOpenSites() {
      int nOpen = 0;
      for (int i = 1; i<parents.length-1; i++) {
         if (parents[i] != -1) {
            nOpen++;
         }
      }
      return nOpen;
   }
   
   public boolean percolates() {
      // does the system percolate?
      return (findRoot(0) == findRoot(n*n+2));
   }
   
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
   
   private int get1D(int row, int col){
      return (row*(n-1) + col);
   }
   
   private int[][] getNeighbors(int row, int col) {
      int[][] neighbors = new int[4][2];
      
      neighbors[0][0] = row-1;
      neighbors[0][1] = col;
      neighbors[1][0] = row;
      neighbors[1][1] = col-1;
      neighbors[2][0] = row;
      neighbors[2][1] = col+1;
      neighbors[3][0] = row+1;
      neighbors[3][1] = col;
      
      return neighbors;
   }
   
   private boolean inBounds(int row, int col) {
      return    (   row > 0 
                 && row < n+1
                 && col > 0
                 && col < n+1);
   }
   // public static void main(String[] args)   // test client (optional)
}