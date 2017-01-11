public class Percolation {
   int[][] parents;
   
   public Percolation(int n) {               // create n-by-n grid, with all sites blocked
      parents = new int[n][n];
      for (int i = 0, j = 0; i < n; i++, j++) {
         parents[i][j] = -1;
      }
   }
   // public    void open(int row, int col)    // open site (row, col) if it is not open already
   // public boolean isOpen(int row, int col)  // is site (row, col) open?
   // public boolean isFull(int row, int col)  // is site (row, col) full?
   // public     int numberOfOpenSites()       // number of open sites
   // public boolean percolates()              // does the system percolate?

   // public static void main(String[] args)   // test client (optional)
}