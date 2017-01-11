public class QuickFindUF {
    private int[] id;
    
    public QuickFindUF (int N) {
        int idx;
        
        id = new int[N];
        for (idx = 0; idx < N; idx++) {
            id[idx] = idx;
        }
    }
    
    public boolean connected(int p, int q) {
        return (id[p] == id[q]);
    }
    
    public int find(int obj) {
        return id[obj];
    }
    
    public void union(int p, int q) {
        int p_comp = find(p);
        int q_comp = find(q);
        int idx;
        
        if (p_comp != q_comp) {
            
            for (idx = 0; idx < id.length; idx++) {
                if (id[idx] == q_comp) {
                    id[idx] = p_comp;
                }
            }
        }
    }
}