import static unionfind.Init.buildQF;
import unionfind.QuickFindUF;

public class TestQF {
    public static void main (String[] args) {
        QuickFindUF qf = null;
        
        qf = buildQF(args[0]);
        
        System.out.println(qf);
    }
}