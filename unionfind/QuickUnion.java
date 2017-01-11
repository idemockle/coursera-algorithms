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
        int parent = id[obj];
        int child = obj;
        while (parent != child) {
            child = parent;
            parent = id[child];
        }
        
        return parent;
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