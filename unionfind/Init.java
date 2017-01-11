package unionfind;

import java.nio.file.*;
import java.util.Scanner;

public class Init {
    
    public static QuickFindUF buildQF(String f_path) {
        QuickFindUF qf = null;
        
        try (Scanner f_scan = new Scanner(Paths.get(f_path))) {
            
            qf = new QuickFindUF(f_scan.nextInt());
            
            while (f_scan.hasNextInt()) {
                qf.union(f_scan.nextInt(), f_scan.nextInt());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return qf;
    }
    
    public static QuickUnion buildQU(String f_path) {
        QuickUnion qu = null;
        
        try (Scanner f_scan = new Scanner(Paths.get(f_path))) {
            
            qu = new QuickUnion(f_scan.nextInt());
            
            while (f_scan.hasNextInt()) {
                qu.union(f_scan.nextInt(), f_scan.nextInt());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return qu;
    }
    
    public static WeightedQuickUnion buildWQU(String f_path) {
        WeightedQuickUnion qu = null;
        
        try (Scanner f_scan = new Scanner(Paths.get(f_path))) {
            
            qu = new WeightedQuickUnion(f_scan.nextInt());
            
            while (f_scan.hasNextInt()) {
                qu.union(f_scan.nextInt(), f_scan.nextInt());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return qu;
    }
}