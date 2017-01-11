import static unionfind.Init.buildWQU;
import unionfind.WeightedQuickUnion;

public class TestWQU {
    public static void main (String[] args) {
        WeightedQuickUnion uf = null;
        
        uf = buildWQU(args[0]);
        
        System.out.println(uf);
    }
}