package unionfind;

public class WeightedQuickUnion extends QuickUnion {
    int[] treeSize;
    int[] id;
    
    public WeightedQuickUnion (int N) {
        int idx;
        
        id       = new int[N];
        treeSize = new int[N];
        for (idx = 0; idx < N; idx++) {
            id[idx] = idx;
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
        
        id[child] = parent;
        treeSize[parent]++;
    }
}