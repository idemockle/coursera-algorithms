import static unionfind.Init.buildQU;
import unionfind.QuickUnion;

public class TestQU {
    public static void main (String[] args) {
        QuickUnion uf = null;
        
        uf = buildQU(args[0]);
        
        System.out.println(uf);
    }
}