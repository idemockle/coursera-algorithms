package unionfind;

public class QuickUnion {
    private int[] id;
    
    public QuickUnion (int N) {
        int idx;
        
        id = new int[N];
        for (idx = 0; idx < N; idx++) {
            id[idx] = idx;
        }
    }
    
    public int root(int obj) {
        while (obj != id[obj]) {
            obj = id[obj]
        }
        
        return obj;
    }
    
    public boolean connected(int p, int q) {
        return (root(p) == root(q));
    }
    
    public void union(int p, int q) {
        int child = root(p);
        int parent = root(q);
        
        id[child] = parent;
    }
    
    public String toString() {
        return java.util.Arrays.toString(id);
    }
}