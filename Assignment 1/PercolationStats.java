import java.util.Random;
// import java.lang.Math;
// import java.lang.String;

public class PercolationStats {
   private int[] thresholds;
   private int n;
   private Random rGen;
   
   public PercolationStats(int n, int trials) {
      // perform trials independent experiments on an n-by-n grid
      Percolation field;
      int nodeRow;
      int nodeCol;
      
      if (n<=0 || trials<=0) {
         throw new java.lang.IllegalArgumentException();
      }
      
      this.n = n;
      thresholds = new int[trials];
      
      for (int i=0; i<trials; i++) {
         field = new Percolation(n);
         
         while ( !field.percolates() ) {
            nodeRow = rand();
            nodeCol = rand();
            if (!field.isOpen(nodeRow, nodeCol)) {
               field.open(nodeRow, nodeCol);
            }
         }
         
         thresholds[i] = field.numberOfOpenSites();
      }
   }
   public double mean() {
      // sample mean of percolation threshold
      double sum = 0;
      
      for (int i=0; i<thresholds.length; i++)
         sum += thresholds[i];
      
      return sum/thresholds.length;
   }
   public double stddev() {
      // sample standard deviation of percolation threshold
      double sqDev = 0;
      double threshMean = mean();
      
      for (int i=0; i<thresholds.length; i++)
         sqDev = Math.pow(thresholds[i]-threshMean, 2);
      
      return Math.sqrt(sqDev / (thresholds.length - 1));
   }     
   public double confidenceLo() {
      // low  endpoint of 95% confidence interval
      double threshMean = mean();
      double threshStDev = stddev();
      
      return (threshMean - (1.96*threshStDev / Math.sqrt(thresholds.length)));
   }
   public double confidenceHi() {
      // high endpoint of 95% confidence interval
      double threshMean = mean();
      double threshStDev = stddev();
      
      return (threshMean + (1.96*threshStDev / Math.sqrt(thresholds.length)));
   }

   private int rand() {
      return (rGen.nextInt(n)+1);
   }
   
   public static void main(String[] args) {
      PercolationStats runs = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
      System.out.println(String.format("%1$24s","mean") + "= " + runs.mean());
      System.out.println(String.format("%1$24s","stddev") + "= " + runs.stddev());
      System.out.println(String.format("%1$24s","95% confidence interval") + "= " + runs.confidenceLo() + ", " + runs.confidenceHi());
      
      
   }
}