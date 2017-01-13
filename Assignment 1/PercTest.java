import java.util.Scanner;
import java.nio.file.Paths;
public class PercTest {
   public static void main(String[] a) {
        Percolation p = null;
        int n = 0;
        
        try (Scanner f_scan = new Scanner(Paths.get(a[0]))) {
            
            n = f_scan.nextInt();
            
            p = new Percolation(n);
            
            while (f_scan.hasNextInt()) {
                p.open(f_scan.nextInt(), f_scan.nextInt());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        // System.out.println(p.percolates());
        // System.out.println(p.numberOfOpenSites());
    
        for (int r=1; r<=n; r++) {
           for (int c = 1; c <= n-1; c++){
              System.out.print(String.format("%1$6s",p.getParents(r,c)));
           }
           System.out.println(String.format("%1$6s",p.getParents(r,n)));
        }
   }
}