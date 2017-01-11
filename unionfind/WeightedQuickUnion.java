package unionfind;

public class WeightedQuickUnion extends QuickUnion {
    int[] treeSize;
    
    public WeightedQuickUnion (int N) {
        super(N);
        
        treeSize = new int[N];
        for (int idx = 0; idx < N; idx++) {
            treeSize[idx] = 1;
        }
    }
    
    public void union(int p, int q) {
        int child;
        int parent;
        
        if (treeSize[root(p)] <= treeSize[root(q)]) {
            child = root(p);
            parent = root(q);
        } else {
            child = root(q);
            parent = root(p);
        }
        
        setID(child, parent);
        treeSize[parent]++;
    }
}