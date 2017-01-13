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
      treeSize = new int[n*n+2];
      for (int i = 0, j = 0; i < parents.length; i++, j++) {
         parents[i] = -1;
         treeSize[i] = 1;
      }
      
      // treeSize[0] = 1;
      // treeSize[n*n+1] = 1;
      parents[0] = 0;
      parents[n*n+1] = n*n+1;
      this.n = n;
   }
   public void open(int row, int col) { 
      // open site (row, col) if it is not open already
      int[][] neighbors;
      int nodeToOpen = get1D(row,col);
      int nbRow;
      int nbCol;
      
      if (nodeToOpen > n && nodeToOpen < get1D(n,1)) {
         parents[nodeToOpen] = nodeToOpen;
      } else {
         if (nodeToOpen <= n) {
            parents[nodeToOpen] = 0;
         } else {
            parents[nodeToOpen] = n*n+1;
         }
      }
      
      
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
      return (findRoot(0) == findRoot(n*n+1));
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
      return ((row-1)*(n) + col);
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
   
   public int getParents(int row, int col) {
      return parents[get1D(row,col)];
   }
   
   public int getParents(int idx) {
      return parents[idx];
   }
   
   public static void main(String[] args) {
      // test client (optional)
      Percolation p = new Percolation(2);
      
      System.out.println("percolates? "+p.percolates());
      System.out.println("nOpen? "+p.numberOfOpenSites());
      System.out.println("open 1 1");
      p.open(1,1);
      System.out.println("percolates? "+p.percolates());
      System.out.println("nOpen? "+p.numberOfOpenSites());
      System.out.println(p.isOpen(1,1)+" "+p.isOpen(1,2));
      System.out.println(p.isOpen(2,1)+" "+p.isOpen(2,2));
      System.out.println(p.getParents(2,1));
      System.out.println("open 1 2");
      p.open(1,2);
      System.out.println("percolates? "+p.percolates());
      System.out.println("nOpen? "+p.numberOfOpenSites());
      System.out.println(p.getParents(0));
      System.out.println(p.getParents(1,1)+" "+p.getParents(2));
      System.out.println(p.getParents(3)+" "+p.getParents(4));
      System.out.println(p.getParents(5));
      
      // System.out.println(p.get1D(1,1)+" "+p.get1D(1,2)+" "+p.get1D(2,1)+" "+p.get1D(2,2));
      // System.out.println("n = "+p.n);
      
      
   }
}